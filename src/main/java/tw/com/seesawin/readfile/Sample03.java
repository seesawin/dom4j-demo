package tw.com.seesawin.readfile;

import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;

import tw.com.seesawin.readfile.util.FileReader;

public class Sample03 extends AbstractSample {

	public static void main(String[] args) throws DocumentException {
		// execute
		Sample03 sample = new Sample03();
		// read from absolute path
//		sample.parseWithSAX(new File("C:\\test\\test01.xml"));
		
		// read from this project's resource folder
		sample.parseWithSAX(FileReader.getFileWithUtil(Sample04.class, "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml"));

		sample.execute();

	}

	public void execute() {
		XPath xpathSelector = DocumentHelper.createXPath("//MPEG-TABLES/SDT");
		List<Node> results = xpathSelector.selectNodes(doc);
		for (Iterator<Node> iter = results.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			System.out.println("001 : " + element.getName());
		}

		String sdtEntry1 = doc.valueOf("//MPEG-TABLES/SDT/SDT-ENTRY[1]/SERVICE-ID");
		System.out.println("SDT-ENTRY 1 : " + sdtEntry1);
		
		String sdtEntry2 = doc.valueOf("//MPEG-TABLES/SDT/SDT-ENTRY[2]/SERVICE-ID");
		System.out.println("SDT-ENTRY 2 : " + sdtEntry2);
	}
}
