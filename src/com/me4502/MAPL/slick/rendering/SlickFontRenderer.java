package com.me4502.MAPL.slick.rendering;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class SlickFontRenderer {

	String fontName = "Verdana";
	int fontType = Font.BOLD;
	int size = 8;
	UnicodeFont font;

	public SlickFontRenderer(String fontName, int fontType, int fontSize) {
		this.fontName = fontName;
		this.fontType = fontType;
		size = fontSize;
		font = new UnicodeFont(new Font(fontName, fontType, size));
	}

	public SlickFontRenderer(InputStream fontStream, int fontType, int fontSize) {
		this.fontType = fontType;
		size = fontSize;
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontType, fontSize);
			font = new UnicodeFont(f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSize(int size) {
		this.size = size;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		try {
			font.addAsciiGlyphs();
			font.getEffects().add(new ColorEffect());
			font.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getFontHeight(String text) {
		return font.getHeight(text);
	}

	public int getFontWidth(String text) {
		return font.getWidth(text);
	}

	public void drawFont(int x, int y, String text, float r, float g, float b, float a) {
		Color c = new Color(r, g, b, a);
		font.drawString(x, y, text, c);
	}

	public void drawCentredFont(int x, int y, String text, float r, float g, float b, float a) {
		Color c = new Color(r, g, b, a);
		font.drawString(x - getFontWidth(text)/2, y - getFontHeight(text)/2, text, c);
	}
}