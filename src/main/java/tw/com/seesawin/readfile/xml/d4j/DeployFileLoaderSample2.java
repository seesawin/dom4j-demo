package tw.com.seesawin.readfile.xml.d4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class DeployFileLoaderSample2 {

	public static void main(String[] args) throws DocumentException {
		// execute
		DeployFileLoaderSample2 sample = new DeployFileLoaderSample2();
		sample.parseWithSAX(new File("C:\\test\\test.xml"));

		System.out.println("0001");
		sample.iterateRootChildren();

		// System.out.println("0002");
		// sample.iterateRootChildren("student");

		// System.out.println("0003");
		// sample.treeWalk(sample.getDocument());

		// System.out.println("0004");
		// sample.iterateBackListRootChildren();
		//
		// System.out.println("0005");
		// sample.browseRootChildren();
	}

	/**
	 * dom4j object model representation of a xml document.
	 * 
	 * Note: We use the interface(!) not its implementation
	 */
	private Document doc;
	private Element root;

	/**
	 * Loads a document from a file.
	 *
	 * @throw a org.dom4j.DocumentException occurs whenever the buildprocess fails.
	 */
	public void parseWithSAX(File aFile) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		this.doc = xmlReader.read(aFile);
	}

	/********************** Using Iterator ********************************/

	/**
	 * Loads a document from a file.
	 *
	 */
	public void iterateRootChildren() {
		root = this.doc.getRootElement();
		Iterator elementIterator = root.elementIterator();
		int count = 0;

		while (elementIterator.hasNext()) {
			++count;
			System.out.println("-------------count " + count + " ---------------");
			Element element = (Element) elementIterator.next();
//			System.out.println(element.getName());

			treeWalk(element);

			// Iterator elementIterator2 = element.elementIterator("student22");
			// while (elementIterator2.hasNext()) {
			// Element element2 = (Element) elementIterator2.next();
			// System.out.println(">> " + element2.getName());
			// // System.out.println(element2.getText());
			//
			// Iterator elementIterator3 = element2.elementIterator("firstname");
			// while (elementIterator3.hasNext()) {
			// Element element3 = (Element) elementIterator3.next();
			// System.out.println(">>>> " + element3.getName());
			// System.out.println(">>>>>> " + element3.getText());
			// }
			// }
		}
		System.out.println("count : " + count);
	}

	public void iterateRootChildren(String aFilterElementName) {
		root = this.doc.getRootElement();
		Iterator elementIterator = root.elementIterator(aFilterElementName);
		while (elementIterator.hasNext()) {
			Element element = (Element) elementIterator.next();
			System.out.println(element.getName());
		}
	}

	/********************** Fast index based Navigation ********************************/
	public Document getDocument() {
		return this.doc;
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
//				System.out.println(element.getName());
				treeWalk((Element) node);
			} else if (node instanceof Text) {
				if(element.getText() != null && element.getTextTrim() != "") {
					System.out.println(element.getName() + ":" + element.getTextTrim());
				}
			} else if (node instanceof Attribute) {
//				System.out.println(element.getName());
			} else {
				// do something....
				String key = element.getName();
				String value = element.getTextTrim();
//				System.out.println("key : " + key + ", value : " + value);
			}
		}
	}

	/********************** Using a backed List ********************************/
	public void iterateBackListRootChildren() {
		Element root = doc.getRootElement();

		List elementsList = root.elements();

		// we have access to the size() and other List methods
		if (elementsList.size() > 4) {
			// now lets remove a range of elements
			elementsList.subList(3, 4).clear();
		}
	}

	public void browseRootChildren() {

		/*
		 * Let's look how many "James" are in our XML Document an iterate them ( Yes, there are three James in this project ;) )
		 */

		XPath xpathSelector = DocumentHelper.createXPath("/class/student");
		List<Node> results = xpathSelector.selectNodes(doc);
		for (Iterator<Node> iter = results.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			System.out.println("001 : " + element.getName());
		}

		for (Node n : results) {
			System.out.println("002 : " + n.getName());
		}

		for (int i = 0; i < results.size(); i++) {
			Node n = results.get(i);
			System.out.println("003 : " + n.getName());
		}

		// for(Element e : results) {
		// System.out.println(e.getName());
		// }

		// select all children of address element having person element with attribute and value "Toby" as parent
		String firstname1 = doc.valueOf("//class/student[1]/firstname/@name");
		System.out.println("firstname1 : " + firstname1);

		String firstname2 = doc.valueOf("//class/student[2]/firstname");
		System.out.println("firstname2 : " + firstname2);

		String hobby = doc.valueOf("//class/student[1]/firstname/@name");
		System.out.println("hobby : " + hobby);

		String name = doc.valueOf("//class/student[@rollno='593']/firstname");
		System.out.println("name : " + name);

		// select people elements which have location attriute with the value "London"
		Number count = doc.numberValueOf("//class/student[2]/marks");
		System.out.println("count : " + count);
	}
}
