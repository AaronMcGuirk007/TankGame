import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.html.HTML.Tag;

public class Collisions extends Rectangle {

    private Point bulletLoc;
    private Point targetLoc;
    private Point tankLoc;
    private Target mainTarget;
    private String direction;

    private Rectangle currBullet;
    private Rectangle currTarget;
    private Rectangle currTank;

    private JComponent panel;

    // Collision constructor for Target Objects
    //
    public Collisions(Point bulletLoc, Target mainTarget, Point tankLoc, JComponent panel, String direction) {
        // do something
        this.bulletLoc = bulletLoc;
        this.targetLoc = mainTarget.getCenterPoint();
        this.mainTarget = mainTarget;
        this.tankLoc = tankLoc;
        this.direction = direction;
        this.panel = panel;

        currBullet = new Rectangle(bulletLoc.x, bulletLoc.y, 7, 7);
        currTarget = new Rectangle(targetLoc.x - 25, targetLoc.y - 25, 100, 100);
        currTank = new Rectangle(tankLoc.x, tankLoc.y, 90, 90);
    }

    /**
     * Checks to see if a bullet has collided with a collisionbox
     * @param currPlayer
     * @return if collided with something
     */
    public boolean checkCollisions(Player currPlayer) {
        // Sets the current location of the bullet to a temp variable.
        currBullet.setLocation(bulletLoc);

        // Sets isHit to false unless provoked
        boolean isHit = false;

        // The point locations of the current target
        Point topLeft = new Point(currTarget.x, currTarget.y);
        Point topRight = new Point(currTarget.x + 50, currTarget.y);
        Point bottomLeft = new Point(currTarget.x, currTarget.y + 50);
        Point bottomRight = new Point(currTarget.x + 50, currTarget.y + 50);
        
        // Prints out information for bullet tracking
        System.out.println("Bullet Location X: " + currBullet.x + " Y: " + currBullet.y);
        System.out.println("Target Location X:");
        System.out.println(topLeft);
        System.out.println(topRight);
        System.out.println(bottomLeft);
        System.out.println(bottomRight);

        // Checks for bullet collision on the top of the target
        if (direction.equals("Down")) {
            if (currBullet.x >= topLeft.x && currBullet.x <= topRight.x) {
                if (currBullet.y >= topLeft.y && currBullet.y <= bottomRight.y) {
                    isHit = true;
                    System.out.println("Target hit");
                    mainTarget.hitTarget(currPlayer);
                }
            }
        }

        // Checks for bullet collision on the right side of the target
        if (direction.equals("Left")) {
            if (currBullet.y >= topLeft.y && currBullet.y <= bottomLeft.y) {
                if (currBullet.x >= topLeft.x && currBullet.x <= bottomRight.x) {
                    isHit = true;
                    System.out.println("Target hit");
                    mainTarget.hitTarget(currPlayer);
                }
            }
        }

        // Checks for bullet collision on the bottom of the target
        if (direction.equals("Up")) {
            if (currBullet.x >= bottomLeft.x && currBullet.x <= bottomRight.x) {
                if (currBullet.y <= bottomLeft.y && currBullet.y >= topRight.y) {
                    isHit = true;
                    System.out.println("Target hit");
                    mainTarget.hitTarget(currPlayer);
                }
            }
        }

        // Checks for bullet collision on the left side of the target
        if (direction.equals("Right")) {
            if (currBullet.y >= topLeft.y && currBullet.y <= bottomLeft.y) {
                if (currBullet.x >= topLeft.x && currBullet.x <= topRight.x) {
                    isHit = true;
                    System.out.println("Target hit");
                    mainTarget.hitTarget(currPlayer);
                }
            }
        }

        return isHit;
    }

    /**
     * Checks to see if a tank has been hit by a bullet
     * 
     * @param enemy tank that we are checking to see if they have been hit.
     */
    public boolean checkTankCollision(Player enemy) {
        // Sets the current location of the bullet to a temp variable.
        currBullet.setLocation(bulletLoc);

        // Sets isHit to false unless provoked
        boolean isHit = false;

        // The point locations of the current target
        Point topLeft = new Point(currTank.x, currTank.y);
        Point topRight = new Point(currTank.x + 90, currTank.y);
        Point bottomLeft = new Point(currTank.x, currTank.y + 90);
        Point bottomRight = new Point(currTank.x + 90, currTank.y + 90);
        
        // Prints out information for bullet tracking
        System.out.println("Bullet Location X: " + currBullet.x + " Y: " + currBullet.y);
        System.out.println("Target Location X:");
        System.out.println(topLeft);
        System.out.println(topRight);
        System.out.println(bottomLeft);
        System.out.println(bottomRight);

        // Checks for bullet collision on the top of the target
        if (direction.equals("Down")) {
            if (currBullet.x >= topLeft.x && currBullet.x <= topRight.x) {
                if (currBullet.y >= topLeft.y && currBullet.y <= bottomRight.y) {
                    isHit = true;
                    System.out.println("Target hit");
                    enemy.hit();
                }
            }
        }

        // Checks for bullet collision on the right side of the target
        if (direction.equals("Left")) {
            if (currBullet.y >= topLeft.y && currBullet.y <= bottomLeft.y) {
                if (currBullet.x >= topLeft.x && currBullet.x <= bottomRight.x) {
                    isHit = true;
                    System.out.println("Target hit");
                    enemy.hit();
                }
            }
        }

        // Checks for bullet collision on the bottom of the target
        if (direction.equals("Up")) {
            if (currBullet.x >= bottomLeft.x && currBullet.x <= bottomRight.x) {
                if (currBullet.y <= bottomLeft.y && currBullet.y >= topRight.y) {
                    isHit = true;
                    System.out.println("Target hit");
                    enemy.hit();
                }
            }
        }

        // Checks for bullet collision on the left side of the target
        if (direction.equals("Right")) {
            if (currBullet.y >= topLeft.y && currBullet.y <= bottomLeft.y) {
                if (currBullet.x >= topLeft.x && currBullet.x <= topRight.x) {
                    isHit = true;
                    System.out.println("Target hit");
                    enemy.hit();
                }
            }
        }

        return isHit;
    }



}