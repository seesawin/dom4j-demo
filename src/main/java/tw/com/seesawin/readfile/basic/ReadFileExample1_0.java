package tw.com.seesawin.readfile.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// classic
public class ReadFileExample1_0 {
	private static final String FILENAME = "E:\\test\\filename.txt";

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			// close resources
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
}
