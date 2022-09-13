import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * This class will create maps for the tank game to play on
 * 
 * @author Aaron McGuirk, Ethan Bartlett, Jason Macutek, Adam Leonard, Ben McColgen
 * @version Spring 2022
 */

public class Map {
    private JFrame frame;
    private int tilesize;
	private Dimension dimensions;
	private Point tankStartLoc;

	private Point tile;
	
	public static enum MAPS
	{
		MAP_1
	}
    
    private final int[][] MAP_1 = 
	{
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 6, 0, 1, 2, 0, 1, 2, 3, 0, 0, 0, 0, 1, 2, 0, 0, 0, 3, 3, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 4, 0, 0, 3, 1, 2, 0, 3, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 0, 0, 0, 0 },
		{ 0, 6, 6, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 0, 0, 0, 1, 2, 3, 0, 4, 0, 4 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 6, 0, 6 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 1, 2, 3, 0, 0, 0, 1, 2, 1, 2, 1, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 3, 3, 3, 0, 0, 4, 1, 2, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3 }
	};

	private int[][] map;
	
	private BufferedImage[][] imageMap;

	public Map(MAPS map)
	{
		tilesize = 84;
		
		dimensions = new Dimension(23, 13);
		
		tile = new Point();

		imageMap = new BufferedImage[dimensions.height][dimensions.width];
		
		switch (map)
		{
			case MAP_1:
			this.map = MAP_1;
			break;
	}
}
/* 
public void paint(Graphics g2d)
{
	for (int i = 0; i < dimensions.height; ++i)
	{
		for (int j = 0; j < dimensions.width; ++j)
		{
			g2d.drawImage(imageMap[i][j], j * tilesize, i * tilesize, Game.getInstance());
		}
	}
} */

public int[][] getMap()
{
	return map;
}
}