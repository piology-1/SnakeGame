import javax.swing.JFrame;

public class GameWindow extends JFrame {
    // Constants for window dimensions [px]
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public GameWindow(Board gameBoard) {
        super("Snake Game");

        // Add the game board to the window
        add(gameBoard);

        // Adjust the window size to fit the game board
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen AFTER board is added
        setLocationRelativeTo(null);

        // Make the window non-resizable
        setResizable(false);
    }
}
