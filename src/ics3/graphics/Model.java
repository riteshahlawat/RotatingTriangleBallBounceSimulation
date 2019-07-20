package ics3.graphics;

/**
 * Model class where we will be storing all data and calculations for math, lines, and vectors
 */
public class Model {
    // imports all model classes that are used for the rules and display of components
    public static Ball ball;
    public static Triangle triangle;
    public static Line[] lines = new Line[4];


    private double[] topLeftCoordinates = new double[2];
    private double[] topRightCoordinates = new double[2];
    private double[] bottomLeftCoordinates = new double[2];
    private double[] bottomRightCoordinates = new double[2];

    /**
     * The default constructor for the Model class in which we will instantiate all variables
     * and methods that need to be ran once
     */
    public Model() {
        ball = new Ball(500, 100, 10, Math.toRadians(360 - 120), 8);
        triangle = new Triangle(200, Triangle_Bounce.view.paneWidth, Triangle_Bounce.view.paneHeight, 30, .3);
        topLeftCoordinates[0] = 0;
        if (ball.getSpeed() >= 45) {
            topLeftCoordinates[1] = 20;
            topRightCoordinates[1] = 20;
        } else {
            topLeftCoordinates[1] = 0;
            topRightCoordinates[1] = 0;
        }
        topRightCoordinates[0] = Triangle_Bounce.view.paneWidth;
        bottomLeftCoordinates[0] = 0;
        bottomLeftCoordinates[1] = Triangle_Bounce.view.paneHeight - 2;
        bottomRightCoordinates[0] = Triangle_Bounce.view.paneWidth;
        bottomRightCoordinates[1] = Triangle_Bounce.view.paneHeight - 2;
        initializeFrameBorders();
    }

    /**
     * runs the loop functions for all the Model components
     */
    public void runLoopFunctions() {
        ball.moveBall();
        triangle.calculateLineVariables();
        bounceOffFrame(ball);
        triangle.bounceOffAllLines(ball);
    }

    /**
     * Initializes all the top, bottom, left, and right frame line components
     */
    private void initializeFrameBorders() {
        lines[0] = new Line(topLeftCoordinates[0], topLeftCoordinates[1], topRightCoordinates[0], topRightCoordinates[1], 0);
        lines[1] = new Line(topLeftCoordinates[0], topLeftCoordinates[1], bottomLeftCoordinates[0], bottomLeftCoordinates[1], 0);
        lines[2] = new Line(bottomLeftCoordinates[0], bottomLeftCoordinates[1], bottomRightCoordinates[0], bottomRightCoordinates[1], 0);
        lines[3] = new Line(bottomRightCoordinates[0], bottomRightCoordinates[1], topRightCoordinates[0], topRightCoordinates[1], 0);
    }

    /**
     * Checks if ball and the line is in collision and if so, it bounces the ball using the vector bounce with the line's slope
     *
     * @param ball used to figure out which ball (if/when more balls are used) to use
     */
    private void bounceOffFrame(Ball ball) {
        if (ball.returnY() <= lines[0].getCurrY()) {
            ball.setY(lines[0].getCurrY());
            ball.vector.performBounce(lines[0].getSlope());
        }
        if (ball.returnX() <= lines[1].getCurrX()) {
            ball.setX(lines[1].getCurrX());
            ball.vector.performBounce(lines[1].getSlope());
        }
        if (ball.returnY() >= lines[2].getCurrY() - ball.returnDiameter()) {
            ball.setY(lines[2].getCurrY() - ball.returnDiameter());
            ball.vector.performBounce(lines[2].getSlope());
        }
        if (ball.returnX() >= lines[3].getCurrX() - ball.returnDiameter()) {
            ball.setX(lines[3].getCurrX() - ball.returnDiameter());
            ball.vector.performBounce(lines[3].getSlope());
        }
    }
}
