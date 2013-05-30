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

	/**
	 * Called when a slider should be rendered.
	 * 
	 * @param slider The slider to render.
	 * @param x Mouse x coord.
	 * @param y Mouse y coord.
	 * @param sliderOffset The x offset of the slider.
	 */
	public abstract void renderSlider(MAPLSlider slider, int x, int y, int sliderOffset);

	/**
	 * Called when a tooltip should be rendered.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param tooltip The text to render.
	 */
	public abstract void renderTooltip(MAPLTooltip tooltip);
}