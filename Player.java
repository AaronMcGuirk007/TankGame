import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Player {

    private int score;
    private int health;
    private Tank currTank;

    /**
     * Constructs a new player
     * 
     * @param currTank
     */
    public Player(Tank currTank) {
        this.currTank = currTank;
        score = 0;
        health = 100;
    }

    /**
     * This method increments the player score
     */
    public void scorePoint() {
        score += 1;
        System.out.println("Scored 1 point");
    }

    public void hit(){
        if (health >= 25) {
            health -= 25;
        } else {
            currTank.destroyTank();
        }
    }

    /**
     * This method returns the players score
     * 
     * @return score
     */
    public int getScore() {
        return score;
    }


    /**
     * This method returns the players health.
     * 
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * This method the x and y coordinates of the player in a point.
     * 
     * @return the Players (x,y) position
     */
    public Point getPoint() {
        return new Point(currTank.getX(), currTank.getY());
    }
}