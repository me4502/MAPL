package com.me4502.MAPL;

import java.io.Serializable;


public abstract class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5008892581509810840L;

	double[] points;

	public Position add(Position pos) {
		try {
			for (int dim = 0; dim < getDimensions(); dim++)
				setPoint(dim, getPoint(dim) + pos.getPoint(dim));

			return this;
		} catch (Exception e) {
			throw new InvalidDimensionException("Position does not contain this dimension!");
		}
	}

	public Position multiply(Position pos) {
		try {
			for (int dim = 0; dim < getDimensions(); dim++)
				setPoint(dim, getPoint(dim) * pos.getPoint(dim));

			return this;
		} catch (Exception e) {
			throw new InvalidDimensionException("Position does not contain this dimension!");
		}
	}

	public Position divide(Position pos) {
		try {
			for (int dim = 0; dim < getDimensions(); dim++) {
				if(pos.getPoint(dim) == 0)
					continue;
				setPoint(dim, getPoint(dim) / pos.getPoint(dim));
			}

			return this;
		} catch (Exception e) {
			throw new InvalidDimensionException("Position does not contain this dimension!");
		}
	}

	public Position subtract(Position pos) {
		try {
			for (int dim = 0; dim < getDimensions(); dim++)
				setPoint(dim, getPoint(dim) - pos.getPoint(dim));

			return this;
		} catch (Exception e) {
			throw new InvalidDimensionException("Position does not contain this dimension!");
		}
	}

	public int getDimensions() {
		return points.length;
	}

	public double getPoint(int dimension) throws InvalidDimensionException {
		try {
			return points[dimension];
		} catch (Exception e) {
			throw new InvalidDimensionException("Vector does not contain this dimension!");
		}
	}

	public int getRoundPoint(int dimension) throws InvalidDimensionException {
		try {
			return (int) points[dimension];
		} catch (Exception e) {
			throw new InvalidDimensionException("Vector does not contain this dimension!");
		}
	}

	public void setPoint(int dimension, double value) throws InvalidDimensionException {
		try {
			points[dimension] = value;
		} catch (Exception e) {
			throw new InvalidDimensionException("Vector does not contain this dimension!");
		}
	}

	public double getLength() {

		return Math.sqrt(getLengthSquared());
	}

	public double getLengthSquared() {

		double length = 0;
		for (int i = 0; i < getDimensions(); i++)
			length += getPoint(i) * getPoint(i);

		return length;
	}

	public double getDistanceBetweenSquared(Position pos) throws InvalidDimensionException {
		if (pos.getDimensions() == getDimensions()) {
			double result = 0;
			for (int i = 0; i < getDimensions(); i++) {
				result += Math.pow(getPoint(i) - pos.getPoint(i), 2);
			}

			return result;
		} else
			throw new InvalidDimensionException("Dimensions of positions do not match!");
	}

	public double getDistanceBetween(Position pos) throws InvalidDimensionException {
		return Math.sqrt(getDistanceBetweenSquared(pos));
	}

	@Override
	public String toString() {
		String locs = "";
		for (double d : points) {
			locs = locs + d + ",";
		}
		locs = locs.substring(0, locs.length() - 1);
		return "[" + locs + "]";
	}

	public class InvalidDimensionException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4144631083085939420L;

		public InvalidDimensionException(String message) {
			super(message);
		}
	}

	public Vector toVector() {
		return new Vector(points);
	}

	public Location toLocation() {
		return new Location(points);
	}
}