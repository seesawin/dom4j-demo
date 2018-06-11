package tw.com.seesawin.readfile;

import java.io.File;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AbstractSample {
	/**
	 * dom4j object model representation of a xml document.
	 * 
	 * Note: We use the interface(!) not its implementation
	 */
	public Document doc;
	public Element root;

	/**
	 * Loads a document from a file.
	 *
	 * @throw a org.dom4j.DocumentException occurs whenever the buildprocess fails.
	 */
	public void parseWithSAX(File aFile) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		this.doc = xmlReader.read(aFile);
	}
	
	/**
	 * Loads a document from a InputStream.
	 *
	 * @throw a org.dom4j.DocumentException occurs whenever the buildprocess fails.
	 */
	public void parseWithSAX(InputStream is) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		this.doc = xmlReader.read(is);
	}
}
