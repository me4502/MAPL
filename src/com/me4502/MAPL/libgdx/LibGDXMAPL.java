package com.me4502.MAPL.libgdx;

import java.io.File;

import com.me4502.MAPL.MAPL;
import com.me4502.MAPL.rendering.RenderUtils;

public class LibGDXMAPL extends MAPL {

	private boolean paused;

	@Override
	public File getApplicationDirectory() {
		return null;
	}

	public void setPaused(boolean pause) {
		paused = pause;
	}

	public boolean isPaused() {
		return paused;
	}

	@Override
	public RenderUtils getRenderer() {
		return null;
	}
}