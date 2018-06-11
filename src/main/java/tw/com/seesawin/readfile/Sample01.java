package tw.com.seesawin.readfile;

import java.util.Iterator;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import tw.com.seesawin.readfile.util.FileReader;

public class Sample01 extends AbstractSample {

	public static void main(String[] args) throws DocumentException {
		// init
		Sample01 sample = new Sample01();

		// read from absolute path
//		sample.parseWithSAX(new File("C:\\test\\test01.xml"));

		// read from this project's resource folder
		sample.parseWithSAX(FileReader.getFileWithUtil(Sample01.class, "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml"));

		System.out.println("-------------------------------------------------");
		sample.iterateRootChildren();
		
		System.out.println("-------------------------------------------------");
		sample.iterateRootChildren("PMTs");
	}

	/**
	 * Using Iterator
	 *
	 */
	public void iterateRootChildren() {
		root = this.doc.getRootElement();
		Iterator elementIterator = root.elementIterator();
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			System.out.println(element.getName());
		}
	}
	
	public void iterateRootChildren(String aFilterElementName) {
		root = this.doc.getRootElement();
		Iterator elementIterator = root.elementIterator(aFilterElementName);
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			System.out.println(element.getName());
		}
	}


}
