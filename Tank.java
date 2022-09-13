import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This Tank class is responsible for managing the movement and health of the
 * tank(s)
 * given its start location and moves by what key is being pressed at the time
 */

public class Tank extends Sprite {
    // Movement speed, delay time between frames of animation, and the size of the
    // tank
    //private static final int MOVE_SPEED = 30;
    public static final int DELAY_TIME = 33;
    public static final int SIZE = 20;

    // Contains the filenames for the Animated Pictures
    final String[] gTP = { "gifs/GreenTankUp.gif", "gifs/GreenTankDown.gif", "gifs/GreenTankLeft.gif",
            "gifs/GreenTankRight.gif" };
    final String[] rTP = { "gifs/RedTankUp.gif", "gifs/RedTankDown.gif", "gifs/RedTankLeft.gif",
            "gifs/RedTankRight.gif" };

    Image image = null;
    Image explosion = null;
    Image crater = null;

    // latest location of the tank
    private Point centerPoint;
    private double upperLeftX, upperLeftY;
    // max coordinates the tank can be
    private int xMax, yMax;
    // health the tank has
    private int health;

    // Tank Color (0 = Green , 1 = Red)
    private int tankColor;

    private int lastPosX;
    private int lastPosY;;

    // Current Tank Pic
    private String currP;

    // has the tanks health reached zero?
    private boolean done;
    // Booleans for when the tank is moving in a certain direction(Up, Down, Left,
    // Right)
    private boolean movingUp, movingRight, movingDown, movingLeft;
    // Where the tank is being drawn
    private JComponent container;

    private int xSpeed, ySpeed = 2;

    /**
     * Constructs a new tank
     * 
     * @param startCenter the initial point at which the center of the tank will be
     *                    drawn
     * @param container   Swing component in which this ball is being drawn to allow
     *                    for
     *                    a call to the components repaint method
     */
    public Tank(Point startCenter, JComponent container, int tankColor) {
        super(container);
        centerPoint = startCenter;
        upperLeftX = startCenter.x - SIZE / 2;
        upperLeftY = startCenter.y - SIZE / 2;
        this.yMax = container.getHeight() - SIZE;
        this.xMax = container.getWidth() - SIZE;
        this.container = container;
        this.tankColor = tankColor;

        if (tankColor == 0) {
            currP = gTP[0];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        } else {
            currP = rTP[0];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        }
        explosion = Toolkit.getDefaultToolkit().getImage("gifs/Explosion.gif");
        crater = Toolkit.getDefaultToolkit().getImage("Crater.png");
        health = 3;
    }

    public void paint(Graphics g) {
        if (done) {
            g.drawImage(explosion, (int) lastPosX, (int) lastPosY - 15, null);
            //g.drawImage(crater, (int) upperLeftX, (int) upperLeftY - 15, null);
        } else {
            g.drawImage(image, (int) upperLeftX, (int) upperLeftY, null);
        }

    }

    public int getX() {
        return (int) upperLeftX;
    }

    public int getY() {
        return (int) upperLeftY;
    }

    public void run() {
    }

    /**
     * Returns dead if the tanks health is zero
     *
     * @return Returns true if the tanks health is zero, false otherwise
     */
    public boolean done() {
        return done;
    }

    /**
     * Method that delays by 33ms
     */
    protected void sleep() {
        try {
            sleep(DELAY_TIME);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Movement methods that determine what direction the tank is moving
     * 
     * @return true if the direction of the key is being pressed
     */
    public void moveUp() {
        upperLeftY -= 10;

        if (tankColor == 0) {
            currP = gTP[0];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        } else {
            currP = rTP[0];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        }

        container.repaint();
    }

    public void moveRight() {
        upperLeftX += 10;

        if (tankColor == 0) {
            currP = gTP[3];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        } else {
            currP = rTP[3];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        }

        container.repaint();

    }

    public void moveDown() {
        upperLeftY += 10;

        if (tankColor == 0) {
            currP = gTP[1];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        } else {
            currP = rTP[1];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        }

        container.repaint();

    }

    public void moveLeft() {
        upperLeftX -= 10;

        if (tankColor == 0) {
            currP = gTP[2];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        } else {
            currP = rTP[2];
            image = Toolkit.getDefaultToolkit().getImage(currP);
        }

        container.repaint();

    }

    public void destroyTank() {
        done = true;
        lastPosX = (int) upperLeftX;
        lastPosY = (int ) upperLeftY;
        container.repaint();
    }

    /**
     * Checks to see if a bullet is inside of the tank
     *
     * @param bullet the bullet that is being checked to see if it is inside the
     *               tank
     * @return true if the bullet has collided with the tank, false otherwise
     */
    public boolean contains(Point bullet) {
        if (bullet.x > upperLeftX && bullet.x < upperLeftX + SIZE && bullet.y > upperLeftY
                && bullet.y < upperLeftY + SIZE) {
            return true;
        }
        return false;
    }

    /**
     * Method to be called for when the tank gets hit, the health will decrease
     */
    public void decreaseHealth() {
        if (health != 0) {
            health--;
        }

        if (health == 0) {
            done = true;
        }
    }
}