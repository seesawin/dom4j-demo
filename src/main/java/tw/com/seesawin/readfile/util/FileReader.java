package tw.com.seesawin.readfile.util;

import java.io.InputStream;

public class FileReader {
	/*
	 * read file
	 * 
	 * */
	public static InputStream getFileWithUtil(Class clazz, String fileName) {
		ClassLoader classLoader = clazz.getClassLoader();
		return classLoader.getResourceAsStream(fileName);
	}
}
