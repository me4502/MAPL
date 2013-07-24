package com.me4502.MAPL.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JarUtils {

	/**
	 * Create a default configuration file from the .jar.
	 *
	 * @param actual      The destination file
	 * @param defaultName The name of the file inside the jar's defaults folder
	 * @param jarFile     The jarfile to search inside.
	 */
	public static void createDefaultConfiguration(File actual, String defaultName, String jarFile) {

		// Make parent directories
		File parent = actual.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}

		if (actual.exists()) {
			return;
		}

		InputStream input = null;
		JarFile file = null;
		try {
			File jar = new File(com.me4502.MAPL.MAPL.class.getProtectionDomain().getCodeSource().getLocation().getPath(), jarFile);
			System.out.println(jar.getAbsolutePath());
			file = new JarFile(jar);
			ZipEntry copy = file.getEntry(defaultName);
			if (copy == null) {
				file.close();
				throw new FileNotFoundException();
			}
			input = file.getInputStream(copy);
		} catch (Exception e) {
			System.out.println("Unable to read default configuration: " + defaultName);
			e.printStackTrace();
		}

		if (input != null) {
			FileOutputStream output = null;

			try {
				output = new FileOutputStream(actual);
				byte[] buf = new byte[8192];
				int length = 0;
				while ((length = input.read(buf)) > 0) {
					output.write(buf, 0, length);
				}

				System.out.println("Default configuration file written: " + actual.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {
					file.close();
				} catch (IOException ignored) {
				}

				try {
					input.close();
				} catch (IOException ignore) {
				}

				try {
					if (output != null) {
						output.close();
					}
				} catch (IOException ignore) {
				}
			}
		} else if (file != null)
			try {
				file.close();
			} catch (IOException ignored) {
			}
	}
}