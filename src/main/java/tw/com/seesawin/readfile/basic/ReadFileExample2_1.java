package tw.com.seesawin.readfile.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileExample2_1 {
	private static final String FILENAME = "E:\\test\\filename.txt";

	public static void main(String[] args) {
		// execute
		ReadFileExample2_1.read(FILENAME);
	}

	public static String read(String fileLocation) {
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
//				sb.append(System.lineSeparator());
				sCurrentLine = br.readLine();
			}

			String everything = sb.toString();
			System.out.println(everything);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
