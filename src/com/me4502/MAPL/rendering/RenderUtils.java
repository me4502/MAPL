package com.me4502.MAPL.rendering;


public interface RenderUtils {

	public void setTextureState(boolean state);

	public boolean getTextureState();

	public Rectangles rectangles();

	public Lines lines();

	public static interface Rectangles {

		public void startRectangles(boolean filled);

		public void drawRectangle(int x1, int y1, int x2, int y2, float r, float g, float b, float a);

		public void drawSingleRectangle(int x1, int y1, int x2, int y2, boolean filled, float r, float g, float b, float a);

		public void endRectangles();
	}

	public static interface Lines {

		public void startLines();

		public void drawLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b, float a);

		public void drawSingleLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b, float a);

		public void drawThickLine(int x1, int y1, int x2, int y2, float width);

		public void endLines();
	}
}