package ics3.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller class part of the MVC implements KeyListener to invoke rotation and size increase/decrease of triangle
 */
public class Controller implements KeyListener {

    private boolean increaseSize;
    private boolean decreaseSize;


    /**
     * Default constructor where variables are initialized
     */
    public Controller() {
        increaseSize = false;
        decreaseSize = false;
    }

    /**
     * Function to check if an event which indicates that a keystroke occurred in a component
     *
     * @param e KeyEvent name
     */
    public void keyPressed(KeyEvent e) {
        // if the 'C' key is pressed
        if (e.getKeyCode() == 67) {
            // reverse direction of triangle rotation
            Triangle_Bounce.model.triangle.setAngleSpeed(-(Triangle_Bounce.model.triangle.getAngleSpeed()));
        }
        // if '+' key is pressed
        if (e.getKeyCode() == 61) {
            // sets increase to true
            increaseSize = true;
        }
        if (e.getKeyCode() == 45) {
            // sets decrease to true
            decreaseSize = true;
        }
    }

    /**
     * Function from KeyListener that runs when a key is released on a component
     *
     * @param e KeyEvent -> used when key is released
     */
    public void keyReleased(KeyEvent e) {
        // resets both booleans
        decreaseSize = false;
        increaseSize = false;
    }

    /**
     * Unused KeyTyped function but is needed to be put in place for the KeyListener to function
     *
     * @param e KeyEvent -> Used when a key is typed
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Getter method to get the boolean for increasing size
     *
     * @return increaseSize
     */
    public boolean getIncreaseSize() {
        return increaseSize;
    }

    /**
     * Getter method to get the boolean for decreasing size
     *
     * @return decreaseSize
     */
    public boolean getDecreaseSize() {
        return decreaseSize;
    }
}
