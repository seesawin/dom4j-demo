package tw.com.seesawin.readfile.basic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileExample3 {
	private static final String FILENAME = "C:\\test\\test.xml";

	public static void main(String[] args) {
		
		try {
			String content = readFile(FILENAME);
			System.out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String readFile(String fileLocation) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(fileLocation, new String[0])));
		return content;
	}
}
