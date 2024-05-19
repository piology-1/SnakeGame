import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake implements KeyListener {
    public static final int START_X = 5;
    public static final int START_Y = 5;

    private int xVelocity = 0;
    private int yVelocity = 0;

    private List<SnakeBodyPart> bodyParts;

    public Snake() {
        bodyParts = new ArrayList<>();
        bodyParts.add(new SnakeBodyPart(START_X * GridComponent.SIZE, START_Y * GridComponent.SIZE));
    }

    public SnakeBodyPart getHead() {
        return bodyParts.get(0);
    }

    public List<SnakeBodyPart> getBodyParts() {
        return Collections.unmodifiableList(bodyParts);
    }

    public void draw(Graphics g) {
        for (SnakeBodyPart part : bodyParts) {
            part.draw(g);
        }

        var head = bodyParts.get(0);
        head.draw(g, Color.WHITE);
    }

    public void addBodyPartAtTail() {
        SnakeBodyPart tail = bodyParts.get(bodyParts.size() - 1);
        bodyParts.add(new SnakeBodyPart(tail.getX(), tail.getY()));
    }

    public void move() {
        // Move the body parts starting from the end to maintain proper order
        // Each body part takes the position of the one in front of it
        // This loop excludes the head, as it will be moved separately
        for (int i = bodyParts.size() - 1; i > 0; i--) {
            SnakeBodyPart current = bodyParts.get(i);
            SnakeBodyPart next = bodyParts.get(i - 1);
            current.setPosition(next.getX(), next.getY());
        }
        // Move the head according to the velocity
        // The head's position is updated after the body parts have been moved
        // This ensures that the head moves to the new position before the body parts
        // follow
        SnakeBodyPart head = bodyParts.get(0);
        head.setPosition(head.getX() + xVelocity * GridComponent.SIZE, head.getY() + yVelocity * GridComponent.SIZE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (xVelocity != 1) {
                    xVelocity = -1;
                    yVelocity = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (xVelocity != -1) {
                    xVelocity = 1;
                    yVelocity = 0;
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (yVelocity != 1) {
                    xVelocity = 0;
                    yVelocity = -1;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (yVelocity != -1) {
                    xVelocity = 0;
                    yVelocity = 1;
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}