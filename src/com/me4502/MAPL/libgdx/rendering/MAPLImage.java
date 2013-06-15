package com.me4502.MAPL.libgdx.rendering;

import com.badlogic.gdx.graphics.Texture;

public class MAPLImage {

	private int resolution;
	private Texture image;

	public MAPLImage(Texture image) {

		this.image = image;
		resolution = Math.min(image.getWidth(), image.getHeight());
	}

	public MAPLImage(String absolutePath) {

		image = new Texture(absolutePath);
		resolution = Math.min(image.getWidth(), image.getHeight());
	}

	public void setResolution(int res) {

		resolution = res;
	}

	public int getResolution() {

		return resolution;
	}

	public Texture getImage() {

		return image;
	}
}