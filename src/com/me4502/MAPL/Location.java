package com.me4502.MAPL;

/**
 * The location class.
 * 
 * Generally used to symbolize absolute locations, for example a sprite's
 * position. For relative positions, see {@link Vector}
 * 
 * @author Me4502
 * 
 */
public class Location extends Position implements Cloneable {

	public Location(double... points) {
		this.points = points.clone();
	}

	public Location(Location location) {
		points = location.points.clone();
	}

	public static Location fromString(String string) {
		double[] bits;
		string = string.substring(1, string.length() - 1);
		bits = new double[string.split(",").length];
		int i = 0;
		for (String bit : string.split(",")) {
			bits[i] = Double.parseDouble(bit);
			i++;
		}

		return new Location(bits);
	}

	@Override
	public Location clone() {
		return new Location(this);
	}

	public static boolean isWithinRadius(Location location1, Location location2, int radius) {

		return location1.getDistanceBetweenSquared(location2) < radius*radius;
	}
}