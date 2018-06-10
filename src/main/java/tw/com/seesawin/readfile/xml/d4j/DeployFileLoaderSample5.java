package tw.com.seesawin.readfile.xml.d4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DeployFileLoaderSample5 {

	/**
	 * dom4j object model representation of a xml document.
	 * 
	 * Note: We use the interface(!) not its implementation
	 */
	private Document doc;
	private Element root;

	public static void main(String[] args) throws DocumentException {
		// init
		DeployFileLoaderSample5 sample = new DeployFileLoaderSample5();
		sample.parseWithSAX(new File("C:\\test\\test01.xml"));

		// process
		sample.browseRootChildren();
	}

	/**
	 * Loads a document from a file.
	 *
	 * @throw a org.dom4j.DocumentException occurs whenever the buildprocess fails.
	 */
	public void parseWithSAX(File aFile) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		this.doc = xmlReader.read(aFile);
	}

	public void browseRootChildren() {
		// 1.定位root位子
		// 2.取得指定路徑的Node
		Node rootNode = doc.selectSingleNode("//MPEG-TABLES/SDT");

		// 取得root的子層"SDT-ENTRY"的Node List
		List<Node> sdtEntry = rootNode.selectNodes("SDT-ENTRY");
		System.out.println("SDT-ENTRY : " + sdtEntry.size());

		for (Node n0 : sdtEntry) {
			// 取得SDT-ENTRY的子層"SERVICE-ID"的單一Node
			Node serviceId = n0.selectSingleNode("SERVICE-ID");
			System.out.println(serviceId.getName() + " : " + serviceId.getStringValue());

			// 取得SDT-ENTRY的子層"SDT-DESCRIPTORS"的Node List
			List<Node> sdtDescriptors = n0.selectNodes("SDT-DESCRIPTORS");
			for (Node n1 : sdtDescriptors) {
				List<Node> service = n1.selectNodes("SERVICE");
				if (service == null) {
					System.out.println("SERVICE is null!");
				} else {
					for (Node n2 : service) {
						Node typeRaw = n2.selectSingleNode("TYPE-RAW");
						System.out.println(typeRaw.getName() + " : " + typeRaw.getStringValue());
					}
				}
			}
		}
	}
}
