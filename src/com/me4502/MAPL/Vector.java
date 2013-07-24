package com.me4502.MAPL;

/**
 * The vector class.
 * 
 * Generally used to symbolize relative locations, for example a velocity. For
 * absolute positions, see {@link Location}
 * 
 * @author Me4502
 * 
 */
public class Vector extends Position implements Cloneable {

	public Vector(double... coordinate) {
		points = coordinate.clone();
	}

	public Vector(Vector vector) {
		points = vector.points.clone();
	}

	public static Vector fromString(String string) {
		double[] bits;
		string = string.substring(1, string.length() - 1);
		bits = new double[string.split(",").length];
		int i = 0;
		for (String bit : string.split(",")) {
			bits[i] = Double.parseDouble(bit);
			i++;
		}

		return new Vector(bits);
	}

	@Override
	public Vector clone() {
		return new Vector(this);
	}

	public Vector relative(Location location) {
		Vector vec = new Vector(this);
		for (int i = 0; i < getDimensions(); i++) {
			vec.setPoint(i, location.getPoint(i) - getPoint(i));
		}

		return vec;
	}

	public Vector normalize() {

		double length = getLength();

		for(int i = 0; i < getDimensions(); i++)
			setPoint(i, getPoint(i) / length);

		return this;
	}
}