package com.me4502.MAPL.GUI;

public abstract class MAPLToggleButton extends MAPLButton {

	public boolean state = false;

	/**
	 * Instantiates a new MAPLToggleButton.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param width The width.
	 * @param height The height.
	 * @param name The text displayed on the button. (If any)
	 * @param state The default state of the button.
	 */
	public MAPLToggleButton(int x, int y, int width, int height, String name, boolean state) {
		super(x, y, width, height, name);
		this.state = state;
	}

	/**
	 * Instantiates a new MAPLToggleButton.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param width The width.
	 * @param height The height.
	 * @param name The text displayed on the button. (If any)
	 * @param tooltip The buttons tooltip (If any)
	 * @param state The default state of the button.
	 */
	public MAPLToggleButton(int x, int y, int width, int height, String name, String tooltip, boolean state) {
		super(x, y, width, height, name, tooltip);
		this.state = state;
	}

	/**
	 * Instantiates a new MAPLToggleButton.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param width The width.
	 * @param height The height.
	 * @param name The text displayed on the button. (If any)
	 * @param tooltip The buttons tooltip (If any)
	 * @param scaleX The X Scale.
	 * @param scaleY The Y Scale.
	 * @param state The default state of the button.
	 */
	public MAPLToggleButton(int x, int y, int width, int height, String name, String tooltip, float scaleX, float scaleY, boolean state) {
		super(x, y, width, height, name, tooltip, scaleX, scaleY);
		this.state = state;
	}

	@Override
	public boolean onClick(int x, int y, int button) {

		if(super.onClick(x, y, button)) {

			state = !state;
			return true;
		}

		return false;
	}
}