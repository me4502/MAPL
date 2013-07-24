package com.me4502.MAPL.rendering;

public interface FontRenderer {

	public void init();

	public int getFontHeight(String text);

	public int getFontWidth(String text);

	public void drawFont(int x, int y, String text, float r, float g, float b, float a);

	public void drawCentredFont(int x, int y, String text, float r, float g, float b, float a);
}