package src;

import java.io.*;

public class RunClient {
    public static void main(String[] args){
        ChatterboxClient client = new ChatterboxClient();
        try {
            client.startConnection("localhost", 1234);
            client.sendMessage("CLIENT CONNECTED");
            while(true){
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String userInput = stdIn.readLine();
                if(userInput.equals("exit")){
                    client.stopConnection();
                    break;
                }
                client.sendMessage(userInput);
            }
            // client.stopConnection();
        }
        catch(IOException e){
            System.out.println("An error occured!");
            System.out.println(e);
        }
    }
}
