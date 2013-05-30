package com.me4502.MAPL.entities;

import com.me4502.MAPL.Location;
import com.me4502.MAPL.Vector;

public interface Entity {

	public Location getLocation();

	public Vector getVelocity();

	public int getAge();

	public void render();

	public void update();
}