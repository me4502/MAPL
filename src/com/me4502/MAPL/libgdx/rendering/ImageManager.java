package com.me4502.MAPL.libgdx.rendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.me4502.MAPL.MAPL;
import com.me4502.MAPL.libgdx.LibGDXMAPL;

public class ImageManager {

	private static ImageManager instance;

	public static ImageManager inst() {

		return instance;
	}

	public ImageManager() {

		instance = this;
	}

	/**
	 * Stores all loaded textures.
	 */
	private Map<String, MAPLImage> loadedTextures = new HashMap<String, MAPLImage>();

	/**
	 * Stores image names to image path conversions.
	 */
	private Map<String, ImageRegistration> images = new HashMap<String, ImageRegistration>();

	/**
	 * Retrieves the image with the specified name. If the image is not loaded, it will be loaded.
	 * 
	 * @param name The name of the image.
	 * 
	 * @return An instance of the image.
	 */
	public MAPLImage getImage(String name) {

		try {
			if(!loadedTextures.containsKey(name.toUpperCase())) {
				loadedTextures.put(name.toUpperCase(), images.get(name.toUpperCase()).createImage());
				System.out.println("Loading texture: " + name.toUpperCase());
			}
			return loadedTextures.get(name.toUpperCase());
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Registers an image, ready to be drawn.
	 * 
	 * @param name The name of the image.
	 * @param path The path to the image file.
	 * 
	 * @return true if successful
	 */
	public boolean registerImage(String name, String path) {
		try {
			if(images.containsKey(name.toUpperCase()))
				return false;
			try {
				URL url = this.getClass().getResource(path);
				File f = new File(url.getFile());
				if(!f.exists())
					throw new FileNotFoundException();
				System.err.println(f.getPath());
				images.put(name.toUpperCase(), new ImageRegistration(f.getPath()));
			} catch(Exception e) {
				try {
					File f = new File(((LibGDXMAPL) MAPL.inst()).getApplicationDirectory(), path);
					if(!f.exists())
						throw new FileNotFoundException();
					System.err.println(f.getAbsolutePath());
					images.put(name.toUpperCase(), new ImageRegistration(f.getAbsolutePath()));
				} catch(Exception ee) {
					images.put(name.toUpperCase(), new ImageRegistration(path));
				}
			}
			System.out.println("Registering image: " + name + " at " + path + " with ID " + images.size());
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Registers an image, ready to be drawn.
	 * 
	 * @param name The name of the image.
	 * @param path The path to the image file.
	 * 
	 * @return true if successful
	 */
	public boolean registerSpriteSheetImage(String name, String path, int width, int height, int x, int y) {
		try {
			if(images.containsKey(name.toUpperCase()))
				return false;
			try {
				URL url = this.getClass().getResource(path);
				File f = new File(url.getFile());
				if(!f.exists())
					throw new FileNotFoundException();
				System.err.println(f.getPath());
				images.put(name.toUpperCase(), new ImageRegistration(f.getPath(), width, height, x, y));
			} catch(Exception e) {
				try {
					File f = new File(((LibGDXMAPL) MAPL.inst()).getApplicationDirectory(), path);
					if(!f.exists())
						throw new FileNotFoundException();
					System.err.println(f.getAbsolutePath());
					images.put(name.toUpperCase(), new ImageRegistration(f.getAbsolutePath(), width, height, x, y));
				} catch(Exception ee) {
					images.put(name.toUpperCase(), new ImageRegistration(path, width, height, x, y));
				}
			}
			System.out.println("Registering image: " + name + " at " + path + " with ID " + images.size());
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param scale
	 * @return if image existed.
	 */
	public boolean draw(String name, int x, int y, int rotation) {
		return draw(name,x,y,1f,1f,rotation);
	}

	public int getGlTexture(String name) {
		return 0;//getImage(name.toUpperCase()).getImage().getTexture().getTextureID();
	}

	public void bindGlTexture(String name) {
		//getImage(name.toUpperCase()).getImage().getTexture().bind();
	}

	public boolean draw(String name, int x, int y, float scaleX, float scaleY, int rotation) {
		return draw(name,x,y,scaleX,scaleY,rotation,1f);
	}

	public boolean draw(String name, int x, int y, int rotation, float alpha) {
		return draw(name,x,y,1f,1f,rotation,alpha);
	}

	private int baseImageScale = 16;

	public boolean draw(String name, int x, int y, float scaleX, float scaleY, int rotation, float alpha) {
		try {
			MAPL.inst().getRenderer().setTextureState(true);
			//getImage(name.toUpperCase()).getImage().setAlpha(alpha);
			//if(rotation != 0)
			//	getImage(name.toUpperCase()).getImage().setRotation(rotation);
			int res = baseImageScale;
			if(baseImageScale != getImage(name.toUpperCase()).getResolution())
				res = getImage(name.toUpperCase()).getResolution() / (getImage(name.toUpperCase()).getResolution() / baseImageScale);
			//getImage(name.toUpperCase()).getImage().setCenterOfRotation(getImage(name.toUpperCase()).getResolution()/2,getImage(name.toUpperCase()).getResolution()/2);
			//getImage(name.toUpperCase()).getImage().draw(x, y, scaleX * res, scaleY * res);
			//getImage(name.toUpperCase()).getImage().setRotation(0);
			MAPL.inst().getRenderer().setTextureState(false);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Returns a SpriteSheet with a dynamic resolution. (All items need to be the same resolution).
	 * 
	 * @param path
	 * @param width The amount of images across
	 * @param height The amount of images vertically
	 * @return
	 */
	public TextureAtlas getSpriteSheet(String path, int width, int height) {

		try {
			try {
				URL url = this.getClass().getResource(path);
				File f = new File(url.getFile());
				System.err.println(f.getPath());
				Texture i = new Texture(f.getPath());
				TextureAtlas atlas = new TextureAtlas();
				return atlas;
			}
			catch(Exception e) {
				try {
					File f = new File(((LibGDXMAPL) MAPL.inst()).getApplicationDirectory(), path);
					if(!f.exists())
						throw new FileNotFoundException();
					System.err.println(f.getAbsolutePath());
					Texture i = new Texture(f.getAbsolutePath());
					TextureAtlas atlas = new TextureAtlas();
					return atlas;
					//return new TextureAtlas(i, i.getWidth() / width, i.getHeight() / height);
				} catch(Exception ee) {
					Texture i = new Texture(path);
					TextureAtlas atlas = new TextureAtlas();
					return atlas;
					//return new SpriteSheet(i, i.getWidth() / width, i.getHeight() / height);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}