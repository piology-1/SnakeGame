import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food extends GridComponent {
    private static final Random RANDOM = new Random();

    public Food() {
        setRandomPosition();
        color = Color.RED;
    }

    public Food(int x, int y) {
        super(x, y);
        color = Color.RED;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void setRandomPosition() {
        x = RANDOM.nextInt(GameWindow.WIDTH / SIZE) * SIZE;
        y = RANDOM.nextInt(GameWindow.HEIGHT / SIZE) * SIZE;
    }
}
