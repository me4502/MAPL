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

	/**
	 * Instantiates a new MAPLButton.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 * @param width The width.
	 * @param height The height.
	 * @param name The text displayed on the button. (If any)
	 * @param tooltip The buttons tooltip (If any)
	 * @param scaleX The X Scale.
	 * @param scaleY The Y Scale.
	 */
	public MAPLButton(int x, int y, int width, int height, String name, String tooltip, float scaleX, float scaleY) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.name = name;
		this.tooltip = tooltip;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public int width, height;
	public float scaleX = 1f, scaleY = 1f;
	public String name;
	public String tooltip;

	@Override
	public void onRender(int x, int y) {

		if(!isVisible()) return;
		MAPL.inst().getGUIRenderer().renderButton(this,x,y);
		if(tooltip != null && tooltip.length() > 0) {
			if(isMouseOver(x,y)) {
				new MAPLTooltip(x,y,tooltip).onRender(x, y);
			}
		}
	}

	@Override
	public boolean onClick(int x, int y, int button) {

		if(!isVisible()) return false;
		if(isMouseOver(x,y)) {
			clickAction(button);
			return true;
		}
		return false;
	}

	@Override
	public boolean isMouseOver(int x, int y) {
		if(x > this.x && x < this.x + width * scaleX) {
			if(y > this.y && y < this.y + height * scaleY) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The action thats called upon being clicked.
	 */
	public abstract void clickAction(int button);
}