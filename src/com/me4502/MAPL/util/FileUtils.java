package com.me4502.MAPL.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;

import com.me4502.MAPL.MAPL;

public class FileUtils {

	public static boolean hasGotNatives = false;

	public static boolean isInstalling() {

		File natives = new File(MAPL.inst().getApplicationDirectory(), "natives/");
		if (!natives.exists())
			natives.mkdirs();
		File os = new File(natives, SystemUtils.getOsString());
		return !os.exists();
	}

	public static File getAppDir(String par0Str) {
		System.out.println("Finding Application Directory: " + par0Str);
		String s = System.getProperty("user.home", ".");
		File file = null;

		if (SystemUtils.getOsString().equalsIgnoreCase("windows")) {
			String s1 = System.getenv("APPDATA");

			if (s1 != null)
				file = new File(s1, new StringBuilder().append(".").append(par0Str).append('/').toString());
			else
				file = new File(s, new StringBuilder().append('.').append(par0Str).append('/').toString());
		} else if (SystemUtils.getOsString().equalsIgnoreCase("macosx"))
			file = new File(s, new StringBuilder().append("Library/Application Support/").append(par0Str).toString());
		else if (SystemUtils.getOsString().equalsIgnoreCase("solaris"))
			file = new File(s, new StringBuilder().append('.').append(par0Str).append('/').toString());
		else if (SystemUtils.getOsString().equalsIgnoreCase("linux"))
			file = new File(s, new StringBuilder().append(par0Str).append('/').toString());
		else
			file = new File(s, new StringBuilder().append(par0Str).append('/').toString());

		if (!file.exists() && !file.mkdirs())
			throw new RuntimeException("The working directory could not be created: " + file.getName());
		else
			return file;
	}

	public static void downloadFiles(String list, File outputDir) {

		BufferedReader bufferedreader = null;
		try {
			URL url = new URL(list);
			URLConnection urlconnection = url.openConnection();
			urlconnection.setDoOutput(true);
			bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			String s;
			while ((s = bufferedreader.readLine()) != null) {
				downloadFile(list.replace("files.txt", s), new File(outputDir, s));
			}
			if (hasGotNatives == false) {
				new File(outputDir, "natives.done").createNewFile();
				hasGotNatives = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bufferedreader != null)
				try {
					bufferedreader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static void downloadFile(final String url, final File out) {
		System.out.println("Downloading File: " + url);
		//if (out.exists())
		//	out.delete();
		java.nio.channels.ReadableByteChannel readablebytechannel = null;
		FileOutputStream fileoutputstream = null;
		try {
			URL url1 = new URL(url);
			readablebytechannel = Channels.newChannel(url1.openStream());
			fileoutputstream = new FileOutputStream(out);
			fileoutputstream.getChannel().transferFrom(readablebytechannel, 0L, 0x1000000L);
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				fileoutputstream.close();
				readablebytechannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}