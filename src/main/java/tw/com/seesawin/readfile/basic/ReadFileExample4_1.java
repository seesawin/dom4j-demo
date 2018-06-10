package tw.com.seesawin.readfile.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;


public class ReadFileExample4_1 {

	public static void main(String[] args) {
		try {
			try(FileInputStream inputStream = new FileInputStream(FILENAME)) {     
			    String everything = IOUtils.toString(inputStream);
			    System.out.println(everything);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// create a read mehtod
	
}
