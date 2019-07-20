package ics3.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Line class to draw the border lines and triangle lines
 */
public class Line extends JComponent {
    private double currX;
    private double currY;
    private double initialX;
    private double initialY;

    private double slope, perpendicularSlope;
    private double bValue;
    private double xIntersect, yIntersect;
    private double distance;

    private int renderX1, renderY1, renderX2, renderY2;

    private int strokeWeight;

    public Line() {

    }

    /**
     * Default constructor used to initialize variables and render methods ran once
     *
     * @param initialX     initial X-Variable
     * @param initialY     initial Y-Variable
     * @param currX        current X-Variable
     * @param currY        current Y-Variable
     * @param strokeWeight the thickness of the line
     */
    public Line(double initialX, double initialY, double currX, double currY, int strokeWeight) {
        this.initialX = initialX;
        this.initialY = initialY;
        this.currX = currX;
        this.currY = currY;
        xIntersect = 0;
        yIntersect = 0;
        distance = 0;
        setLineVariables();
        this.strokeWeight = strokeWeight;
        initRendering();
    }

    /**
     * getter methods to get the private variables from methods outside of the class
     *
     * @return variable
     */
    public double getCurrX() {
        return currX;
    }

    public double getCurrY() {
        return currY;
    }

    public double getInitialX() {
        return initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public double getSlope() {
        return slope;
    }

    /**
     * Getter method to return the equation of line in format y = mx + b
     *
     * @return an array of data type double -> slope [0] and bValue[1] of line
     */
    public double[] getEquationOfLine() {
        return new double[]{slope, bValue};
    }

    /**
     * Method to set the location at (x,y)1 and (x,y)2 coordinates and re-rendering them
     *
     * @param iX initial X-Variable
     * @param iY initial Y-Variable
     * @param cX current X-Variable
     * @param cY current Y-Variable
     */
    public void setLocation(double iX, double iY, double cX, double cY) {
        initialX = iX;
        initialY = iY;
        currX = cX;
        currY = cY;
        setLineVariables();
        initRendering();
    }

    /**
     * Method to render the line
     */
    private void initRendering() {
        setSize(getPreferredSize());
        setLocation((int) Math.min(initialX, currX) - 1, (int) Math.min(initialY, currY) - 1);
        renderX1 = (int) (initialX - getX());
        renderY1 = (int) (initialY - getY());
        renderX2 = (int) (currX - getX()); // getX() and getY() are the top left corners of the bounding rectangle
        renderY2 = (int) (currY - getY());
    }

    /**
     * Method which gets the size of the drawing pane for the line in JComponent
     *
     * @return Dimension data type with drawing size (length and width)
     */
    @Override
    public Dimension getPreferredSize() {
        double width = Math.abs(initialX - currX) + strokeWeight;
        double height = Math.abs(initialY - currY) + strokeWeight;

        return new Dimension((int) width, (int) height);
    }

    /**
     * paintComponent method which is overridden from the JComponent class to draw the line on the Drawing panel (size is defined by getPreferredSize method)
     *
     * @param g Graphics parameter which is then converted into a Graphics2D by type casting g
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(strokeWeight));
        g2d.drawLine(renderX1, renderY1, renderX2, renderY2);
    }

    /**
     * method to initialize the line variables in y = mx + b and also get the perpendicular slope which will be needed when bouncing
     */
    public void setLineVariables() {
        slope = Linear_Math.equationLine(new double[]{initialX, initialY}, new double[]{currX, currY})[0];
        bValue = Linear_Math.equationLine(new double[]{initialX, initialY}, new double[]{currX, currY})[1];
        perpendicularSlope = Linear_Math.calculatePerpendicularSlope(slope);

    }

}