package ics3.graphics;

/**
 * Class which takes in Line classes and creates a triangle with them rendered
 * in the middle of the screen and implements bouncing off that triangle
 */
public class Triangle {
    // Array for lines (3 for 3 sides)
    public Line[] lines = new Line[3];
    private double[] topCoordinates = new double[2]; // top x,y for top of triangle
    private double[] leftCoordinates = new double[2]; // left
    private double[] rightCoordinates = new double[2]; // right
    private double angleSpeed;

    private double radius;
    private double angle;
    private double centerX, centerY;

    /**
     * Default constructor where variables and methods are instantiated and initialized
     *
     * @param radius      radius of a triangle (equilateral so it takes in components as a circle)
     * @param frameWidth  width of the drawing frame (needed to set triangle in the middle)
     * @param frameHeight height of the drawing frame (needed to set triangle in the middle)
     * @param angle       the current angle at which the triangle is drawn
     * @param angleSpeed  the current speed at which the triangle rotates
     */
    public Triangle(double radius, double frameWidth, double frameHeight, double angle, double angleSpeed) {
        this.radius = radius;
        this.angle = angle;
        centerX = frameWidth / 2;
        centerY = frameHeight / 2;
        this.angleSpeed = angleSpeed;

        topCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(0 + angle));
        topCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(0 + angle));

        leftCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(240 + angle));
        leftCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(240 + angle));

        rightCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(120 + angle));
        rightCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(120 + angle));

        lines[0] = new Line(leftCoordinates[0], leftCoordinates[1], rightCoordinates[0], rightCoordinates[1], 5);
        lines[1] = new Line(leftCoordinates[0], leftCoordinates[1], topCoordinates[0], topCoordinates[1], 5);
        lines[2] = new Line(topCoordinates[0], topCoordinates[1], rightCoordinates[0], rightCoordinates[1], 5);
    }

    /**
     * spins the triangle and increases/decreases the size
     */
    public void calculateLineVariables() {
        increaseTriangleRotation();
        increaseSize();
    }

    /**
     * Function to bounce off the lines of the triangle
     *
     * @param ball takes in a Ball (needed to check distance between them in the collision engine
     */
    public void bounceOffAllLines(Ball ball) {
        for (Line line : lines) {
            if (collision_Engine.circleLineCollision(ball, line)) {
                ball.vector.performBounce(line.getSlope());
            }
        }
    }


    /**
     * Increases rotation on the triangle and re-renders the lines to display
     * them at set location
     */
    private void increaseTriangleRotation() {
        angle += angleSpeed;
        topCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(0 + angle));
        topCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(0 + angle));

        leftCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(240 + angle));
        leftCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(240 + angle));

        rightCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(120 + angle));
        rightCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(120 + angle));

        lines[0].setLocation(leftCoordinates[0], leftCoordinates[1], rightCoordinates[0], rightCoordinates[1]);
        lines[1].setLocation(leftCoordinates[0], leftCoordinates[1], topCoordinates[0], topCoordinates[1]);
        lines[2].setLocation(topCoordinates[0], topCoordinates[1], rightCoordinates[0], rightCoordinates[1]);
    }

    /**
     * Increases the size of the triangle by increasing the radius of the triangle
     */
    private void increaseSize() {
        if (Triangle_Bounce.controller.getIncreaseSize()) {
            radius += 1.5;
        } else if (Triangle_Bounce.controller.getDecreaseSize()) {
            radius -= 1.5;
        } else {
            return;
        }

        topCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(0 + angle));
        topCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(0 + angle));

        leftCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(240 + angle));
        leftCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(240 + angle));

        rightCoordinates[0] = centerX + radius * Math.cos(Math.toRadians(120 + angle));
        rightCoordinates[1] = centerY + radius * Math.sin(Math.toRadians(120 + angle));

        lines[0].setLocation(leftCoordinates[0], leftCoordinates[1], rightCoordinates[0], rightCoordinates[1]);
        lines[1].setLocation(leftCoordinates[0], leftCoordinates[1], topCoordinates[0], topCoordinates[1]);
        lines[2].setLocation(topCoordinates[0], topCoordinates[1], rightCoordinates[0], rightCoordinates[1]);
    }

    /**
     * setter method to set the speed of the rotation
     *
     * @param angleSpeed how fast it will rotate (double)
     */
    public void setAngleSpeed(double angleSpeed) {
        this.angleSpeed = angleSpeed;
    }

    /**
     * getter method to get the current speed of the rotation
     *
     * @return speed of the angle
     */
    public double getAngleSpeed() {
        return angleSpeed;
    }
}
