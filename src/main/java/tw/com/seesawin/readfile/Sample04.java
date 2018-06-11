package tw.com.seesawin.readfile;

import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Node;

import tw.com.seesawin.readfile.util.FileReader;

public class Sample04 extends AbstractSample{

	public static void main(String[] args) throws DocumentException {
		// init
		Sample04 sample = new Sample04();
		
		// read from absolute path
//		sample.parseWithSAX(new File("C:\\test\\test01.xml"));
		
		// read from this project's resource folder
		sample.parseWithSAX(FileReader.getFileWithUtil(Sample04.class, "NZ_PCH29_538M_Channel_ONE_Rating-AO-0_020912-1.xml"));

		// process
		sample.execute();
	}

	public void execute() {
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
