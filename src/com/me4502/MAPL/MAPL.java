package com.me4502.MAPL;

import com.me4502.MAPL.GUI.GUIRenderer;

/**
 * MAPL - Me4502's All-Purpose Programming Library.
 * 
 * @author Me4502
 * 
 */
public abstract class MAPL {

	private static MAPL instance;

	public MAPL() {
		instance = this;
	}

	public static MAPL inst() {

		return instance;
	}

	GUIRenderer guiRenderer;

	public GUIRenderer getGUIRenderer() {
		return guiRenderer;
	}

	public void setGUIRenderer(GUIRenderer renderer) {
		guiRenderer = renderer;
	}
} 