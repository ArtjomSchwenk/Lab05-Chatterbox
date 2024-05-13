package zuul;

import zuul.commands.Command;
import zuul.commands.Parser;
import zuul.world.World;
import zuul.world.persistence.WorldAdapter;
import java.io.*;

/**
 * This class is the main class of the "World of Zuul" application.
 */
public class Game {
    private Parser parser;
    private GameStatus gameStatus;
    private World world;
    private BufferedReader input; // Use BufferedReader directly
    private PrintWriter output;   // Use PrintWriter directly

    /**
     * Constructor initializes the game with input and output streams.
     */
    public Game(BufferedReader inputReader, PrintWriter outputWriter) {
        this.input = inputReader;
        this.output = outputWriter;
        createRooms("data/zuul.yml");
        parser = new Parser();
    }

    /**
     * Main game loop: reads input, processes commands, and outputs results.
     */
    public void play() {
        printWelcome();

        try {
            String commandLine;
            while (gameStatus.isPlaying() && (commandLine = input.readLine()) != null) {
                Command command = parser.getCommand(commandLine);
                String response = command.process(gameStatus);
                output.println(response);
            }
        } catch (IOException e) {
            output.println("An error occurred while trying to read your command.");
        }
    }

    /**
     * Prints the initial welcome message.
     */
    private void printWelcome() {
        output.println();
        output.println(world.getDescription());
        output.println(gameStatus.getLocationDescription());
        output.println();
    }

    /**
     * Initializes the game world from a file.
     */
    private void createRooms(String worldFileName) {
        world = new WorldAdapter().readFromFile(worldFileName);
        gameStatus = new GameStatus(world.getStartRoom());
    }
}
