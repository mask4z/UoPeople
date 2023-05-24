package Unit7.ProgrammingAssignment;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WebServer {

    static final Integer LISTENING_PORT = 50505;

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

        try (connection; connection) {

            Scanner in = new Scanner(connection.getInputStream());
            OutputStream out = connection.getOutputStream();
            PrintWriter pOut = new PrintWriter(out);
            String rootDirectory = ""; //Set up root directory

            while (true) {
                if (!in.hasNext()) {
                    break;
                }
                String request = in.nextLine();
                if (request.trim().length() == 0) {
                    break;
                }
                if (request.trim().startsWith("GET")) {
                    System.out.println(request);

                    if (request.trim().substring(4).startsWith("/")) {
                        String pathToFile = request.trim().substring(3);
                        File file = new File(rootDirectory + pathToFile);

                        if (!file.exists()) {
                            pOut.print("HTTP/1.1 404 File Not Found\r\n\r\n");
                            pOut.flush();
                            throw new RuntimeException("File not found");
                        }
                        //Handle file
                    }
                } else {
                    pOut.print("HTTP/1.1 405 Method Not Allowed\r\n\r\n");
                    pOut.flush();
                    throw new RuntimeException("Method not allowed");
                }
                if (in.tokens().count() != 3) {
                    pOut.print("HTTP/1.1 Error: Invalid tokens\r\n\r\n");
                    pOut.flush();
                    throw new RuntimeException("Invalid tokens");
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
}
