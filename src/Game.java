import javax.swing.JOptionPane;

public class Game {
    private GameWindow window;
    private Board board;
    private Food food;
    private Snake snake;
    private boolean isRunning;
    private int speed = 100; // [ms]

    public Game() {
        food = new Food();
        snake = new Snake();
        board = new Board(food, snake);
        window = new GameWindow(board);
        isRunning = true;
        window.setVisible(true);
    }

    public void start() {
        // Start the game loop in a new thread
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while (isRunning) {

            snake.move();

            board.checkForSnakeOnFood();

            if (board.hasCollisionOccurred()) {
                endGame();
            }

            board.repaint();

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                System.err.println("Game loop interrupted: " + e.getMessage());
            }
        }
    }

    private void endGame() {
        isRunning = false;
        showGameOverScreen();
    }

    private void showGameOverScreen() {
        int option = JOptionPane.showConfirmDialog(window,
                "Game Over! Your score is: " + snake.getBodyParts().size() + "\nDo you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            restart();
        } else {
            System.exit(0);
        }
    }

    private void restart() {
        food = new Food();
        snake = new Snake();
        board.reset(food, snake);
        isRunning = true;
    }
}
