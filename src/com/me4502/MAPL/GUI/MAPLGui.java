package com.me4502.MAPL.GUI;

public abstract class MAPLGui {

	/**
	 * Identifier. Used to allow the GUI renderer to tell apart special types of a gui. Eg, buttons using special images.
	 */
	private String identifier = "";
	private boolean visible = true;

	public int x, y;

	/**
	 * Instantiates a new GUI Item.
	 * 
	 * @param x The x position.
	 * @param y The y position.
	 */
	public MAPLGui(int x, int y) {

		this.x = x;
		this.y = y;
	}

	/**
	 * Check whether this GUI item is visible.
	 * 
	 * @return whether the GUI item is visible.
	 */
	public boolean isVisible() {

		return visible;
	}

	/**
	 * Set if the gui item is visible or not.
	 * 
	 * @param visible The gui items new visibility.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Get the gui items identifier.
	 * 
	 * @return this guis identifier
	 */
	public String getIdentifier() {

		return identifier;
	}

	/**
	 * Set this guis identifier.
	 * 
	 * @param identifier The new identifier
	 */
	public MAPLGui setIdentifier(String identifier) {

		this.identifier = identifier;
		return this;
	}

	/**
	 * Renders the GUI item.
	 * 
	 * @param x Mouse x position.
	 * @param y Mouse y position.
	 */
	public abstract void onRender(int x, int y);

	/**
	 * Called upon a click occuring. Checks if the click touched this.
	 * 
	 * @param x Click x position.
	 * @param y Click y position.
	 * @return If we care about the click.
	 */
	public abstract boolean onClick(int x, int y, int button);

	/**
	 * Checks whether the mouse is over this gui item or not.
	 * 
	 * @param x The mouse X coordinate
	 * @param y The mouse Y coordinate
	 * @return if the mouse is over
	 */
	public abstract boolean isMouseOver(int x, int y);
}