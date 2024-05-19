import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

public class Board extends JPanel {
    private Food food;
    private Snake snake;
    private boolean showGrid = false;

    public Board(Food food, Snake snake) {
        this.food = food;
        this.snake = snake;

        setPreferredSize(new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        // Show dialog to ask user if they want to display the grid
        setShowGrid();

        // Add the Snake object as a KeyListener
        addKeyListener(snake);
    }

    public void reset(Food food, Snake snake) {
        this.food = food;
        this.snake = snake;

        // Show dialog to ask user if they want to display the grid
        setShowGrid();

        // Re-add the Snake as a KeyListener after reset
        addKeyListener(snake);

        repaint();
    }

    private void setShowGrid() {
        // Show dialog to ask user if they want to display the grid
        int option = JOptionPane.showConfirmDialog(null,
                "Do you want to display the grid?",
                "Grid Display",
                JOptionPane.YES_NO_OPTION);

        showGrid = (option == JOptionPane.YES_OPTION);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // override the 'paintComponent' method of the JPanel
        super.paintComponent(g);
        food.draw(g);
        snake.draw(g);
        drawScore(g);

        if (showGrid)
            drawGrid(g);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + snake.getBodyParts().size(), 10, 20);
    }

    private void drawGrid(Graphics g) {
        // set the color in which the grid should be drawn at
        g.setColor(Color.GRAY);

        // draw vertical lines
        for (int x = 0; x < GameWindow.WIDTH; x += GridComponent.SIZE) {
            g.drawLine(x, 0, x, GameWindow.HEIGHT);
        }

        // draw horizontal lines
        for (int y = 0; y < GameWindow.HEIGHT; y += GridComponent.SIZE) {
            g.drawLine(0, y, GameWindow.WIDTH, y);
        }
    }

    public void checkForSnakeOnFood() {
        SnakeBodyPart head = snake.getHead();

        if (head.x == food.x && head.y == food.y) {
            snake.addBodyPartAtTail();
            food.setRandomPosition();
        }
    }

    public boolean hasCollisionOccurred() {
        return hasCollidedWithWall() || hasCollidedWithSelf();
    }

    public boolean hasCollidedWithWall() {
        SnakeBodyPart head = snake.getHead();
        return head.getX() < 0 || head.getX() >= GameWindow.WIDTH || head.getY() < 0
                || head.getY() >= GameWindow.HEIGHT;
    }

    public boolean hasCollidedWithSelf() {
        SnakeBodyPart head = snake.getHead();
        List<SnakeBodyPart> snakeBody = snake.getBodyParts();

        // Start checking for collision from the third body part onwards
        // since collision with the head itself is not possible,
        // and the way we add body parts requires this method...
        for (int i = 2; i < snakeBody.size(); i++) {
            SnakeBodyPart part = snakeBody.get(i);
            if (head.getX() == part.getX() && head.getY() == part.getY()) {
                return true; // Collision detected
            }
        }

        return false; // No collision detected
    }

}
