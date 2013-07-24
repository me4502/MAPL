package com.me4502.MAPL.entities;

import com.me4502.MAPL.BoundingBox;
import com.me4502.MAPL.Location;
import com.me4502.MAPL.Vector;

public interface Entity {

	public BoundingBox getBoundingBox();

	public boolean doesIntersect(Entity other);

	public Location getLocation();

	public void setLocation(Location other);

	public Vector getVelocity();

	public int getAge();

	public boolean isValid();

	public void render();

	public void update();

	void setVelocity(Vector velocity);
}