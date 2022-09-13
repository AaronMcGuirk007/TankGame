import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

/**
 * This class is the Target class which is used for the collisions made with the
 * Bullet class
 * 
 * @author Aaron McGuirk, Ben McColgen, Adam Leonard, Ethan Bartlett, Jason
 *         Macutek
 * @version Spring 2022
 */

public class Target {

    protected final int radius = 25;
    protected int x, y, area;
    protected Point centerPoint;
    protected boolean done, pvpClass;
    private Player currPlayer;
    protected JComponent container;
    private int health;

    public Target(JComponent container, int width, int height) {
        // 1000 x 1000 gameboard size
        Random rand = new Random();
        if (width == 700 && height == 700) {
            this.x = rand.nextInt(width + 1);
            this.y = rand.nextInt(height + 1);
            this.area = (radius * 2) * (radius * 2);
            this.container = container;
            this.centerPoint = new Point(x, y);
            health = 100;
            done = false;
            pvpClass = true;
            container.repaint();
        } else {
            // 1000 x 1000 gameboard size
            this.x = rand.nextInt(width + 1);
            this.y = rand.nextInt(height + 1);
            this.area = (radius * 2) * (radius * 2);
            this.container = container;
            this.centerPoint = new Point(x, y);
            health = 100;
            done = false;
            container.repaint();
        }
    }

    public int getArea() {
        return this.area;
    }

    public Point getCenterPoint() {
        return this.centerPoint;
    }

    public void hitTarget(Player currPlayer) {
        this.currPlayer = currPlayer;
        if (health > 0) {
            health -= 25;
            container.repaint();
        } else {
            destroyTarget();
        }
    }

    public void destroyTarget() {
        done = true;
        container.repaint();
    }

    public boolean done() {
        return done;
    }

    public void paint(Graphics g) {
        if (done) {

            currPlayer.scorePoint();

        } else if (!pvpClass) {

            if (health == 100) {
                g.setColor(new Color(176, 255, 115));
            } else if (health == 75) {
                g.setColor(new Color(255, 90, 112));
            } else if (health == 50) {
                g.setColor(new Color(203, 42, 63));
            } else if (health == 25) {
                g.setColor(new Color(92, 4, 15));
            } else {
                destroyTarget();
            }

            g.fillRect(x - radius, y - radius, radius * 2, radius * 2);
        }
    }
}