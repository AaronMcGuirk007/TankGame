import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author 
 * @version Spring 2022
 */

public abstract class SpriteController implements Runnable {

    /** list of sprites currently on the screen */
    protected java.util.List<Sprite> list;

    /** the panel in which our graphics are drawn */
    protected JPanel panel;

    // these store the values passed to the constructor
    // for window label and size to be used when needed in
    // the run method that sets up the user interface
    protected String windowLabel;
    protected Dimension windowSize;

    /**
     * an object to serve as the lock for thread safety of our list
     * access
     */
    protected Object lock = new Object();

    // thisTGC is a copy of the this reference of this object
    // so we can access it in the paintComponent method (where
    // this would refer to the JPanel instead!)
    protected SpriteController thisSpriteController;

    /**
     * Constructor, which needs to take the size and name of the
     * window to create.
     * 
     * @param label Window label string
     * @param size  Size of the window to create
     */
    public SpriteController(String label, int width, int height) {

        windowLabel = label;
        windowSize = new Dimension(width, height);
        thisSpriteController = this;
    }

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        JFrame frame = new JFrame(windowLabel);
        frame.setPreferredSize(windowSize);

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                // first, we should call the paintComponent method we are
                // overriding in JPanel
                super.paintComponent(g);

                // call the method to draw any non-animated
                // objects such as background patterns, visual
                // feedback about dragging, etc.
                // we need to use thisSpriteController to get the reference
                // to the SpriteController since this
                // here will refer to the JPanel on which the
                // paintComponent is called
                thisSpriteController.paint(g);

                // redraw each sprite at its
                // current position, remove the ones that are done
                // along the way
                int i = 0;

                // since we will be modifying the list, we will
                // lock access in case any other code tries to
                // access the list
                synchronized (lock) {
                    while (i < list.size()) {
                        Sprite b = list.get(i);
                        if (b.done()) {
                            list.remove(i);
                        } else {
                            b.paint(g);
                            i++;
                        }
                    }
                }
            }
        };

        // the panel should be placed appropriately within the frame
        // by this method, so if anything further is needed such as
        // additional panels, buttons, etc., that can be accomplished
        // here by overriding the default implementation of buildGUI
        // below
        buildGUI(frame, panel);

        // we don't know here what mouse, keyboard, or other listeners
        // a derived class might want to add, so derived classes that
        // use listeners should override this method and set them
        addListeners(panel);

        // construct the list of Sprites
        list = new ArrayList<Sprite>();

        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Default implementation of the method that will draw any static
     * image needed in the window and any visual feedback needed for
     * event handling (like mouse press to mouse location "sling"
     * lines).
     * 
     * Derived classes should override this if such functionality is
     * needed.
     * 
     * @param g Graphics object in which to draw.
     */
    protected void paint(Graphics g) {

    }

    /**
     * Default implementation of the method that will add listeners
     * at the appropriate time during creation of the window.
     * 
     * Derived classes should override this if such functionality is
     * needed.
     * 
     * @param p the JPanel to which any mouse or keyboard listeners
     *          should be attached
     */
    protected void addListeners(JPanel p) {
        
    }

    /**
     * Default implementation of the method that will connect the
     * given frame, which represents the whole window and the panel,
     * which is where graphics will be drawn and mouse events
     * delivered. If additional components are used, they can be set
     * up here. The default implementation simply adds the panel to
     * the frame.
     * 
     * Derived classes should override this if such functionality is
     * needed.
     * 
     * @param frame the JFrame to which components ultimately need to
     *              be added
     * @param panel the JPanel where graphics will be drawn that needs
     *              to be added somewhere in the GUI hierarchy
     */
    protected void buildGUI(JFrame frame, JPanel panel) {

        frame.add(panel);
    }

}
