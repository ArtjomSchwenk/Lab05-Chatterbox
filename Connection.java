import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
    private Socket socket;

    public Connection(Socket socket){
        this.socket = socket;
    }

    public void write(String message) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        // Convert the string to bytes
        byte[] byteArray = message.getBytes();

        // Write the length of the message first
        outputStream.write(byteArray.length);
        outputStream.write(byteArray);
        outputStream.flush();
    }
}
