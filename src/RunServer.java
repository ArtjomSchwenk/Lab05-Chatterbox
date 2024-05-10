package src;

public class RunServer {
    public static void main(String[] args){
        ChatterboxServer server = new ChatterboxServer(1234);
        server.startServer();
    }
}
