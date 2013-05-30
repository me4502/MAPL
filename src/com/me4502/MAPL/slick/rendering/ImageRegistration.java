package com.me4502.MAPL.slick.rendering;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageRegistration {

	private String path;

	private int width,height,x,y;
	private boolean isSheet;

	public ImageRegistration(String path) {

		this.path = path;
	}

	public ImageRegistration(String path, int width, int height, int x, int y) {

		this.path = path;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		isSheet = true;
	}

	public String getPath() {

		return path;
	}

	public MAPLImage createImage() throws SlickException {

		if(!isSheet)
			return new MAPLImage(path);

		SpriteSheet tower = ImageManager.inst().getSpriteSheet(getPath(), width, height);

		return new MAPLImage(tower.getSubImage(x, y));
	}
}