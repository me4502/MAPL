package com.me4502.MAPL.util;

import java.util.Collection;

public class StringUtil {

	/**
	 * Join an array of strings into a string.
	 * 
	 * @param str
	 * @param delimiter
	 * @param initialIndex
	 * @return
	 */
	public static String joinString(String[] str, String delimiter, int initialIndex) {
		if (str.length == 0) {
			return "";
		}
		StringBuilder buffer = new StringBuilder(str[initialIndex]);
		for (int i = initialIndex + 1; i < str.length; ++i) {
			buffer.append(delimiter).append(str[i]);
		}
		return buffer.toString();
	}

	/**
	 * Join an array of strings into a string.
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String joinString(String[] str, String delimiter) {
		return joinString(str, delimiter, 0);
	}

	/**
	 * Join an array of strings into a string.
	 * 
	 * @param str
	 * @param delimiter
	 * @param initialIndex
	 * @return
	 */
	public static String joinString(Object[] str, String delimiter,
			int initialIndex) {
		if (str.length == 0) {
			return "";
		}
		StringBuilder buffer = new StringBuilder(str[initialIndex].toString());
		for (int i = initialIndex + 1; i < str.length; ++i) {
			buffer.append(delimiter).append(str[i].toString());
		}
		return buffer.toString();
	}

	/**
	 * Join an array of strings into a string.
	 * 
	 * @param str
	 * @param delimiter
	 * @param initialIndex
	 * @return
	 */
	public static String joinString(int[] str, String delimiter,
			int initialIndex) {
		if (str.length == 0) {
			return "";
		}
		StringBuilder buffer = new StringBuilder(Integer.toString(str[initialIndex]));
		for (int i = initialIndex + 1; i < str.length; ++i) {
			buffer.append(delimiter).append(Integer.toString(str[i]));
		}
		return buffer.toString();
	}

	/**
	 * Join an list of strings into a string.
	 * 
	 * @param str
	 * @param delimiter
	 * @param initialIndex
	 * @return
	 */
	public static String joinString(Collection<?> str, String delimiter,
			int initialIndex) {
		if (str.size() == 0) {
			return "";
		}
		StringBuilder buffer = new StringBuilder();
		int i = 0;
		for (Object o : str) {
			if (i >= initialIndex) {
				if (i > 0) {
					buffer.append(delimiter);
				}

				buffer.append(o.toString());
			}
			++i;
		}
		return buffer.toString();
	}
}