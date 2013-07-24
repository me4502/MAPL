package com.me4502.MAPL.util.config;

import com.me4502.MAPL.util.yaml.YAMLProcessor;

public class YAMLConfiguration {

	public final YAMLProcessor config;

	public int antiAliasing;
	public boolean vSync;
	public boolean debug;
	public String language;

	public YAMLConfiguration(YAMLProcessor config) {

		this.config = config;
	}

	public void load() {

		antiAliasing = config.getInt("anti-aliasing", 0);
		vSync = config.getBoolean("enable-vsync", false);
		debug = config.getBoolean("debug-mode", false);
		language = config.getString("language", "en_US");

		config.save();
	}

	public void save() {

		config.setProperty("anti-aliasing", antiAliasing);
		config.setProperty("enable-vsync", vSync);
		config.setProperty("language", language);
		config.setProperty("debug-mode", debug);

		config.save();
	}
}