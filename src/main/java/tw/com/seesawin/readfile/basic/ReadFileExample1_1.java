package tw.com.seesawin.readfile.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileExample1_1 {
	private static final String FILENAME = "E:\\test\\filename.txt";

	public static void main(String[] args) {
		// execute
		ReadFileExample1_1.read(FILENAME);
	}

	public static String read(String fileLocation) {
		BufferedReader br = null;
		FileReader fr = null;
		StringBuilder sb = new StringBuilder();

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
				sb.append(System.lineSeparator());
				sCurrentLine = br.readLine();
			}

			System.out.println(sb.toString());

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return sb.toString();
	}
}
