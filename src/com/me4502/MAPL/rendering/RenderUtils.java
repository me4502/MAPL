package com.me4502.MAPL.rendering;


public interface RenderUtils {

	public void setTextureState(boolean state);

	public boolean getTextureState();

	public Rectangles rectangles();

	public static interface Rectangles {

		public void startRectangles(boolean filled);

		public void drawRectangle(int x1, int y1, int x2, int y2, float r, float g, float b, float a);

		public void drawSingleRectangle(int x1, int y1, int x2, int y2, boolean filled, float r, float g, float b, float a);

		public void endRectangles();
	}

	public Lines lines();

	public static interface Lines {

		public void startLines();

		public void drawLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b, float a);

		public void drawSingleLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b, float a);

		public void drawThickLine(int x1, int y1, int x2, int y2, float width);

		public void endLines();
	}

	public Pixels pixels();

	public static interface Pixels {

		public void startPixels();

		public void drawPixel(int x, int y, float r, float g, float b, float a);

		public void drawSinglePixel(int x, int y, float r, float g, float b, float a);

		public void endPixels();
	}

	public Circles circles();

	public static interface Circles {

		public void startCircles();

		public void drawCircle(int x, int y, double radius, boolean outline, float r, float g, float b, float a);

		public void drawSingleCircle(int x, int y, double radius, boolean outline, float r, float g, float b, float a);

		public void endCircles();
	}

	public LineLoop lineLoops();

	public static interface LineLoop {

		public void startLineLoops();

		public void drawLineLoops(int x, int y, double radius, float r, float g, float b, float a);

		public void drawSingleLineLoops(int x, int y, double radius, float r, float g, float b, float a);

		public void endLineLoops();
	}
}