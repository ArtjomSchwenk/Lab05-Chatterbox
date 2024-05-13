package zuul;

import src.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ZuulServer {
    int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Game game;
    State sender;
    State receiver;

    public ZuulServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            game = new Game(in, out);
            game.play();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    public void stopServer() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZuulServer zs = new ZuulServer(1234);
        zs.startServer();
    }
}

