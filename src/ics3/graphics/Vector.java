package ics3.graphics;
// Math class for vector based movement / bouncing

/**
 * Vector class which uses vector based movement and vector based bounce to move the ball
 */
public class Vector {
    // important variables
    public double iX, iY, currX, currY;
    public double scaleX, scaleY;

    /**
     * default constructor assigns values to the parameters
     *
     * @param iX    initial line X-Variable
     * @param iY    initial line Y-Variable
     * @param currX current line X-Variable
     * @param currY current line Y-Variable
     * @param ang   angle on which the line is affected by
     */
    public Vector(double iX, double iY, double currX, double currY, double ang) {
        this.iX = iX;
        this.iY = iY;
        this.currX = currX;
        this.currY = currY;
        scaleX = Math.cos(ang);
        scaleY = Math.sin(ang);
    }

    /**
     * Function to perform a bounce when given a slope and using vectors
     *
     * @param slopeOfSurface the slope of a surface (from a line)
     */
    public void performBounce(double slopeOfSurface) {
        double[] parallelVector; // The component vector that is parallel to the bouncing surface
        double[] normalVector; // The component vector that is perpendicular to the bouncing surface
        double[] compIntersect; // The intersection of the two above vectors (used to find the length of those components)

        // Determine the intersections of the normal and parallel vectors
        if (slopeOfSurface == 99999999) {
            compIntersect = new double[]{iX, currY};
        } else if (slopeOfSurface == 0) {
            compIntersect = new double[]{currX, iY};
        } else {
            // Calculate the parallel and normal vectors, then determine their intersection
            parallelVector = Linear_Math.eqnLine(new double[]{iX, iY}, slopeOfSurface);
            normalVector = Linear_Math.eqnLine(new double[]{currX, currY}, -1 / slopeOfSurface);
            compIntersect = Linear_Math.intersectionLines(parallelVector, normalVector);
        }

        // Use the compIntersect to find the component distance of the parallel and normal vectors
        // and store those component distances in the parallelMag and normalMag double[] variables
        double[] parallelMag = Linear_Math.findComponentDistance(compIntersect, new double[]{iX, iY});
        double[] normalMag = Linear_Math.findComponentDistance(new double[]{currX, currY}, compIntersect);

        // In every bounce, the normal magnitude gets reversed (i.e. the normal vector flips in direction)
        // while the parallel magnitude stays the same.
        normalMag[0] = -normalMag[0];
        normalMag[1] = -normalMag[1];

        // Add the components using the new parallelMag and normalMag variables and then return that new
        // vector
        double[] newDirections = Linear_Math.addComponents(parallelMag, normalMag);
        scaleX = newDirections[0];
        scaleY = newDirections[1];
        // get the x,y components to size 1 when added and squared (pythagoras theorem) by dividng by hypotenuse
        double hypotenuse = Math.sqrt(Math.pow(scaleX, 2) + Math.pow(scaleY, 2));
        scaleX = scaleX / hypotenuse;
        scaleY = scaleY / hypotenuse;


    }
}
