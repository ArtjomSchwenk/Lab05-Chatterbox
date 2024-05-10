package src;

import java.io.*;

public class RunClient {
    public static void main(String[] args){
        ChatterboxClient client = new ChatterboxClient();
        try {
            client.startConnection("localhost", 1234);
            client.sendMessage("Hello, server!");
            // client.stopConnection();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            System.out.println(e);
        }
    }
}
