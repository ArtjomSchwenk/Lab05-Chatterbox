package prelab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketReader {

    /**
     * Reads data from a socket connected to the specified IP address and port.
     * @param ipAddress The IP address of the server to connect to.
     * @param port The port number on which the server is listening.
     * @return The response from the server as a string.
     * @throws IOException If an I/O error occurs when creating or accessing the socket.
     */

    public static String readFromSocket(String ipAddress, int port) throws IOException {
        try (Socket socket = new Socket(ipAddress, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line + "\n");
            }

            return response.toString();
        }
    }

    // Example usage
    public static void main(String[] args) {
        try {
            String ipAddress = "127.0.0.1"; // IP address of the server
            int port = 12345; // Port number the server is listening on
            String result = readFromSocket(ipAddress, port);
            System.out.println("Received from server: " + result);
        } catch (IOException e) {
            System.err.println("Error reading from socket: " + e.getMessage());
        }
    }
}
