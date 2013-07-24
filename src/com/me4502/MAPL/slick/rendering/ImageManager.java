package com.me4502.MAPL.slick.rendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.me4502.MAPL.MAPL;
import com.me4502.MAPL.slick.SlickMAPL;

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
	private Map<String, MAPLImage> loadedTextures = new LinkedHashMap<String, MAPLImage>();

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

	public void clearUnusedImages() {

		Iterator<Entry<String, MAPLImage>> iter = loadedTextures.entrySet().iterator();

		while(iter.hasNext()) {

			Entry<String, MAPLImage> text = iter.next();

			if(System.currentTimeMillis() - text.getValue().getLastDrawTime() > 1000 * 20) {
				iter.remove();
				System.out.println("Unloading texture: " + text.getKey().toUpperCase());
			}
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
					File f = new File(((SlickMAPL) MAPL.inst()).getApplicationDirectory(), path);
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
					File f = new File(((SlickMAPL) MAPL.inst()).getApplicationDirectory(), path);
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
		return getImage(name.toUpperCase()).getImage().getTexture().getTextureID();
	}

	public void bindGlTexture(String name) {
		getImage(name.toUpperCase()).getImage().getTexture().bind();
	}

	public boolean draw(String name, int x, int y, float scaleX, float scaleY, int rotation) {
		return draw(name,x,y,scaleX,scaleY,rotation,1f);
	}

	public boolean draw(String name, int x, int y, int rotation, float alpha) {
		return draw(name,x,y,1f,1f,rotation,alpha);
	}

	public boolean draw(String name, int x, int y, float scaleX, float scaleY, int rotation, float alpha) {
		try {
			MAPL.inst().getRenderer().setTextureState(true);
			MAPLImage image = getImage(name.toUpperCase());
			image.getImage().setAlpha(alpha);
			if(rotation != 0)
				image.getImage().setRotation(rotation);
			image.getImage().setCenterOfRotation(image.getWidth()/2,image.getHeight()/2);
			image.getImage().draw(x, y, scaleX * image.getWidth(), scaleY * image.getHeight());
			image.getImage().setRotation(0);
			image.setDrawTime();
			MAPL.inst().getRenderer().setTextureState(false);
			return true;
		}
		catch(Exception e) {
			System.err.println(name);
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
	public SpriteSheet getSpriteSheet(String path, int width, int height) {

		try {
			try {
				URL url = this.getClass().getResource(path);
				File f = new File(url.getFile());
				System.err.println(f.getPath());
				Image i = new Image(f.getPath(), Image.FILTER_NEAREST);
				return new SpriteSheet(i, i.getWidth() / width, i.getHeight() / height);
			}
			catch(Exception e) {
				try {
					File f = new File(((SlickMAPL) MAPL.inst()).getApplicationDirectory(), path);
					if(!f.exists())
						throw new FileNotFoundException();
					System.err.println(f.getAbsolutePath());
					Image i = new Image(f.getAbsolutePath(), Image.FILTER_NEAREST);
					return new SpriteSheet(i, i.getWidth() / width, i.getHeight() / height);
				} catch(Exception ee) {
					Image i = new Image(path, Image.FILTER_NEAREST);
					return new SpriteSheet(i, i.getWidth() / width, i.getHeight() / height);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}