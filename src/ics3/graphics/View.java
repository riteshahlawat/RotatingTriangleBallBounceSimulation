package ics3.graphics;

import javax.swing.*;


/**
 * view class where the drawing of the frame and interactions of them is located
 */
public class View {
    // imports JFrame to create the frame
    protected JFrame frame;
    // imports all Images
    protected Image backgroundImage;
    // Frame Size
    public static final int FRAME_WIDTH = Triangle_Bounce.FRAME_WIDTH;
    public static final int FRAME_HEIGHT = Triangle_Bounce.FRAME_HEIGHT;
    // the pane size (where components are drawed on the frame)
    public static double paneWidth;
    public static double paneHeight;
    // All invalid insests that we will need to take in mind when dealing with drawing

    /**
     * Default constructor which initializes all the classes and frame variables
     */
    public View() {
        // constructor where we initialize the classes
        frame = new JFrame("Triangle_Bounce");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setLayout(null);
        paneWidth = frame.getContentPane().getWidth();
        paneHeight = frame.getContentPane().getHeight();
        backgroundImage = new Image("data\\bg.jpeg", 0, 0, paneWidth, paneHeight);

    }

    /**
     * runs all initial GUI to set up the components (will be ran once)
     */
    public void runAllGUI() {
        initializeUserInterface();
        placeFrameComponents();
    }


    /**
     * initializes the frame
     */
    private void initializeUserInterface() {
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * adds all the components to the screen and makes them able to be rendered
     * and drawn onto the screen
     */
    private void placeFrameComponents() {
        frame.add(Triangle_Bounce.model.ball);
        Triangle_Bounce.model.ball.setVisible(true);
        for (int i = 0; i < 3; i++) {
            frame.add(Triangle_Bounce.model.triangle.lines[i]);
            Triangle_Bounce.model.triangle.lines[i].setVisible(true);
        }
        frame.add(backgroundImage);
        backgroundImage.setVisible(true);
        frame.addKeyListener(Triangle_Bounce.controller);
    }
}
