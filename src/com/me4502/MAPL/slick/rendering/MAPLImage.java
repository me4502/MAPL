package com.me4502.MAPL.slick.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MAPLImage {

	private int resolution;
	private Image image;

	public MAPLImage(Image image) {

		this.image = image;
		resolution = Math.min(image.getWidth(), image.getHeight());
	}

	public MAPLImage(String absolutePath) throws SlickException {

		image = new Image(absolutePath, Image.FILTER_NEAREST);
		resolution = Math.min(image.getWidth(), image.getHeight());
	}

	public void setResolution(int res) {

		resolution = res;
	}

	public int getResolution() {

		return resolution;
	}

	public Image getImage() {

		return image;
	}
}