package ics3.graphics;

public class Linear_Math {

    // Given two points, find the equation of the line1 that passes through them and return it as a double[]
    // with the first element being the slope and the second being the y-intercept or b-value
    public static double[] equationLine(double[] point1, double[] point2) {
        double slope;
        double bValue;
        if (point2[0] - point1[0] == 0) {
            slope = 99999999;
            bValue = point2[0];
        } else {
            slope = (point1[1] - point2[1]) / (point1[0] - point2[0]);
            bValue = point1[1] - (slope * point1[0]);
        }

        return new double[]{slope, bValue};
    }

    // Given a point and the slope, find the equation of the line1 and return it as a double[]
    // with the first element being the slope and the second being the y-intercept or b-value
    // If it's a vertical line1, then b-value actually represents the x = __ value
    public static double[] eqnLine(double[] point1, double slope) {
        double bValue;
        if (slope == 99999999) {
            bValue = point1[0];
        } else {
            bValue = point1[1] - (slope * point1[0]);
        }
        return new double[]{slope, bValue};
    }

    // Given the equations of two lines, find their intersection and return it as a double[]
    // with the first element as the x and the second element as the y
    public static double[] intersectionLines(double[] line1, double[] line2) {
        double xVal;
        double yVal;
        if (line1[0] == 99999999) {
            xVal = line1[1];
            yVal = xVal * line2[0] + line2[1];
        } else if (line2[0] == 99999999) {
            xVal = line2[1];
            yVal = xVal * line1[0] + line1[1];
        } else {
            xVal = Math.round(100 * ((line1[1] - line2[1]) / (line2[0] - line1[0]))) / 100;
            yVal = Math.round(100 * ((xVal * line1[0]) + line1[1])) / 100;
        }
        return new double[]{xVal, yVal};
    }

    // Given two points, find their component distance (x-component and y-component) and
    // return it as a double[]
    public static double[] findComponentDistance(double[] objPoint1, double[] objectPt2) {
        return new double[]{objPoint1[0] - objectPt2[0], objPoint1[1] - objectPt2[1]};
    }

    public static double distanceBetweenTwoPoints(double[] point1, double[] pt2) {
        return Math.sqrt(Math.pow(pt2[0] - point1[0], 2) + Math.pow(pt2[1] - point1[1], 2));
    }

    public static double[] XYDistanceBetweenTwoPoints(double[] point1, double[] pt2) {
        return new double[]{pt2[0] - point1[0], pt2[1] - point1[1]};
    }

    // Given the coordinates of the center of a circle and a point on the circumference of
    // the circle, find the tangent slope at that point
    public static double findTangentSlopeOfCircle(double cx, double cy, double px, double py) {
        return calculatePerpendicularSlope((cy - py) / (cx - px));
    }

    // Adds the components of two vectors and returns a resultant vector (the resultant x and y components)
    protected static double[] addComponents(double[] vector1Components, double[] vector2Components) {
        return new double[]{vector1Components[0] + vector2Components[0], vector1Components[1] + vector2Components[1]};
    }

    public static double[][] determineParallelAndNormalComponents(double ix, double iy, double nx, double ny, double slopeOfSurface) {
        double[] parallelVector; // The component vector that is parallel to the bouncing surface
        double[] normalVector; // The component vector that is perpendicular to the bouncing surface
        double[] compIntersect; // The intersection of the two above vectors (used to find the length of those components)
        // A slope of 99999999 (8 9's) is a code for a vertical surface (a code is required since a vertical surface
        // actually has an undefined slope and so it cannot be represented in any other way)

        // Determine the intersections of the normal and parallel vectors
        if (slopeOfSurface == 99999999) {
            /* parallelVector = new double[] {99999999, ix};
            normalVector = new double[] {0, ny};
            ^^Those are the calculations that have been simplified to produce the line1 below: */
            compIntersect = new double[]{ix, ny};
        } else if (slopeOfSurface == 0) {
            /* parallelVector = new double[] {0, iy};
            //normalVector = new double[] {99999999, nx};
            ^^Those are the calculations that have been simplified to produce the line1 below: */
            compIntersect = new double[]{nx, iy};
        } else {
            // Calculate the parallel and normal vectors, then determine their intersection
            parallelVector = Linear_Math.eqnLine(new double[]{ix, iy}, slopeOfSurface);
            normalVector = Linear_Math.eqnLine(new double[]{nx, ny}, -1 / slopeOfSurface);
            compIntersect = Linear_Math.intersectionLines(parallelVector, normalVector);
        }

        // Use the compIntersect to find the component distance of the parallel and normal vectors
        // and store those component distances in the parallelMag and normalMag double[] variables
        double[] parallelMag = Linear_Math.findComponentDistance(compIntersect, new double[]{ix, iy});
        double[] normalMag = Linear_Math.findComponentDistance(new double[]{nx, ny}, compIntersect);

        // Return the parallel and normal vectors' components
        return new double[][]{parallelMag, normalMag};
    }

    public static double calculatePerpendicularSlope(double slope) {
        if (slope == 0) {
            return 99999999;
        }
        if (slope == 99999999) {
            return 0;
        }
        return -1 / slope;
    }

    public static boolean checkIfPointOnLine(double[] eqnLine, double pointToCheck[]) {
        return pointToCheck[1] == pointToCheck[0] * eqnLine[0] + eqnLine[1];
    }

    public static double shortestDistanceBetweenPointAndLine(double[] point, double[] line) {
        double normalSlope = calculatePerpendicularSlope(line[0]);
        double[] normalLineThroughPoint = eqnLine(point, normalSlope);
        double[] intersectionNormalAndLine = intersectionLines(line, normalLineThroughPoint);
        return distanceBetweenTwoPoints(point, intersectionNormalAndLine);
    }

    public static double[] shortestXYDistanceBetweenPointAndLine(double[] point, double[] line) {
        double normalSlope = calculatePerpendicularSlope(line[0]);
        double[] normalLineThroughPoint = eqnLine(point, normalSlope);
        double[] intersectionNormalAndLine = intersectionLines(line, normalLineThroughPoint);
        return XYDistanceBetweenTwoPoints(point, intersectionNormalAndLine);
    }

}
