package com.me4502.MAPL.slick;

import java.io.File;

import com.me4502.MAPL.MAPL;

public class SlickMAPL extends MAPL {

	private File appDir;

	public File getApplicationDirectory() {
		if (appDir == null) {
			appDir = getAppDir(getProgram().getProgramName().replace(" ", ""));
		}

		return appDir;
	}

	private File getAppDir(String par0Str) {
		String s = System.getProperty("user.home", ".");
		File file = null;

		if (getOs().equalsIgnoreCase("windows")) {
			String s1 = System.getenv("APPDATA");

			if (s1 != null)
				file = new File(s1, new StringBuilder().append(".").append(par0Str).append('/').toString());
			else
				file = new File(s, new StringBuilder().append('.').append(par0Str).append('/').toString());
		} else if (getOs().equalsIgnoreCase("macosx")) {
			file = new File(s, new StringBuilder()
			.append("Library/Application Support/").append(par0Str)
			.toString());
		} else if (getOs().equalsIgnoreCase("solaris")) {
			file = new File(s, new StringBuilder().append('.').append(par0Str)
					.append('/').toString());
		} else if (getOs().equalsIgnoreCase("linux")) {
			file = new File(s, new StringBuilder().append(par0Str).append('/')
					.toString());
		} else {
			file = new File(s, new StringBuilder().append(par0Str).append('/')
					.toString());
		}

		if (!file.exists() && !file.mkdirs()) {
			throw new RuntimeException(new StringBuilder()
			.append("The working directory could not be created: ")
			.append(file).toString());
		} else {
			return file;
		}
	}

	protected String getOs() {
		String s = System.getProperty("os.name").toLowerCase();

		if (s.contains("win"))
			return "windows";
		if (s.contains("mac"))
			return "macosx";
		if (s.contains("solaris"))
			return "solaris";
		if (s.contains("sunos"))
			return "solaris";
		if (s.contains("linux"))
			return "linux";
		if (s.contains("unix"))
			return "linux";
		return "linux";
	}
}