package com.me4502.MAPL.slick.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MAPLImage {

	private Image image;

	private long lastDrawTime = 0;

	public MAPLImage(Image image) {

		this.image = image;
	}

	public MAPLImage(String absolutePath) throws SlickException {

		try {
			image = new Image(absolutePath, Image.FILTER_NEAREST);
		}
		catch(Exception e){}
	}

	public int getWidth() {

		return image.getWidth();
	}

	public int getHeight() {

		return image.getHeight();
	}

	public Image getImage() {

		return image;
	}

	public void setDrawTime() {

		lastDrawTime = System.currentTimeMillis();
	}

	public long getLastDrawTime() {

		return lastDrawTime;
	}
}