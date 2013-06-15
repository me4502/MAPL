package com.me4502.MAPL.slick.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MAPLImage {

	private Image image;

	public MAPLImage(Image image) {

		this.image = image;
	}

	public MAPLImage(String absolutePath) throws SlickException {

		image = new Image(absolutePath, Image.FILTER_NEAREST);
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
}