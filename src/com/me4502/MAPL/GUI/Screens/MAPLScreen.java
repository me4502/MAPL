package com.me4502.MAPL.GUI.Screens;

import com.me4502.MAPL.MAPL;

public abstract class MAPLScreen {

	public MAPLScreen() {
	}

	public abstract void init();

	public abstract void render(int x, int y);

	public void update() {
	}

	public abstract void onKeyPress(int key, char character);

	public abstract void onMouseClick(int x, int y, int button);

	public void onMouseMove(int x, int y) {
	}

	public void onMouseDrag(int oldX, int oldY, int newX, int newY) {
	}

	public void onMouseRelease(int x, int y, int button) {
	}

	public int getCentreX() {
		return MAPL.inst().getProgram().getWindowWidth() / 2;
	}

	public int getCentreY() {
		return MAPL.inst().getProgram().getWindowHeight() / 2;
	}

	public float getScaleX() {
		return MAPL.inst().getProgram().getWindowScaleX();
	}

	public float getScaleY() {
		return MAPL.inst().getProgram().getWindowScaleY();
	}
}