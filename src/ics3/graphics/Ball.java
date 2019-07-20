package ics3.graphics;
// imports important libraries

import javax.swing.*;
import java.awt.*;

/**
 * ball classes uses JComponent to draw the ball
 */
public class Ball extends JComponent {
    // important variables
    private double x;
    private double y;
    private double diameter;
    private double originalSpeed;
    private double speed;

    // imports the vector class (for vector based movement / bounce)
    public Vector vector;

    /**
     * default constructor that assigns values to the parameters
     *
     * @param x        current x (drawn at top left of ball)
     * @param y        current y (drawn at top left of ball)
     * @param diameter diameter of the ball
     * @param ang      angle at which the ball is traveling (direction for vector)
     * @param speed    speed at which the ball is moving (magnitude for vector)
     */
    public Ball(double x, double y, double diameter, double ang, double speed) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speed = speed;
        originalSpeed = speed;
        vector = new Vector(x, y, x, y, ang);
        init();
    }

    /**
     * getter method
     *
     * @return variable
     */
    public double returnX() {
        return x;
    }

    public double returnY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double returnDiameter() {
        return diameter;
    }

    public double returnCenterX() {
        return x + diameter / 2;
    }

    public double returnCenterY() {
        return y + diameter / 2;
    }

    /**
     * setter method to set the x position of the ball
     *
     * @param x parameter to get an x position (double) to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setter method to set the y position of the ball
     *
     * @param y parameter to get an x position (double) to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * sets the location and the drawing pane of the ball
     */
    private void init() {
        setLocation((int) x, (int) y);
        setSize(getPreferredSize());
    }

    /**
     * gets the drawing pane size
     *
     * @return Dimension (length, width)
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) diameter, (int) diameter);
    }

    /**
     * paint method that uses 2d graphics to draw the ball
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.fillOval(0, 0, (int) diameter, (int) diameter);
    }

    // moves the ball
    public void moveBall() {
        vector.iX = x;
        vector.iY = y;
        x += (speed * vector.scaleX);
        y += (speed * vector.scaleY);
        vector.currX = x;
        vector.currY = y;
        setLocation((int) x, (int) y);
        // runs the bounceBall() method

    }
}
