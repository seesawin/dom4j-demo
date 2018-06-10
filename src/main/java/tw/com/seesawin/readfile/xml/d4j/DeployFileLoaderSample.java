package tw.com.seesawin.readfile.xml.d4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class DeployFileLoaderSample {

	public static void main(String[] args) throws DocumentException {
		// init
		DeployFileLoaderSample sample = new DeployFileLoaderSample();
		sample.parseWithSAX(new File("C:\\test\\test01.xml"));

		// System.out.println("0001");
		// sample.iterateRootChildren();
		//
		// System.out.println("0002");
		// sample.iterateRootChildren("PMTs");
		//
		// System.out.println("0003");
		// sample.treeWalk(sample.doc);

		System.out.println("0004");
		sample.browseRootChildren();
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
			System.out.println(element.getStringValue());
		}
	}

	/********************** Fast index based Navigation ********************************/
	public void treeWalk(Document document) {
		treeWalk(document.getRootElement());
	}

	public void treeWalk(Element element) {
		int size = element.nodeCount();
		// System.out.println("size : " + size);

		for (int i = 0; i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				// System.out.println(element.getName());
				treeWalk((Element) node);
			} else {
				// do something....
				System.out.println(node.getParent().getName() + ":" + node.getStringValue());
			}
		}
	}

	public void browseRootChildren() {

		/*
		 * Let's look how many "James" are in our XML Document an iterate them ( Yes, there are three James in this project ;) )
		 */

		XPath xpathSelector = DocumentHelper.createXPath("//MPEG-TABLES/TSREADER/VERSION");
		List<Node> results = xpathSelector.selectNodes(doc);
		for (Iterator<Node> iter = results.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			System.out.println(element.getName() + " : " + element.getTextTrim());
		}

		// 指定路徑的值
		String version = doc.valueOf("//MPEG-TABLES/TSREADER");
		System.out.println("VERSION : " + version);

		// 指定路徑的List
		Node rootNode = doc.selectSingleNode("//MPEG-TABLES/PMTs");
		List<Node> pmts = rootNode.selectNodes("CHANNEL");
		System.out.println("pmts size : " + pmts.size());

		for (Node n : pmts) {
			// System.out.println(n.getName() + " : " + n.getStringValue());

			Node serviceNumber = n.selectSingleNode("SERVICE-NUMBER");
			System.out.println(serviceNumber.getName() + " : " + serviceNumber.getStringValue());

			List<Node> elementaryStreamList = n.selectNodes("ELEMENTARY-STREAM");
			for (Node elementaryStream : elementaryStreamList) {
				Node audioLanguage = elementaryStream.selectSingleNode("AUDIO-LANGUAGE");
				if (audioLanguage == null) {
					System.out.println("AUDIO-LANGUAGE is null!");
				} else {
					System.out.println(audioLanguage.getName() + " : " + audioLanguage.getStringValue());
				}
			}
		}
	}
}
