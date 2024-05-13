package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//Part 3
class MalteMain implements Runnable {       // TODO: SWITCH "MALTEMAIN" TO "CHATTERBOX
    private final BlockingQueue<String> eingehendeNachricht = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> gesendeteNachricht = new LinkedBlockingQueue<>();

    public void receiveMessage(String message) {
        eingehendeNachricht.offer(message);
    }

    public void sendMessage(String message) {
        gesendeteNachricht.offer(message);
    }

    @Override
    public void run() {
        // Thread erhalten
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String receivedMessage = eingehendeNachricht.take();
                System.out.println("erhalten " + receivedMessage);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Thread senden
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String outgoingMessage = gesendeteNachricht.take();
                // Nachricht zum Server senden
                System.out.println("senden: " + outgoingMessage);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        MalteMain chatterbox = new MalteMain();     // TODO: SWITCH "MALTEMAIN" TO "CHATTERBOX
        Thread receivingThread = new Thread(chatterbox);
        Thread sendingThread = new Thread(chatterbox);

        receivingThread.start();
        sendingThread.start();

        // Beispiel
        chatterbox.receiveMessage("Hallo von User A");
        chatterbox.sendMessage("Hallo, User B");
    }
}
