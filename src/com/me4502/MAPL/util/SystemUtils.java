package com.me4502.MAPL.util;

public class SystemUtils {

	public static String getOsString() {

		String s = System.getProperty("os.name").toLowerCase();

		if (s.contains("win"))
			return "windows";
		else if (s.contains("mac"))
			return "macosx";
		else if (s.contains("solaris"))
			return "solaris";
		else if (s.contains("sunos"))
			return "solaris";
		else if (s.contains("linux"))
			return "linux";
		else if (s.contains("unix"))
			return "linux";
		else
			return "linux";
	}
}