package tw.com.seesawin.readfile;

import java.util.Iterator;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

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
		
		System.out.println("111-------------------------------------------------");
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
			
//			Iterator it2 = element.elementIterator();
//			while (it2.hasNext()) {
//				Element element2 = (Element) it2.next();
//				System.out.println(element2.getName());
//			}
		}
	}
	
	public void iterateRootChildren(String aFilterElementName) {
		root = this.doc.getRootElement();
		Iterator elementIterator = root.elementIterator(aFilterElementName);
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			System.out.println("------" + element.getName());
			
			Iterator it2 = element.elementIterator();
			while (it2.hasNext()) {
				Element element2 = (Element) it2.next();
				System.out.println(element2.getName());
				
				Node n = element2.selectSingleNode("SERVICE-NUMBER");
				if(n != null) {
					System.out.println(n.getName() + " : " + n.getStringValue());
				}
			}
		}
	}


}
