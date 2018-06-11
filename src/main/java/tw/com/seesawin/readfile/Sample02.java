package tw.com.seesawin.readfile;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import tw.com.seesawin.readfile.util.FileReader;

public class Sample02 extends AbstractSample {

	public static void main(String[] args) throws DocumentException {
		// execute
		Sample02 sample = new Sample02();
		// read from absolute path
		// sample.parseWithSAX(new File("C:\\test\\test01.xml"));

		// read from this project's resource folder
		sample.parseWithSAX(FileReader.getFileWithUtil(Sample04.class, "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml"));

		sample.treeWalk(sample.doc);

	}

	public void treeWalk(Document document) {
		System.out.println(">>>>> ROOT");
		treeWalk(document.getRootElement());
	}

	public void treeWalk(Element element) {
		int size = element.nodeCount();
		// System.out.println("size : " + size);

		if (size != 1) {
			System.out.println("parent : " + element.getName() + ", size : " + size);
		}

		for (int i = 0; i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				// System.out.println(element.getName());
				treeWalk((Element) node);
			} else if (node instanceof Text) {
				if (element.getText() != null && element.getTextTrim() != "") {
					System.out.println(element.getName() + ":" + element.getTextTrim());
				}
			} else if (node instanceof Attribute) {
				// System.out.println(element.getName());
			} else {
				// do something....
				String key = element.getName();
				String value = element.getTextTrim();
				// System.out.println("key : " + key + ", value : " + value);
			}
		}
	}
}
