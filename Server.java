import java.net.*;
import java.io.*;


public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = 8080;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is listening at " + serverSocket.getLocalSocketAddress());
            Socket socket = serverSocket.accept();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " /*+ serverAddress*/);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " /*+ serverAddress*/);
        }
    }
}
