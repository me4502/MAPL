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
	 * @param loc The current BB location.
	 * @param vec The vector to test against.
	 * 
	 * @return Whether or not the vector intersects this bounding box.
	 */
	public boolean doesIntersect(Location loc, Vector vec) {

		for (int i = 0; i < vec.getDimensions(); i++) {
			if (vec.getPoint(i) > loc.getPoint(i) + minimum.getPoint(i) && vec.getPoint(i) < loc.getPoint(i) + maximum.getPoint(i))
				continue;
			else
				return false;
		}
		return true;
	}

	/**
	 * Check to see if a relative vector is within this bounding box.
	 * 
	 * @param vec The relative vector to test against.
	 * 
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

	/**
	 * Check to see if a vector is within this bounding box.
	 * 
	 * @param loc The current BB location.
	 * @param vec The vector to test against.
	 * 
	 * @return Whether or not the vector intersects this bounding box.
	 */
	public boolean doesIntersect(Location loc, BoundingBox other, Location otherLoc) {

		double curX = minimum.getPoint(0) + loc.getPoint(0);
		double otherX = other.getMinimumPoint().getPoint(0) + otherLoc.getPoint(0);

		double curY = minimum.getPoint(1) + loc.getPoint(1);
		double otherY = other.getMinimumPoint().getPoint(1) + otherLoc.getPoint(1);

		return curX <= otherX + other.getMaximumPoint().getPoint(0) &&
				otherX <= curX + maximum.getPoint(0) &&
				curY <= otherY + other.getMaximumPoint().getPoint(1) &&
				otherY <= curY + maximum.getPoint(1);
	}
}