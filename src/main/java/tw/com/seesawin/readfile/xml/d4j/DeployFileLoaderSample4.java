package tw.com.seesawin.readfile.xml.d4j;

import java.io.File;
import java.util.ArrayList;
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

import tw.com.seesawin.readfile.xml.domain.Student;

public class DeployFileLoaderSample4 {

	public static void main(String[] args) throws DocumentException {
		// execute
		DeployFileLoaderSample4 sample = new DeployFileLoaderSample4();
		sample.parseWithSAX(new File("C:\\test\\test01.xml"));

		System.out.println("0001");
		sample.iterateRootChildren();
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
		
		
		XPath xpathSelector = DocumentHelper.createXPath("/MPEG-TABLES/PMTs/CHANNEL");
		List<Node> results = xpathSelector.selectNodes(doc);
		
		for (int i = 0; i < results.size(); i++) {
			Node n = results.get(i);
			System.out.println("003 : " + n.getName());
		}
		
		
		
		
//		Iterator elementIterator = root.elementIterator();
//		int count = 0;
//
//		List<Student> studentList = new ArrayList<Student>();
//		
//		while (elementIterator.hasNext()) {
//			++count;
//			System.out.println("-------------count " + count + " ---------------");
//			Element element = (Element) elementIterator.next();
////			System.out.println(element.getName());
//			
//			Student model = new Student();
//
//			treeWalk(element, "student", model);
//			studentList.add(model);
//
//		}
//		
//		System.out.println("count : " + count);
		
	}


	/********************** Fast index based Navigation ********************************/
	public Document getDocument() {
		return this.doc;
	}

	public void treeWalk(Element element, String parentName, Student model) {
		int size = element.nodeCount();
		// System.out.println("size : " + size);

		if (size != 1) {
			System.out.println("parent : " + element.getName() + ", size : " + size);
		}

		for (int i = 0; i < size; i++) {
			System.out.println("aaaa i : " + i);
			Node node = element.node(i);
			if (node instanceof Element) {
//				System.out.println(element.getName());
				treeWalk((Element) node, node.getName(), model);
			} else if (node instanceof Text) {
				if(element.getText() != null && element.getTextTrim() != "") {
					System.out.println("parentName : " + parentName);
//					if(parentName != element.getName()) {
						System.out.println(element.getName() + ":" + element.getTextTrim());
//					}
						if("firstname".equals(element.getName())) {
							model.setFirstname(element.getTextTrim());
						}
						if("pid".equals(element.getName())) {
							model.setPid(element.getTextTrim());
						}
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
}
