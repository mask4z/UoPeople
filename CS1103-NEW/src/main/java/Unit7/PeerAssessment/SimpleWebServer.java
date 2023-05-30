package Unit7.PeerAssessment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class SimpleWebServer {

	/**
	 * The server listens on this port. Note that the port number must be greater
	 * than 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50505;

	/**
	 * The server's root directory. I used a directory on my home computer to store
	 * the files that SimpleWebServer would serve up. For running the server on your
	 * computer, user.dir will make the server's root directory the current working
	 * directory that you've saved the file in.
	 */
	private final static String ROOT_DIRECTORY = "/Users/renaldo";

	/**
	 * Main program opens a server socket and listens for connection requests. It
	 * calls the handleConnection() method to respond to connection requests. The
	 * program runs in an infinite loop, unless an error occurs.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		} catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}

	}

	private static class ConnectionThread extends Thread {
		Socket connection;

		ConnectionThread(Socket connection) {
			this.connection = connection;
		}

		public void run() {
			handleConnection(connection);
		}
	}

	/**
	 * This method processes the connection with one client. It creates streams for
	 * communicating with the client, reads a command from the client, and carries
	 * out that command. The connection is also logged to standard output. If no
	 * errors are encountered, a "200 OK" response is sent. Errors 400, 403, 404,
	 * 500, and 501 are implemented according to their descriptions. If the
	 * requested file is a directory, an HTML directory listing response is sent.
	 */
	private static void handleConnection(Socket connection) {

		Scanner in;
		PrintWriter outgoing;
		OutputStream out;
		int position;
		String command;
		String rawFileName, fileName;
		String protocol;

		try {
			in = new Scanner(connection.getInputStream());
			outgoing = new PrintWriter(connection.getOutputStream());
			out = connection.getOutputStream();
			command = in.next();

			if (command.equalsIgnoreCase("index")) {
				sendIndex(new File(ROOT_DIRECTORY), outgoing);
			} else if (!command.equalsIgnoreCase("get")) {
				System.out.println("ERROR: Unsupported command");
				sendErrorResponse(501, out);
			} else {
				rawFileName = in.next();
				protocol = in.next();

				// trimming the file name from the command, protocol and replacing extra
				// spaces(%20)
				try {
					fileName = rawFileName.substring(2 + command.length());
					position = fileName.indexOf("%20" + protocol, 0);
					fileName = fileName.substring(0, position);
					fileName = fileName.replaceAll("%20", " ");
				} catch (StringIndexOutOfBoundsException e) {
					fileName = rawFileName;
				}

				if (!protocol.equals("HTTP/1.1") && !protocol.equals("HTTP/1.0")) {
					System.out.println("Error: Bad request. Not HTTP/1.1 or HTTP/1.0");
					sendErrorResponse(400, out);
				} else {
					File file = new File(ROOT_DIRECTORY, fileName);
					System.out.println("Attempting to retrieve file at: " + file.toString());

					if (file.isDirectory()) {
						sendDirectoryListing(file, outgoing);
					} else if (file.exists() && file.canRead()) {
						outgoing.print(protocol + " 200 OK\r\n");

						outgoing.print("Connection: close\r\n");

						outgoing.print("Content-Length: " + file.length() + "\r\n");

						String type = getMimeType(fileName);
						outgoing.print("Content-Type: " + type + "\r\n");
						outgoing.print("\r\n");
						outgoing.flush();
						sendFile(file, out);
					} else {
						if (file.exists() && !file.canRead()) {
							System.out.println("Error: Permission to read file denied.");
							sendErrorResponse(403, out);
						} else if (!file.exists()) {
							System.out.println("Error: File does not on exist on this server.");
							sendErrorResponse(404, out);
						}
						outgoing.flush();
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
			return;
		} finally { // make SURE connection is closed before returning!
			try {
				connection.close();
			} catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}

	}

	/**
	 * Send the File <code>file</code> over the OutputStream <code>socketOut</code>.
	 * File is sent as a stream of bytes.
	 * 
	 * @param file      File object to be sent.
	 * @param socketOut OutputStream over which the file is to be sent.
	 * @throws IOException If any errors are encountered while creating the
	 *                     InputStream or OutputStream.
	 */
	private static void sendFile(File file, OutputStream socketOut) throws IOException {

		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(socketOut);
		while (true) {
			int x = in.read(); // read one byte from file
			if (x < 0)
				break; // end of file reached
			out.write(x); // write the byte to the socket
		}
		out.flush();

	} // end sendFile

	private static void sendErrorResponse(int errorCode, OutputStream socketOut) {

		String protocol = "HTTP/1.1 ";
		String statusMessage = null, statusDescription = null;

		switch (errorCode) {
		case 400:
			statusDescription += "400 Bad Request";
			statusMessage += "The syntax of the request is bad.";
			break;
		case 403:
			statusDescription += "403 Forbidden";
			statusMessage += "The server does not have permission to read the file.";
			break;
		case 404:
			statusDescription += "404 Not Found";
			statusMessage += "The resource that you requested does not exist on this server.";
			break;
		case 500:
			statusDescription += "500 Internal Server Error";
			statusMessage += "There has been an error in handling the connection.";
			break;
		case 501:
			statusDescription += "501 Not Implemented";
			statusMessage += "The command received has not been implemented.";
			break;
		default:
			statusDescription += "500 Internal Server Error";
			statusMessage += "There has been an error in handling the connection.";
			break;
		}

		try {
			PrintWriter out = new PrintWriter(socketOut);
			out.print(protocol + statusDescription + "\r\n");
			out.print("Connection: close\r\n");
			out.print("Content-Type: text/html\r\n");
			out.print("\r\n");
			out.print("<html><head><title>Error</title></head><body>\r\n");
			out.print("<h2>Error" + statusDescription + "</h2>\r\n");
			out.print("<p>" + statusMessage + "</p>\r\n");
			out.print("</body></html>\r\n");
			out.flush();
			out.close();
		} catch (Exception e) {
			// Nothing to do if error occurs while attempting to send error message.
		}

	} // end sendErrorResponse

	/**
	 * Send an HTML Directory Listing response if the requested file is a directory.
	 * 
	 * @param directory File object that is a directory from which to obtain a
	 *                  listing.
	 * @param outgoing  PrintWriter through which to send the HTML response.
	 * @throws Exception If an error is encountered while attempting to list the
	 *                   files in the directory or while sending the Directory
	 *                   Listing.
	 */
	private static void sendDirectoryListing(File directory, PrintWriter outgoing) throws Exception {

		File[] files = directory.listFiles();

		outgoing.print("HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n" + "<h1>Directory Listing</h1>"
				+ "<h3>" + directory.getPath() + "</h3>" + "<table border=\"0\" cellspacing=\"8\">"
				+ "<tr><th><b>Filename</b></th><th align=\"right\"><b>Size</b></th>"
				+ "<th><b>Last Modified</b></th></tr>"
				+ "<tr><td><b><a href=\"../\">../</b></td><td></td><td></td></tr>");

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				outgoing.print("<tr><td><b><a href=\"" + files[i].getPath() + files[i].getName() + "/\">"
						+ files[i].getName() + "/</a></b></td><td></td><td></td></tr>");
			} else {
				outgoing.print("<tr><td><a href=\"" + files[i].getPath() + "\">" + files[i].getName()
						+ "</a></td><td align=\"right\">" + files[i].length() + "</td><td>"
						+ new Date(files[i].lastModified()).toString() + "</td></tr>");
			}
		}
		outgoing.print("</table><hr>\r\n");
		outgoing.flush();

	} // end sendDirectoryListing

	/**
	 * This is called by the handleConnection() method in response to an "INDEX"
	 * command from the client. Send the list of files in the server's directory.
	 */
	private static void sendIndex(File directory, PrintWriter outgoing) throws Exception {
		String[] fileList = directory.list();
		for (int i = 0; i < fileList.length; i++)
			outgoing.println(fileList[i]);
		outgoing.flush();
		outgoing.close();
		if (outgoing.checkError())
			throw new Exception("Error while transmitting data.");
	}

	/**
	 * Returns the MIME type in String format.
	 * 
	 * @param fileName The filename from which to determine the MIME type.
	 * @return MIME type in String format.
	 */
	private static String getMimeType(String fileName) {

		int pos = fileName.lastIndexOf('.');
		if (pos < 0) // no file extension in name
			return "x-application/x-unknown";
		String ext = fileName.substring(pos + 1).toLowerCase();
		if (ext.equals("txt"))
			return "text/plain";
		else if (ext.equals("html"))
			return "text/html";
		else if (ext.equals("htm"))
			return "text/html";
		else if (ext.equals("css"))
			return "text/css";
		else if (ext.equals("js"))
			return "text/javascript";
		else if (ext.equals("java"))
			return "text/x-java";
		else if (ext.equals("jpeg"))
			return "image/jpeg";
		else if (ext.equals("jpg"))
			return "image/jpeg";
		else if (ext.equals("png"))
			return "image/png";
		else if (ext.equals("gif"))
			return "image/gif";
		else if (ext.equals("ico"))
			return "image/x-icon";
		else if (ext.equals("class"))
			return "application/java-vm";
		else if (ext.equals("jar"))
			return "application/java-archive";
		else if (ext.equals("zip"))
			return "application/zip";
		else if (ext.equals("xml"))
			return "application/xml";
		else if (ext.equals("xhtml"))
			return "application/xhtml+xml";
		else
			return "x-application/x-unknown";
		// Note: x-application/x-unknown is something made up;
		// it will probably make the browser offer to save the file.

	} // end getMimeType

}
