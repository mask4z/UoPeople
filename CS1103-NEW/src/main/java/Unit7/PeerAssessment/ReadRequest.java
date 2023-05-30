package Unit7.PeerAssessment;

// Usage to test HTTP GET: localhost:50505?c:\test\test.txt
// I used curl, postman and chrome web browser to test.

// This is a simple web server to handel HTTP GET requests.


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/** ReadRequest - ServerSocket listens on port LISTENING_PORT for request.  It is looking for HTTP GET request. 
 */
public class ReadRequest {
	
	/** LISTENING_PORT
	 */
	private final static int LISTENING_PORT = 50505;
	
	/** main
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		}
		catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			// Handle Connection Request with Multi Thread Function.
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
				HandleConnectionThread connthread = new HandleConnectionThread(connection);
				connthread.start();
			}
		}
		catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		} finally {
		    try {
		    	serverSocket.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}

	/** HandleConnectionThread - This handles the http request.
    */
    private static class HandleConnectionThread extends Thread {
        private final Socket clientSocket;

        public HandleConnectionThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
            	// BufferedReader
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // OutputStream
                OutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());
                // FileErrorReturn - This is a object for file data and error.
                FileErrorReturn fileErrorReturn = new FileErrorReturn();
                // DataErrorReturn - This is a object for data and error.
                DataErrorReturn dataErrorReturn = new DataErrorReturn();
    			while (true) {
    				String line = in.readLine();
    				// No more data and have not found GET verb.  ERROR.
    				if (line == null) {
    					fileErrorReturn.Error = makeHTTPErrorResponse("400", "Bad Request", "HTTP 1.1 or 1.0 only on this server.");
    					break;
    				}
    				// Found POST verb which is not Implemented.  ERROR.
    				if (line != null && line.length() >= 4 && line.substring(0, 4).equals("POST") && line.contains("HTTP")) {
    					fileErrorReturn.Error = makeHTTPErrorResponse("501", "Not Implemented", "HTTP GET only on this server.");
    					break;
    				}
    				// Found GET verb.
    				if (line != null && line.length() >= 3 && line.substring(0, 3).equals("GET") && line.contains("HTTP")) {
    					fileErrorReturn = getFilenameAndPath(line);
    					break;
    				}
    			}
    			// Send file data back, if successful.
    			if (fileErrorReturn.File != null && fileErrorReturn.Error.isEmpty()) {
    				dataErrorReturn = getFileData(fileErrorReturn.File);    				
    				if (dataErrorReturn.Data != null && dataErrorReturn.Error.isEmpty()) {
    					out.write(dataErrorReturn.Data.getBytes("UTF8"));
    				}
     			}
    			// Send error back, if not successful.
    			if (!fileErrorReturn.Error.isEmpty()) {
    				out.write(fileErrorReturn.Error.getBytes("UTF8"));
    			}
    			else if (!dataErrorReturn.Error.isEmpty()) {
    				out.write(dataErrorReturn.Error.getBytes("UTF8"));
    			}
                out.flush();
            } catch (Exception e) {            	
                System.out.println("Server socket shut down unexpectedly!");
                System.out.println("Error: " + e);
                System.out.println("Exiting.");
                try {
                	// 500 Internal Server Error
                    OutputStream outerror = new BufferedOutputStream(clientSocket.getOutputStream());
                    outerror.write(makeHTTPErrorResponse("500", "Internal Server Error", "Internal Server Error.").getBytes("UTF8"));
                    outerror.flush();                 
                } catch (IOException eerror) {
                	eerror.printStackTrace();
                }
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    	
	/** getFilenameAndPath - This is getting the data from (file and path) and verifying that it is good.
    */
	private static FileErrorReturn getFilenameAndPath(String data) {
		FileErrorReturn fileErrorReturn = new FileErrorReturn();
		File file  = null;
		// Parse data.
		String tokens = data.split(" ")[1];		
		String pathToFile = tokens.split("/")[1].replace("?file", "").replace("&file", "");		
		pathToFile = pathToFile.substring(1);			
		if (pathToFile != null && pathToFile.length() > 0) {
			file = new File(pathToFile);
			// File or Directory does not exists.  ERROR.
			if (!file.isDirectory() && !file.exists()) {
				fileErrorReturn.Error = makeHTTPErrorResponse("404", "Not Found", "The resource (File or Directory) that you requested does not exist on this server.");
			}
			// Cannot read data in file.  ERROR.
			else if (!file.canRead()) {
				fileErrorReturn.Error = makeHTTPErrorResponse("403", "Forbidden", "The resource (File) does not have permission to be read on this server.");
			}
			fileErrorReturn.File = file;
		}		
		return fileErrorReturn;
	}

	/** getFilenameAndPath - This is getting the (file and path) and verifying that it is good.
	*/
	private static DataErrorReturn getFileData(File file) throws IOException {
		DataErrorReturn dataErrorReturn = new DataErrorReturn();
		dataErrorReturn.Data += "HTTP/1.1 200 OK\r\n";
		String contentType = getMimeType(file.getName());
		String contentLength = String.valueOf(file.length());
		dataErrorReturn.Data += "Content-Type: " + contentType + "\r\n";       
		dataErrorReturn.Data += "\r\n";
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		while (true) {
			int x = in.read(); 
			if (x < 0)
				break;
			dataErrorReturn.Data += String.valueOf(((char)x));
		}
		in.close();
		dataErrorReturn.Data += "\r\n";
		return dataErrorReturn;
	}

	/** getMimeType
	*/
	private static String getMimeType(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) 
		return "x-application/x-unknown";
		String ext = fileName.substring(pos+1).toLowerCase();
		if (ext.equals("txt")) return "text/plain";
		else if (ext.equals("html")) return "text/html";
		else if (ext.equals("htm")) return "text/html";
		else if (ext.equals("css")) return "text/css";
		else if (ext.equals("js")) return "text/javascript";
		else if (ext.equals("java")) return "text/x-java";
		else if (ext.equals("jpeg")) return "image/jpeg";
		else if (ext.equals("jpg")) return "image/jpeg";
		else if (ext.equals("png")) return "image/png";
		else if (ext.equals("gif")) return "image/gif";
		else if (ext.equals("ico")) return "image/x-icon";
		else if (ext.equals("class")) return "application/java-vm";
		else if (ext.equals("jar")) return "application/java-archive";
		else if (ext.equals("zip")) return "application/zip";
		else if (ext.equals("xml")) return "application/xml";
		else if (ext.equals("xhtml")) return"application/xhtml+xml";
		else return "x-application/x-unknown";
	}
	
	/** makeHTTPResponse - Used to from HTTP Error Response.
	*/
	public static String makeHTTPErrorResponse(String errornumber, String errorstring, String errortext)
	{
		return "HTTP/1.1 " + errornumber + " " + errorstring + "\r\n"
			+ "Content-Type: text/html\r\n\r\n"
			+ "<html><head><title>Error</title></head><body>"
			+ "<h2>Error: " + errornumber + " " + errorstring + "</h2>"
			+ "<p>"+ errortext +"</p>"
			+ "</body></html>";
	}

	/** ErrorReturn
	*/
	public static  class ErrorReturn {
		public String Error;
		public ErrorReturn() {		
			Error = "";
		}
	}
	
	/** FileErrorReturn
	*/
	public static  class FileErrorReturn extends ErrorReturn {
		public File File;
		public FileErrorReturn() {
			File = null;
		}		
	}
	
	/** DataErrorReturn
	*/
	public static  class DataErrorReturn extends ErrorReturn {
		public String Data;
		public DataErrorReturn() {
			Data = "";
		}
	}

}
