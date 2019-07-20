package ics3.graphics;

/**
 * Class which is used to detect collision
 */
public class collision_Engine {

    public collision_Engine() {

    }

    /**
     * returns true if there is a collision
     *
     * @param ball takes in a ball class (useful for more than 1 ball)
     * @param line takes in a line (useful for more than 1 line)
     * @return true if collision and false if no collision
     */
    public static boolean circleLineCollision(Ball ball, Line line) {
        // previous ball location
        double previousBallCX = ball.returnCenterX() - (ball.vector.scaleX * ball.getSpeed());  // x velocity..
        double previousBallCY = ball.returnCenterY() - (ball.vector.scaleY * ball.getSpeed()); // y velocity...
        // future ball location
        double futureBallCX = ball.returnCenterX() + (ball.vector.scaleX * ball.getSpeed());  // x velocity..
        double futureBallCY = ball.returnCenterY() + (ball.vector.scaleY * ball.getSpeed()); // y velocity...
        // slope and bValue of the line (in format y = mx + b)
        double slope = Linear_Math.equationLine(new double[]{line.getInitialX(), line.getInitialY()}, new double[]{line.getCurrX(), line.getCurrY()})[0];
        double bValue = Linear_Math.equationLine(new double[]{line.getInitialX(), line.getInitialY()}, new double[]{line.getCurrX(), line.getCurrY()})[1];
        // get distance from previous ball pos to the line
        double[] previousBallLineXYDistance = Linear_Math.shortestXYDistanceBetweenPointAndLine(new double[]{previousBallCX, previousBallCY}, new double[]{slope, bValue});
        double previousBallLineDistance = Linear_Math.shortestDistanceBetweenPointAndLine(new double[]{previousBallCX, previousBallCY}, new double[]{slope, bValue});
        // get current distance from ball to line
        double[] XYDistance = Linear_Math.shortestXYDistanceBetweenPointAndLine(new double[]{ball.returnCenterX(), ball.returnCenterY()}, new double[]{slope, bValue});
        double distance = Linear_Math.shortestDistanceBetweenPointAndLine(new double[]{ball.returnCenterX(), ball.returnCenterY()}, new double[]{slope, bValue});
        // get future distance from ball to line
        double[] futureBallLineXYDistance = Linear_Math.shortestXYDistanceBetweenPointAndLine(new double[]{futureBallCX, futureBallCY}, new double[]{slope, bValue});
        // checks if collision (1st part) (only for previous ball instance)
        if (Math.round(100 * (previousBallLineXYDistance[0] * XYDistance[0])) / 100 <= 0 && Math.round(100 * (previousBallLineXYDistance[1] * XYDistance[1])) / 100 <= 0) {
            // gets equation of new line created
            double[] eqnOfMovementLine = Linear_Math.equationLine(new double[]{ball.returnCenterX(), ball.returnCenterY()}, new double[]{previousBallCX, previousBallCY});
            double[] intersectionOfSurfaceAndMovementLines = Linear_Math.intersectionLines(eqnOfMovementLine, new double[]{slope, bValue});
            // Eliminate any floating point arithmetic errors
            intersectionOfSurfaceAndMovementLines[0] = Math.round(10000 * intersectionOfSurfaceAndMovementLines[0]) / 10000;
            intersectionOfSurfaceAndMovementLines[1] = Math.round(10000 * intersectionOfSurfaceAndMovementLines[1]) / 10000;

            // Give a leeway for the ball radius
            boolean intersectionBetweenMaxMinXValues = intersectionOfSurfaceAndMovementLines[0] >= Math.min(line.getInitialX(), line.getCurrX()) - ball.returnDiameter() / 2 && intersectionOfSurfaceAndMovementLines[0] <= Math.max(line.getInitialX(), line.getCurrX()) + ball.returnDiameter() / 2;
            boolean intersectionBetweenMaxMinYValues = intersectionOfSurfaceAndMovementLines[1] >= Math.min(line.getInitialY(), line.getCurrY()) - ball.returnDiameter() / 2 && intersectionOfSurfaceAndMovementLines[1] <= Math.max(line.getInitialY(), line.getCurrY()) + ball.returnDiameter() / 2;
            // (2cnd part) for collision and if collision has occurred set new ball position and push back the ball to the line
            if (intersectionBetweenMaxMinXValues && intersectionBetweenMaxMinYValues) {
                ball.setX(previousBallCX - ball.returnDiameter() / 2);
                ball.setY(previousBallCY - ball.returnDiameter() / 2);
                pushBack(ball, -(previousBallLineDistance - ball.returnDiameter() / 2));
                return true;
            }
        }
        // checks if collision (1st part) (only for future ball instance)
        if (Math.round(10000 * (futureBallLineXYDistance[0] * XYDistance[0])) / 10000 <= 0 && Math.round(10000 * (futureBallLineXYDistance[1] * XYDistance[1])) / 10000 <= 0) {
            double[] eqnOfMovementLine = Linear_Math.equationLine(new double[]{ball.returnCenterX(), ball.returnCenterY()}, new double[]{futureBallCX, futureBallCY});
            double[] intersectionOfSurfaceAndMovementLines = Linear_Math.intersectionLines(eqnOfMovementLine, new double[]{slope, bValue});
            // Eliminate any floating point arithmetic errors
            intersectionOfSurfaceAndMovementLines[0] = Math.round(10000 * intersectionOfSurfaceAndMovementLines[0]) / 10000;
            intersectionOfSurfaceAndMovementLines[1] = Math.round(10000 * intersectionOfSurfaceAndMovementLines[1]) / 10000;

            // Give a leeway for the ball radius
            boolean intersectionBetweenMaxMinXValues = intersectionOfSurfaceAndMovementLines[0] >= Math.min(line.getInitialX(), line.getCurrX()) - ball.returnDiameter() / 2 && intersectionOfSurfaceAndMovementLines[0] <= Math.max(line.getInitialX(), line.getCurrX()) + ball.returnDiameter() / 2;
            boolean intersectionBetweenMaxMinYValues = intersectionOfSurfaceAndMovementLines[1] >= Math.min(line.getInitialY(), line.getCurrY()) - ball.returnDiameter() / 2 && intersectionOfSurfaceAndMovementLines[1] <= Math.max(line.getInitialY(), line.getCurrY()) + ball.returnDiameter() / 2;
            // (2cnd part) for collision and if collision has occurred set new ball position and push back the ball to the line
            if (intersectionBetweenMaxMinXValues && intersectionBetweenMaxMinYValues) {
                pushBack(ball, -(distance - ball.returnDiameter() / 2));
                return true;
            }
        }
        // if neither previous nor future instances are in collision return false
        return false;
    }

    /**
     * Function to push back a ball to a distance (needed when speed is too high)
     *
     * @param ball     takes in a Ball class
     * @param distance takes in a distance variable
     */
    public static void pushBack(Ball ball, double distance) {
        ball.setX(ball.returnX() - (ball.vector.scaleX * distance));
        ball.setY(ball.returnY() - (ball.vector.scaleY * distance));
    }

}