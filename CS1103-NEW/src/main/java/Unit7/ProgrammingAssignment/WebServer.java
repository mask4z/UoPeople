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
    static final String rootDirectory = "C:\\Users\\Renaldo\\Desktop\\"; //Set up root directory

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
                System.out.println("\nConnection from "
                        + connection.getRemoteSocketAddress());
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
            String line = in.nextLine();
            List<String> words = Arrays.stream(line.split(" ")).toList();

            if (!words.get(0).contentEquals("GET")) {
                sendErrorResponse(501, out);
                connection.close();
            }
            if (in.tokens().count() != 3) {
                sendErrorResponse(400, out);
                connection.close();
            }
            System.out.println(line);

            File currentWorkingDirectory = new File(rootDirectory);
            String pathToFile = words.get(1).substring(1);
            if (words.get(1).startsWith("/")) {

                if (!currentWorkingDirectory.exists()) {
                    sendErrorResponse(404, out);
                    connection.close();
                    return;
                }
                if (currentWorkingDirectory.listFiles() != null
                        && Objects.requireNonNull(currentWorkingDirectory.listFiles()).length != 0) {

                    String response = getResponseHeaders(currentWorkingDirectory);
                    response += "Content-type: text/plain\r\n";
                    pOut.println(response);
                    pOut.println("Directory: " + currentWorkingDirectory.getName());
                    for (File file :
                            currentWorkingDirectory.listFiles()) {
                        if (file.isDirectory()) {
                            pOut.println("  dir: " + file.getName());
                        }
                        pOut.println("  file: " + file.getName());
                    }
                    pOut.flush();
                    connection.close();
                } else {
                    pOut.println(getResponseHeaders(currentWorkingDirectory));
                    pOut.println("Content-Type: text/plain\r\n");
                    pOut.println("No such file/s on Webserver.");
                    pOut.flush();
                    connection.close();
                }
            }
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
        } catch (IOException io) {
            throw new RuntimeException(io);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
            System.out.println("Connection closed");
        }
    }

    private static void sendFile(File selectedFile, OutputStream out) {

    }

    private static String getResponseHeaders(File currentWorkingDirectory) {
        return null;
    }

    private static void sendErrorResponse(int responseCode, OutputStream out) {

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
