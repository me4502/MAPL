package com.me4502.MAPL;

import com.me4502.MAPL.util.config.YAMLConfiguration;

public interface MAPLProgram {

	public String getProgramName();

	public String getProgramVersion();

	public int getWindowWidth();

	public int getWindowHeight();

	public float getWindowScaleX();

	public float getWindowScaleY();

	public YAMLConfiguration getConfiguration();
}