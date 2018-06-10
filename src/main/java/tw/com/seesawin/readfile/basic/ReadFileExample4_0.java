package tw.com.seesawin.readfile.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;


public class ReadFileExample4_0 {
	private static final String FILENAME = "E:\\test\\filename.txt";

	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream(FILENAME);
			try {
			    String everything = IOUtils.toString(inputStream);
			    System.out.println(everything);
			} finally {
			    inputStream.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
