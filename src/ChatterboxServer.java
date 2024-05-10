package src;

import java.net.*;
import java.io.*;

public class ChatterboxServer {
    int port;
    private PrintWriter out;
    private BufferedReader in;

    public ChatterboxServer(int port) {
        this.port = port;
    }

    public void startServer(){
        // TASK 01
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chatterbox server listening on port " + port);

            while (true) {
                System.out.println("Listening...");
                Socket clientSocket = serverSocket.accept();    // waits for connection to be made

                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // input stream to read messages from client
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()
                        )
                    )
                ) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received from client: " + message);
                        sendMessage("Message received", clientSocket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg, Socket clientSocket){
        try { out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(msg);}
        catch(IOException e) {}
    }

    public void stopServer() {

    }
}
