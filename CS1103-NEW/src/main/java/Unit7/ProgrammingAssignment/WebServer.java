package Unit7.ProgrammingAssignment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

    public static void handleConnection(Socket connection) throws IOException {

        try (connection) {

            Scanner in = new Scanner(connection.getInputStream());
            OutputStream out = connection.getOutputStream();
            PrintWriter pOut = new PrintWriter(out);

//            while (true) { // old implementation to demonstrate what the request looks like.
//                if (!in.hasNextLine())
//                    break;
//                String line = in.nextLine();
//                if (line.trim().length() == 0)
//                    break;
//                System.out.println("   " + line);
//            }

            while (true) {
                if (!in.hasNext()) {
                    break;
                }
                String request = in.nextLine();
                if (request.trim().length() == 0) {
                    break;
                } else if (request.trim().startsWith("GET")) {
                    pOut.print("Test"
                            + "\r\n");
                    pOut.flush();
                    System.out.println(request);
                } else {
                    pOut.print("Response 404: File Not Found"
                            + "\r\n");
                    pOut.flush();
                    throw new RuntimeException("EEEERRRRR NOPE");
                }
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Connection closed");
            connection.close();
        }
    }
}
