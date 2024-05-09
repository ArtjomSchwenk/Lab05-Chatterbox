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
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chatterbox server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create input stream to read messages from client
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received from client: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopServer() {

    }
}
