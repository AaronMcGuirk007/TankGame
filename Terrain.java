import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * This class will paint the terrain on the map at a specified location
 * 
 * @author Aaron McGuirk, Ethan Bartlett, Jason Macutek, Adam Leonard, Ben McColgen
 * @version Spring 2022
 */

public class Terrain extends Sprite{

    private int size;

    private int x;

    private int y;

    private int[] xArr;
    
    private int[] yArr;

    private JComponent container;

    public Terrain(int x, int y, int size, JComponent container) {
        super(container);
        this.x = x;
        this.y = y;
        this.size = size;
    }

     /**
     * Draw the square on the given Graphics object.
     * 
     * @param g the Graphics object on which the Square should be drawn
     */
    public void paint(Graphics g) {
        drawSquare(x, y, size, g);
    }

    public void drawSquare(int x, int y, int size, Graphics g){
        g.fillRect(x, y, size,size);
    }

    public void drawOval(int x, int y, int size, Graphics g){
        g.fillOval(x, y, size, size);
    }

     public void drawPolygon(int[] xArr, int[] yArr, int points, Graphics g){
        g.fillPolygon(xArr, yArr, points);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

}
    

