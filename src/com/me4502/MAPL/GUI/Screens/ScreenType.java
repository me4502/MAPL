package com.me4502.MAPL.GUI.Screens;

import java.util.HashMap;

import com.me4502.MAPL.MAPLException;

public class ScreenType {

	private static HashMap<String, Class<? extends MAPLScreen>> screenTypes = new HashMap<String, Class<? extends MAPLScreen>>();

	public static void registerScreen(String name, Class<? extends MAPLScreen> screen) throws MAPLException {

		if(screenTypes.containsKey(name))
			throw new MAPLException("ScreenType registry was attempted, but screen exists! Screen: " + name);
		if(screenTypes.containsValue(screen))
			throw new MAPLException("ScreenType registry was attempted, but screen exists! Screen: " + name);

		screenTypes.put(name, screen);
	}

	public static MAPLScreen getInstanceOfScreen(String name) {

		try {
			MAPLScreen s = screenTypes.get(name).newInstance();
			s.init();
			return s;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;
	}
}