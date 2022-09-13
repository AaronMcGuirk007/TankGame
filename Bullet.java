import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * THe Bubble class that makes Bubbles! Which is used for the BubbleBlower
 * class.
 * 
 * @author Aaron McGuirk, Ethan Bartlett, Jason Macutek
 * @version Spring 2022
 */

public class Bullet extends Sprite {

    private Point startPoint;

    private String direction;

    private double xSpeed, ySpeed;

    private boolean done;

    private Player currPlayer;

    private Player tankLocation;

    private ArrayList<Collisions> cpArr = new ArrayList<>();

    private JComponent panel;

    private ArrayList<Target> targets;

    /**
     * Constructs a new Bullet
     * 
     * @param startPoint the point where the Bullet will be placed.
     * @param panel      the panel which the Bullet is placed on.
     */
    public Bullet(Point startPoint, JComponent panel, String direction, ArrayList<Target> targets, Player currPlayer, Player tankLocation) {
        super(panel);
        this.startPoint = startPoint;
        this.panel = panel;
        this.direction = direction;
        this.targets = targets;
        this.currPlayer = currPlayer;
        this.tankLocation = tankLocation;

        // Bullet speeds used for translation
        ySpeed = 0;
        xSpeed = 0;

        Point tankPoint = tankLocation.getPoint();
        // Creates the new Collision for each bullet fired
        for(int i = 0; i < targets.size(); i++){
            cpArr.add(new Collisions(startPoint, targets.get(i), tankPoint, panel, direction));
        }
        done = false;
    }

    /**
     * Draws the bullet 
     */
    public void paint(Graphics g) {

        // Paints the bullet on the screen
        g.setColor(Color.BLACK);
        g.fillRect(startPoint.x, startPoint.y, 7, 7);

        
    }

    /**
     * Returns the bullets done condition.
     * If the bullet is done it will not be drawn again.
     * 
     * @return done
     */
    public boolean done() {
        return done;
    }

    @Override
    public void run() {

        while (startPoint.x < 2560 && startPoint.x > -300 && startPoint.y < 1440 && startPoint.y > -300) {

            // every 10 ms or so, we move the coordinates of the bubble
            // by a few pixel to emulate wind
            try {
                sleep(33);
            } catch (InterruptedException e) {
            }

            if(direction.equals("Up")) {
                ySpeed -= 1;
                startPoint.translate((int) xSpeed, (int) ySpeed);
            } else if(direction.equals("Down")) {
                ySpeed += 1;
                startPoint.translate((int) xSpeed, (int) ySpeed);
            } else if(direction.equals("Left")) {
                xSpeed -= 1;
                startPoint.translate((int) xSpeed, (int) ySpeed);
            } else if(direction.equals("Right")) {
                xSpeed += 1;
                startPoint.translate((int) xSpeed, (int) ySpeed);
            }

            // This checks for bullet collisions with objects
            if(!cpArr.isEmpty()){
                for(int i = 0; i < cpArr.size(); i++){
                    if(cpArr.get(i).checkCollisions(currPlayer)){
                        done = true;
                        break;
                    }else{
                        panel.repaint();
                    }

                    if(cpArr.get(i).checkTankCollision(tankLocation)){
                        done = true;
                        break;
                    }else{
                        panel.repaint();
                    }
                }
            }
            
        }

        done = true;
        panel.repaint();
    }
}