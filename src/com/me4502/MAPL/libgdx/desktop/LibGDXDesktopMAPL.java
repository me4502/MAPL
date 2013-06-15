package com.me4502.MAPL.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me4502.MAPL.libgdx.LibGDXMAPL;
import com.me4502.MAPL.libgdx.MainLibGDXProgram;

public class LibGDXDesktopMAPL extends LibGDXMAPL {

	public LwjglApplication initialize(MainLibGDXProgram program) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = getProgram().getProgramName();
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		return new LwjglApplication(program, cfg);
	}
}