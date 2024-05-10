package src;

public class RunServer {
    public static void main(String[] args){
        ChatterboxServer server = new ChatterboxServer(8080);
        server.startServer();
    }
}
