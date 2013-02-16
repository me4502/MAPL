package com.me4502.MAPL.GUI;

public abstract class GUIRenderer {

	/**
	 * Called when a button should be rendered.
	 * 
	 * @param button The button to render.
	 * @param x Mouse x coord.
	 * @param y Mouse y coord.
	 */
	public abstract void renderButton(MAPLButton button, int x, int y);
} 