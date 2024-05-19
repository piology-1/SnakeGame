import java.awt.Color;
import java.awt.Graphics;

public abstract class GridComponent {
    protected int x;
    protected int y;
    protected static final int SIZE = 25; // Size of each grid component (25x25 pixels)
    protected Color color;

    // Constructor with random position
    public GridComponent() {
        this.color = null;
    }

    // Constructor with specified position
    public GridComponent(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Abstract method to be implemented by subclasses
    public abstract void draw(Graphics g);
}
