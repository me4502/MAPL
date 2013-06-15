package com.me4502.MAPL.bukkit;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.me4502.MAPL.MAPL;
import com.me4502.MAPL.rendering.RenderUtils;

public class BukkitMAPL extends MAPL {

	JavaPlugin plugin;

	public BukkitMAPL(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public File getApplicationDirectory() {
		return plugin.getDataFolder();
	}

	@Override
	public RenderUtils getRenderer() {
		return null;
	}
}