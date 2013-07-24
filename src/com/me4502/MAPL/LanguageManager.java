package com.me4502.MAPL;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.me4502.MAPL.slick.SlickMAPL;
import com.me4502.MAPL.util.yaml.YAMLFormat;
import com.me4502.MAPL.util.yaml.YAMLProcessor;

public class LanguageManager {

	private static HashMap<String, Language> languages = new HashMap<String, Language>();

	public boolean addLanguage(String name, String filename) {

		System.out.println("Attempting langauge add: " + name);
		Language lang;
		try {
			lang = new Language(filename);
			languages.put(name,lang);
			return true;
		} catch(MAPLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String translateString(String language, String string) {

		String line = languages.get(language).lines.get(string);
		if(line == null)
			line = string;
		return line;
	}

	public class Language {

		File file;

		private HashMap<String, String> lines = new HashMap<String, String>();

		private Language(String filename) throws MAPLException {

			System.out.println("Initializing Language: " + filename);
			file = new File(filename);
			if(!file.exists()) {
				URL url = MAPL.inst().getProgram().getClass().getResource(filename);
				if(url != null)
					file = new File(url.getFile());
			}
			if(!file.exists())
				file = new File(((SlickMAPL) MAPL.inst()).getApplicationDirectory(), filename);
			if(!file.exists())
				file = new File("src/" + filename);
			if(!file.exists())
				throw new MAPLException("Can't find Language File: " + filename);
			loadLines();
		}

		private void loadLines() throws MAPLException {

			YAMLProcessor config = new YAMLProcessor(file, false, YAMLFormat.EXTENDED);
			try {
				config.load();
			} catch (IOException e) {
				e.printStackTrace();
				throw new MAPLException("Unable to load Language File " + file.getName());
			}

			if(config.getKeys(null) == null)
				throw new MAPLException("Invalid Language File");

			for(String key : config.getKeys(null)) {

				System.out.println(key);
				lines.put(key.trim(), config.getString(key).trim());
			}
		}
	}
}