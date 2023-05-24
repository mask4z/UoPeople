package Unit7.ProgrammingAssignment;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WebServer {

    static final Integer LISTENING_PORT = 50505;
    static final String rootDirectory = "C:\\Users\\renos\\Desktop\\"; //Set up root directory

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
                handleConnection(connection);
            }
        } catch (Exception e) {
            System.out.println("Server socket shut down unexpectedly!");
            System.out.println("Error: " + e);
            System.out.println("Exiting.");
        }
    }

    public static void handleConnection(Socket connection) throws IOException, RuntimeException {

        PrintWriter pOut;

        try {

            Scanner in = new Scanner(connection.getInputStream());
            OutputStream out = connection.getOutputStream();
            pOut = new PrintWriter(out);

            while (true) {

                String line = in.nextLine();
                List<String> words = Arrays.stream(line.split(" ")).toList();


                if (!words.get(0).contentEquals("GET")) {
                    sendErrorResponse(501, out);
                    connection.close();
                }
                if (line.split(" ").length != 3) {
                    sendErrorResponse(400, out);
                    connection.close();
                }
                System.out.println(line);

                String pathToFile = words.get(1).substring(1);
                
                File selectedFile = new File(rootDirectory + pathToFile);

                if (!selectedFile.exists()) {
                    sendErrorResponse(404, out);
                    connection.close();
                    return;
                }
                if (selectedFile.isDirectory()) {
                    sendErrorResponse(403, out);
                    connection.close();
                    return;
                }
                if (!selectedFile.canRead()) {
                    sendErrorResponse(403, out);
                    connection.close();
                    return;
                } else {
                    pOut.println(getResponseHeaders(selectedFile));
                    pOut.flush();
                    sendFile(selectedFile, out);
                }
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
            System.out.println("Connection closed");
        }
    }

    private static void sendFile(File selectedFile, OutputStream socket) throws IOException {

        InputStream in = new BufferedInputStream(new FileInputStream(selectedFile));
        OutputStream out = new BufferedOutputStream(socket);

        while (true) {
            int byt = in.read();
            if (byt < 0) {
                break;
            }
            out.write(byt);
        }
        out.flush();
    }

    private static String getResponseHeaders(File currentWorkingDirectory) {

        String responseHeader;

        responseHeader = "HTTP/1.1 200 OK\n";
        responseHeader += "Connection: close\n";
        responseHeader += String.format("Content-Length: %d", currentWorkingDirectory.length()) + "\n";

        return responseHeader;
    }

    private static void sendErrorResponse(int responseCode, OutputStream socket) {

        String response = "";
        PrintWriter out = new PrintWriter(socket);

        switch (responseCode) {
            case 400 -> {
                response = "\nHTTP/1.1 400 Bad Request\n";
                response += "Connection: close\n";
                response += "Content-Type: text/html\n\n";
                response += """
                        <html><head><title>Error</title></head><body>\n
                        <h2>Error: 400 Bad Request</h2>\n
                        <p>Syntax error.</p>\n
                        </body></html>
                        """;
                out.println(response);
                out.flush();
            }
            case 403 -> {
                response = "\nHTTP/1.1 403 Forbidden\n";
                response += "Connection: close\n";
                response += "Content-Type: text/html\n\n";
                response += """
                        <html><head><title>Error</title></head><body>\n
                        <h2>Error: 403 Forbidden</h2>\n
                        <p>The resource that you requested is a directory.</p>\n
                        </body></html>
                        """;
                out.println(response);
                out.flush();
            }
            case 404 -> {
                response = "\nHTTP/1.1 404 Not Found\n";
                response += "Connection: close\n";
                response += "Content-Type: text/html\n\n";
                response += """
                        <html><head><title>Error</title></head><body>\n
                        <h2>Error: 404 Not Found</h2>\n
                        <p>The resource that you requested does not exist on this server.</p>\n
                        </body></html>
                        """;
                out.println(response);
                out.flush();
            }
            case 500 -> {
                response = "\nHTTP/1.1 500 Internal Server Error\n";
                response += "Connection: close\n";
                response += "Content-Type: text/html\n\n";
                response += """
                        <html><head><title>Error</title></head><body>\n
                        <h2>Error: 500 Internal Server Error</h2>\n
                        <p>The request to the server is incorrect.</p>\n
                        </body></html>
                        """;
                out.println(response);
                out.flush();
            }
            case 501 -> {
                response = "\nHTTP/1.1 501 Not Implemented\n";
                response += "Connection: close\n";
                response += "Content-Type: text/html\n\n";
                response += """
                        <html><head><title>Error</title></head><body>\n
                        <h2>Error: 501 Not Implemented</h2>\n
                        <p>The request not a GET method.</p>\n
                        </body></html>
                        """;
                out.println(response);
                out.flush();
            }
        }
    }

    private static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0)  // no file extension in name
            return "x-application/x-unknown";
        String ext = fileName.substring(pos + 1).toLowerCase();
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
        else if (ext.equals("xhtml")) return "application/xhtml+xml";
        else return "x-application/x-unknown";
        // Note:  x-application/x-unknown  is something made up;
        // it will probably make the browser offer to save the file.
    }
}
