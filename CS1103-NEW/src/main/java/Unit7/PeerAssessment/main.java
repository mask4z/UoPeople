package Unit7.PeerAssessment;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class main {
	  private final static int LISTENING_PORT = 50505;
	  
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
	            while (true) {
	                Socket connection = serverSocket.accept();
	                System.out.println("\nConnection from " 
	                        + connection.getRemoteSocketAddress());
	                
	                ConnectionThread thread = new ConnectionThread(connection);
	                thread.start();
	            }
	        }
	        catch (Exception e) {
	            System.out.println("Server socket shut down unexpectedly!");
	            System.out.println("Error: " + e);
	            System.out.println("Exiting.");
	        }
	    }
	  
	  
	  public static void handleConnection(Socket connection) throws IOException {
	

	        OutputStream outputStream = connection.getOutputStream();
			PrintWriter outgoing = new PrintWriter( outputStream );
	        String rootDirectory = "/Users/renaldo/Downloads";           // root path for test.
	        String pathToFile = "/index.html";                 // root path for test.
	        File file = new File(rootDirectory + pathToFile);  // root path for test.

			try {
				int lineCnt = 0;
				Scanner in = new Scanner(connection.getInputStream());
				
				while (true) {
					if ( ! in.hasNextLine() )
						break;
					
					String line = in.nextLine();
					if (lineCnt == 0) {
						String[] line1 = line.split(" ");
						if (!line1[0].equals("GET") ) {
							 sendErrorResponse( 501, outputStream); 
							 break;
						}
						file = new File(line1[1]);
						pathToFile = line1[1];
					}
					
					
					if (line.trim().length() == 0)
						break;
					System.out.println("   " + line);
					lineCnt = lineCnt +  1;
				}
				

				if (file.exists()      == true && 
			        file.isDirectory() != true && 
			        file.canRead()     == true) {
					
					outgoing.print("HTTP/1.1 " + 200+ " OK " + "\r\n");
					outgoing.print("Connection: close" + "\r\n");
					outgoing.print("Content-Type: " + getMimeType(pathToFile)+ "\r\n" );
					outgoing.print("Content-Length: " + file.length()+ "\r\n" );
					outgoing.print("\r\n");
					outgoing.flush();
					sendFile(file, outputStream);
//		      		System.out.print("HTTP/1.1 " + file.length()+ " OK " + "\r\n"); 
			    }
		        //Write the outputStream			
				else {
		            // error 404
					 System.out.println("Error 404 not found ");
					 sendErrorResponse( 404, outputStream); 
		        }
			
				connection.close();  
			}
			catch (Exception e) {
				System.out.println("Error while communicating with client: " + e);
				return;
			}
			finally {  // make SURE connection is closed before returning!
				try {
					connection.close();
				}
				catch (Exception e) {
				}
				System.out.println("Connection closed.");
			}
			
			
		  
	  }
	  private static class ConnectionThread extends Thread {
	         Socket connection;
	         ConnectionThread(Socket connection) {
	            this.connection = connection;
	         }
	         public void run() {
	            try {
					handleConnection(connection);
				} catch (IOException e) {
					e.printStackTrace();
				}
	         }
	      }
	  
	  public static String readFile(String filePath) {
		    try {
		        BufferedReader reader = new BufferedReader(new FileReader(filePath));
		        StringBuilder content = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            content.append(line);
		        }
		        reader.close();
		        return content.toString();
		    }
		    catch (Exception e) {
		        System.out.println("Error while reading file: " + e);
		        return "File not found or could not be read.";
		    }
		}
	  private static String getMimeType(String fileName) {
          int pos = fileName.lastIndexOf('.');
          if (pos < 0)  // no file extension in name
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
             // Note:  x-application/x-unknown  is something made up;
             // it will probably make the browser offer to save the file.
       }
	  private static void sendFile(File file, OutputStream socketOut) throws
	  IOException {
	    InputStream in = new BufferedInputStream(new FileInputStream(file));
	    OutputStream out = new BufferedOutputStream(socketOut);
	    while (true) {
	      int x = in.read(); // read one byte from file
	      if (x < 0)
	         break; // end of file reached
	      out.write(x);  // write the byte to the socket
	   }
	   out.flush();
	}
	  static void sendErrorResponse(int errorCode, OutputStream socketOut) {
		  String str = "";
		  PrintWriter outgoing = new PrintWriter(socketOut);
		  // error 404
		  
		  if (errorCode == 404) {
			  str = "<html><head><title>Error</title></head><body>"
			         + "<h2>Error: 404 Not Found</h2>"
			         + "<p>The resource that you requested does not exist on this server.</p>"
			         + "</body></html>" + "\r\n";
		  
		
			outgoing.print("HTTP/1.1 " + 404+ " NOT FOUND" + "\r\n");

	  }
		  else if (errorCode == 501){
			  
			   str = "<html><head><title>Error</title></head><body>"
				         + "<h2>Error: 501 Not Implemented</h2>"
				         + "<p>The server either does not recognize the request method, or it lacks the ability to fulfill the request.</p>"
				         + "</body></html>" + "\r\n";
			   outgoing.print("HTTP/1.1 " + 501+ " NOT Implemented" + "\r\n");
		  }
		  
		  
		  
			outgoing.print("Connection: close" + "\r\n");
			outgoing.print("Content-Type: " + "text/html" + "\r\n" );
			outgoing.print("Content-Length: " + str.length()+ "\r\n" );
			outgoing.print("\r\n");
            outgoing.println( str + "\r\n");
            outgoing.flush();
	  }
}


