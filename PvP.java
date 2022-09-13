import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Graphics;

/**
 * Description of game class _____.
 * 
 * @author Aaron McGuirk, Ethan Bartlett, Jason Macutek, Adam Leonard, Ben
 *         McColgen
 * @version Spring 2022
 */

public class PvP extends SpriteController implements KeyListener {

	// Delay for the animations
	public static final int DELAY_TIME = 33;

	// list of Sprites
	private java.util.List<Sprite> list;

	private java.util.List<Tank> tanks;

	private ArrayList<Bullet> bullets = new ArrayList<>();
	private ArrayList<Target> targets = new ArrayList<>();

	private int curr, curr2;

	private Bullet newBullet;
	private Player p1, p2;
	private JLabel playerOne, playerTwo, p1HealthBar, p2HealthBar;

	// The tanks
	public Tank tank1;
	public Tank tank2;

	// Terrain background
	private Image background = Toolkit.getDefaultToolkit().getImage("gifs/pvpBackImg.gif");

	// The targets
	// private Target target1;

	// the container of the game
	public JPanel container;

	// list of booleans to track keyPresses
	boolean[] keys = new boolean[255];

	// an object to serve as the lock for thread safety of our list access
	private Object lock = new Object();

	public PvP() {

		super("Tanks", 700, 700);

	}

	/*
	 * 
	 * 
	 * /**
	 * Draw the Tanks and targets
	 * 
	 * @param g the Graphics object in which to draw
	 */
	@Override
	protected void paint(Graphics g) {

		// draws background
		g.drawImage(background, 0, 0, null);
		
		tank1.paint(g);
		tank2.paint(g);
		updateHealth();
		// terrain.paint(g);

		int j = 0;
		while (j < targets.size()) {
			Target t = targets.get(j);
			if (t.done()) {
				t.paint(g);
				targets.remove(j);
				updateScore();
			} else {
				t.paint(g);
				j++;
			}
		}


		// Paints and removes the bullets
		int i = 0;
		while (i < bullets.size()) {
			Bullet b = bullets.get(i);
			if (b.done()) {
				b.stop();
				bullets.remove(i);
			} else {
				b.paint(g);
				i++;
			}
		}
	}

	/**
	 * Add the mouse listeners to the panel. Here, we need methods
	 * from both MouseListener, as the MouseMotionListener will be
	 * the BreakoutPaddle.
	 * 
	 * @param p the JPanel to which the mouse listeners will be
	 *          attached
	 */
	@Override
	protected void addListeners(JPanel panel) {

		panel.addKeyListener(this);
	}

	/**
	 * Add the panel to the frame, and set up additional components like the tanks and targets, also setting up
	 * a repaint thread.
	 * 
	 * @param frame     the JFrame to which the panel is added
	 *                  be added
	 * @param container the JPanel where graphics will be drawn
	 */
	protected void buildGUI(JFrame frame, JPanel container) {

		tank1 = new Tank(new Point(100, 100), container, 0);
		tank2 = new Tank(new Point(600, 600), container, 1);
		p1 = new Player(tank1);
		p2 = new Player(tank2);
		playerOne = new JLabel("Player 1 Score: " + p1.getScore());
		playerTwo = new JLabel("Player 2 Score: " + p2.getScore());
		p1HealthBar = new JLabel("Health: " + p1.getHealth());
		p2HealthBar = new JLabel("Health: " + p2.getHealth());
		container.add(playerOne);
		container.add(playerTwo);
		container.add(p1HealthBar);
		container.add(p2HealthBar);

		//initializes the variables
		for (int i = 0; i < 1; i++) {
			targets.add(new Target(container, 700, 700));
		}

		frame.addKeyListener(this);
		frame.add(container);

		// repaint regularly forever thread
		new Thread() {
			@Override
			public void run() {
				while (true) {
					Sprite.sleepWithCatch(DELAY_TIME);
					container.repaint();
				}

			}
		}.start();
	}

	/**
	 * This method updates the playerscore labels.
	 */
	public void updateScore() {
		playerOne.setText("Player 1 Score: " + p1.getScore());
		playerTwo.setText("Player 2 Score: " + p2.getScore());
	}

	/**
	 * This method updates the healthbars of the tanks
	 */
	public void updateHealth() {
		if(tank1.done()) {
			p1HealthBar.setVisible(false);
		}
		if(tank2.done()) {
			p2HealthBar.setVisible(false);
		}
		p1HealthBar.setText("Health: " + p1.getHealth());
		p2HealthBar.setText("Health: " + p2.getHealth());
		p1HealthBar.setLocation(tank1.getX() + 14, tank1.getY() - 35);
		p2HealthBar.setLocation(tank2.getX() + 14, tank2.getY() - 35);
	}

	/**
	 * Tank movement and firing method
	 * Player 1 movement:
	 * W
	 * ASD
	 * Player 1 Fire
	 * G
	 * 
	 * Tank2m movement:
	 * ^
	 * <V>
	 * Tank2 Fire
	 * L
	 *
	 * @param e mouse event info
	 */
	public void keyPressed(KeyEvent e) {
		Point currLoc = new Point(tank1.getX(), tank1.getY());
		Point currLoc2 = new Point(tank2.getX(), tank2.getY());

		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank1.moveUp();
			curr = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			tank1.moveDown();
			curr = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			tank1.moveLeft();
			curr = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			tank1.moveRight();
			curr = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_G && !tank1.done()) {

			// This sets the correct image for the rotation of the tank
			if (curr == 0) {
				Point temp = new Point(currLoc.x + 41, currLoc.y - 5);
				newBullet = new Bullet(temp, panel, "Up", targets, p1, p2);
				bullets.add(newBullet);
				newBullet.start();
			} else if (curr == 1) {
				Point temp = new Point(currLoc.x + 41, currLoc.y + 90);
				newBullet = new Bullet(temp, panel, "Down", targets, p1, p2);
				bullets.add(newBullet);
				newBullet.start();
			} else if (curr == 2) {
				Point temp = new Point(currLoc.x - 5, currLoc.y + 41);
				newBullet = new Bullet(temp, panel, "Left", targets, p1, p2);
				bullets.add(newBullet);
				newBullet.start();
			} else if (curr == 3) {
				Point temp = new Point(currLoc.x + 90, currLoc.y + 41);
				newBullet = new Bullet(temp, panel, "Right", targets, p1, p2);
				bullets.add(newBullet);
				newBullet.start();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			tank2.moveUp();
			curr2 = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			tank2.moveDown();
			curr2 = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			tank2.moveLeft();
			curr2 = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			tank2.moveRight();
			curr2 = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_L && !tank2.done()) {

			// This sets the correct image for the rotation of the tank
			if (curr2 == 0) {
				Point temp2 = new Point(currLoc2.x + 41, currLoc2.y - 5);
				newBullet = new Bullet(temp2, panel, "Up", targets, p2, p1);
				bullets.add(newBullet);
				newBullet.start();
			} else if (curr2 == 1) {
				Point temp2 = new Point(currLoc2.x + 41, currLoc2.y + 90);
				newBullet = new Bullet(temp2, panel, "Down", targets, p2, p1);
				bullets.add(newBullet);
				newBullet.start();
			} else if (curr2 == 2) {
				Point temp2 = new Point(currLoc2.x - 5, currLoc2.y + 41);
				newBullet = new Bullet(temp2, panel, "Left", targets, p2, p1);
				bullets.add(newBullet);
				newBullet.start();
			} else if (curr2 == 3) {
				Point temp2 = new Point(currLoc2.x + 90, currLoc2.y + 41);
				newBullet = new Bullet(temp2, panel, "Right", targets, p2, p1);
				bullets.add(newBullet);
				newBullet.start();
			}
		}
		//container.repaint();
		// trigger paint so we can see the tank in its new location
	}

	// unused method to satisfy the interfaces.
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String args[]) {

		// The main method is responsible for creating a thread (more
		// about those later) that will construct and show the graphical
		// user interface.
		//javax.swing.SwingUtilities.invokeLater(new Game());
	}
}
