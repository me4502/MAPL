package com.me4502.MAPL;

/**
 * BoundingBox
 * 
 * This class symbolizes a box that can be used for collision detection. As it
 * uses the {@link Vector} class, it allows for infinite dimensional boxes.
 * 
 * @author Me4502
 * 
 */
public class BoundingBox {

    private final Vector minimum;
    private final Vector maximum;

    public BoundingBox(Vector min, Vector max) {
        minimum = min;
        maximum = max;
    }

    public Vector getMinimumPoint() {

        return minimum;
    }

    public Vector getMaximumPoint() {

        return maximum;
    }

    /**
     * Check to see if a vector is within this bounding box.
     * 
     * @param vec
     *            The vector to test against.
     * @return Whether or not the vector intersects this bounding box.
     */
    public boolean doesIntersect(Vector vec) {

        for (int i = 0; i < vec.getDimensions(); i++) {
            if (vec.getPoint(i) > minimum.getPoint(i) && vec.getPoint(i) < maximum.getPoint(i))
                continue;
            else
                return false;
        }
        return true;
    }
}