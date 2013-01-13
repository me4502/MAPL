package com.me4502.MAPL;

public class BoundingBox {

    public Vector minimum;
    public Vector maximum;

    public BoundingBox(Vector min, Vector max) {
        minimum = min;
        maximum = max;
    }

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