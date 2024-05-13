package src;

import java.net.*;
import java.io.*;

public class ChatterboxClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        try{
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(IOException e) {}
    }

    public String sendMessage(String msg) throws IOException{
        out.println(msg);
        String resp = in.readLine();
        System.out.println("echo: " + resp);
        return resp;
    }

    public void stopConnection() throws IOException{
        stdIn.close();
        out.close();
        in.close();
        clientSocket.close();
    }
}
