package zuul;

public class RunServer {
    public static void main(String[] args) {
        ZuulServer server = new ZuulServer(1234);
        server.startServer();
    }
}
