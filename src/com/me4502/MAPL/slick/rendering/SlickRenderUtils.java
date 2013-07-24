package com.me4502.MAPL.slick.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.me4502.MAPL.rendering.RenderUtils;
import com.me4502.MAPL.util.exceptions.MAPLRenderException;

public class SlickRenderUtils implements RenderUtils {

	@Override
	public void setTextureState(boolean state) {

		if(state)
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		else
			GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public boolean getTextureState() {

		return GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
	}

	public static class Rectangles implements RenderUtils.Rectangles {

		private boolean drawing = false;

		protected static Rectangles instance = new Rectangles();

		@Override
		public void startRectangles(boolean filled) {

			try {
				if(drawing)
					throw new MAPLRenderException("Already Drawing Rectangles!");
				drawing = true;
				if(filled)
					GL11.glBegin(GL11.GL_QUADS);
				else
					GL11.glBegin(GL11.GL_LINE_LOOP);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void drawRectangle(int x1, int y1, int x2, int y2, float r, float g, float b, float a) {

			GL11.glColor4f(r, g, b, a);
			GL11.glVertex2d(x1, y1);
			GL11.glVertex2d(x2, y1);
			GL11.glVertex2d(x2, y2);
			GL11.glVertex2d(x1, y2);
		}

		@Override
		public void drawSingleRectangle(int x1, int y1, int x2, int y2, boolean filled, float r, float g, float b, float a) {

			startRectangles(filled);
			drawRectangle(x1,y1,x2,y2,r,g,b,a);
			endRectangles();
		}

		@Override
		public void endRectangles() {

			try {
				if(!drawing)
					throw new MAPLRenderException("Not Drawing Rectangles!");
				drawing = false;
				GL11.glEnd();
			} catch(MAPLRenderException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Lines implements RenderUtils.Lines {

		private static boolean drawing = false;

		protected static Lines instance = new Lines();

		@Override
		public void startLines() {

			try {
				if(drawing)
					throw new MAPLRenderException("Already Drawing Lines!");
				drawing = true;
				GL11.glBegin(GL11.GL_LINES);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void drawLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b, float a) {

			GL11.glColor4f(r, g, b, a);
			if(width > 1) {
				GL11.glEnd();
				drawThickLine(x1,y1,x2,y2,width);
				GL11.glBegin(GL11.GL_LINES);
			}
			else {
				GL11.glLineWidth(width);
				GL11.glVertex2i(x1, y1);
				GL11.glVertex2i(x2, y2);
			}
		}

		@Override
		public void drawSingleLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b, float a) {

			startLines();
			drawLine(x1,y1,x2,y2,width,r,g,b,a);
			endLines();
		}

		@Override
		public void drawThickLine(int x1, int y1, int x2, int y2, float width) {

			Vector2f start = new Vector2f(x1, y1);
			Vector2f end = new Vector2f(x2, y2);

			float dx = x1 - x2;
			float dy = y1 - y2;

			Vector2f rightSide = new Vector2f(dy, -dx);
			if (rightSide.length() > 0) {
				rightSide.normalise();
				rightSide.scale(width / 2);
			}
			Vector2f leftSide = new Vector2f(-dy, dx);
			if (leftSide.length() > 0) {
				leftSide.normalise();
				leftSide.scale(width / 2);
			}

			Vector2f one = new Vector2f();
			Vector2f.add(leftSide, start, one);

			Vector2f two = new Vector2f();
			Vector2f.add(rightSide, start, two);

			Vector2f three = new Vector2f();
			Vector2f.add(rightSide, end, three);

			Vector2f four = new Vector2f();
			Vector2f.add(leftSide, end, four);

			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex3f(one.x, one.y, 0);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex3f(two.x, two.y, 0);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex3f(three.x, three.y, 0);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex3f(four.x, four.y, 0);
			GL11.glEnd();
		}

		@Override
		public void endLines() {

			try {
				if(!drawing)
					throw new MAPLRenderException("Not Drawing Lines!");
				drawing = false;
				GL11.glEnd();
			} catch(MAPLRenderException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Pixels implements RenderUtils.Pixels {

		private static boolean drawing = false;

		protected static Pixels instance = new Pixels();

		@Override
		public void startPixels() {
			try {
				if(drawing)
					throw new MAPLRenderException("Already Drawing Pixels!");
				drawing = true;
				GL11.glBegin(GL11.GL_POINTS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void drawPixel(int x, int y, float r, float g, float b, float a) {

			GL11.glColor4f(r, g, b, a);
			GL11.glVertex2f(x, y);
		}

		@Override
		public void drawSinglePixel(int x, int y, float r, float g, float b, float a) {

			startPixels();
			drawPixel(x,y,r,g,b,a);
			endPixels();
		}

		@Override
		public void endPixels() {

			try {
				if(!drawing)
					throw new MAPLRenderException("Not Drawing Pixels!");
				drawing = false;
				GL11.glEnd();
			} catch(MAPLRenderException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Circles implements RenderUtils.Circles {

		private static boolean drawing = false;

		protected static Circles instance = new Circles();

		@Override
		public void startCircles() {
			try {
				if(drawing)
					throw new MAPLRenderException("Already Drawing Circles!");
				drawing = true;
				GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void drawCircle(int x, int y, double radius, boolean outline, float r, float g, float b, float a) {

			//if(outline) { TODO do this
			//	drawCircle(x,y,radius,outline,r,g,b,a);
			//}

			float angle;
			double x2, y2;

			GL11.glColor4f(r,g,b,a);
			GL11.glVertex2f(x,y);

			GL11.glTexCoord2d(0.5, 0.5);

			double seg = 2*Math.PI / 45;
			for(angle = 0; angle < 2 * Math.PI + seg; angle += seg) {
				x2 = x + Math.sin(angle)*radius;
				y2 = y + Math.cos(angle)*radius;
				GL11.glVertex2d(x2,y2);
			}
		}

		@Override
		public void drawSingleCircle(int x, int y, double radius, boolean outline, float r, float g, float b, float a) {

			startCircles();
			drawCircle(x,y,radius,outline,r,g,b,a);
			endCircles();
		}

		@Override
		public void endCircles() {
			try {
				if(!drawing)
					throw new MAPLRenderException("Not Drawing Circles!");
				drawing = false;
				GL11.glEnd();
			} catch(MAPLRenderException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Rectangles rectangles() {
		return Rectangles.instance;
	}

	@Override
	public Lines lines() {
		return Lines.instance;
	}

	@Override
	public Pixels pixels() {
		return Pixels.instance;
	}

	@Override
	public Circles circles() {
		return Circles.instance;
	}
}