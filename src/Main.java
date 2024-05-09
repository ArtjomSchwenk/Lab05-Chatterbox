package src;

public class Main {
    public static void main(String[] args) {
        ChatterboxServer server = new ChatterboxServer(1312);
        server.startServer();
    }
}
