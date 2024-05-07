import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int portNumber = 8080;
        String message = "Hello there";

        try {
            Socket socket = new Socket(serverAddress, portNumber);
            System.out.println("Connected to the server at " + socket.getInetAddress().getHostName() + " on port " + socket.getPort());
            Connection connection  = new Connection(socket);
            connection.write(message);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverAddress);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverAddress);
        }
    }
}
