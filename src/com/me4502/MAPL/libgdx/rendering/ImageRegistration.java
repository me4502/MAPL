package com.me4502.MAPL.libgdx.rendering;



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

	public MAPLImage createImage() {

		//if(!isSheet)
		return new MAPLImage(path);

		//TextureAtlas tower = ImageManager.inst().getSpriteSheet(getPath(), width, height);

		//return new MAPLImage(tower.);
	}
}