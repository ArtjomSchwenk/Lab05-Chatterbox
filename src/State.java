package src;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class State implements Runnable {
    private final BlockingQueue<String> eingehendeNachricht = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> gesendeteNachricht = new LinkedBlockingQueue<>();
    private final boolean isSender;

    public State(boolean isSender) {
        this.isSender = isSender;
    }

    public void receiveMessage(String message) {
        eingehendeNachricht.offer(message);
    }

    public void sendMessage(String message) {
        gesendeteNachricht.offer(message);
    }

    @Override
    public void run() {
        if (isSender) {
            // Thread senden
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String outgoingMessage = gesendeteNachricht.take();
                    System.out.println("senden: " + outgoingMessage);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } else {
            // Thread erhalten
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String receivedMessage = eingehendeNachricht.take();
                    System.out.println("erhalten " + receivedMessage);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /*
    public static void main(String[] args) {
        Chatterbox receiver = new Chatterbox(false);
        Chatterbox sender = new Chatterbox(true);

        Thread receivingThread = new Thread(receiver);
        Thread sendingThread = new Thread(sender);

        receivingThread.start();
        sendingThread.start();

        // Beispiel
        receiver.receiveMessage("Hallo von User A");
        sender.sendMessage("Hallo, User B");
    }*/
}
