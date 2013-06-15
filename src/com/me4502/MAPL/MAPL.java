package com.me4502.MAPL;

import java.io.File;

import com.me4502.MAPL.GUI.GUIRenderer;
import com.me4502.MAPL.rendering.RenderUtils;

/**
 * MAPL - Me4502's All-Purpose Programming Library.
 * 
 * @author Me4502
 * 
 */
public abstract class MAPL {

	private static MAPL instance;

	private MAPLProgram program;
	private LanguageManager languageManager;

	public MAPL() {
		instance = this;
	}

	public void initialize() {

		languageManager = new LanguageManager();
	}

	public LanguageManager getLanguageManager() {

		return languageManager;
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

	public MAPLProgram getProgram() {

		return program;
	}

	public void setProgram(MAPLProgram program) {

		this.program = program;
	}

	public abstract File getApplicationDirectory();

	public abstract RenderUtils getRenderer();
}