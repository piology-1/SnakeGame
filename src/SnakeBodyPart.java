import java.awt.Color;
import java.awt.Graphics;

public class SnakeBodyPart extends GridComponent {

    public SnakeBodyPart(int x, int y) {
        super(x, y);
        color = Color.GREEN;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fill3DRect(x, y, SIZE, SIZE, true);
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fill3DRect(x, y, SIZE, SIZE, true);
    }
}
