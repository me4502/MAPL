package com.me4502.MAPL.GUI;

import com.me4502.MAPL.MAPL;

public abstract class MAPLButton extends MAPLGui {

	/**
	 * Instantiates a new MAPLButton.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param width The width.
	 * @param height The height.
	 * @param name The text displayed on the button. (If any)
	 */
	public MAPLButton(int x, int y, int width, int height, String name) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.name = name;
	}

	/**
	 * Instantiates a new MAPLButton.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param width The width.
	 * @param height The height.
	 * @param name The text displayed on the button. (If any)
	 * @param tooltip The buttons tooltip (If any)
	 */
	public MAPLButton(int x, int y, int width, int height, String name, String tooltip) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.name = name;
		this.tooltip = tooltip;
	}

	public int width, height;
	public String name;
	public String tooltip;

	@Override
	public void onRender(int x, int y) {
		MAPL.inst().getGUIRenderer().renderButton(this,x,y);
	}

	@Override
	public boolean onClick(int x, int y, int button) {
		if(x > this.x && x < this.x + width) {
			if(y > this.y && y < this.y + height) {
				clickAction();
				return true;
			}
		}
		return false;
	}

	/**
	 * The action thats called upon being clicked.
	 */
	public abstract void clickAction();
}