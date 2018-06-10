package tw.com.seesawin.readfile.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import tw.com.seesawin.readfile.util.JsonUtils;

public class XxmlToJson {

	private static final String FILENAME = "C:\\test\\test01.xml";
	private static final int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (FileInputStream inputStream = new FileInputStream(FILENAME)) {
			String everything = IOUtils.toString(inputStream);
			// System.out.println(everything);

			JSONObject xmlJSONObj = XML.toJSONObject(everything);
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			// System.out.println(jsonPrettyPrintString);

			Map<String, Object> retMap = JsonUtils.fromJson(jsonPrettyPrintString);

//			Set<String> sets = retMap.keySet();
//			for (String s : sets) {
//				System.out.println(s);
//			}
//
//			System.out.println("11111111111");
//
//			Map map2 = (Map) retMap.get("MPEG-TABLES");
//			Set<String> sets2 = map2.keySet();
//			for (String s2 : sets2) {
//				System.out.println(s2);
//			}
//
//			System.out.println("222222222");
//
//			Map map3 = (Map) map2.get("PMTs");
//			Set<String> sets3 = map3.keySet();
//			for (String s3 : sets3) {
//				System.out.println(s3);
//			}
			
			// 指定路徑取出list
//			List list4 = (List) map3.get("CHANNEL");
			List list4 = getList(retMap, "/MPEG-TABLES/PMTs/CHANNEL");
			
			System.out.println("CHANNEL size : " + list4.size());
			for(int i = 0; i < list4.size(); i++) {
				System.out.println("---------------------CHANNEL " + i + " -----------------------");
				Map list4Map = (Map) list4.get(i);
				double s = (double) list4Map.get("SERVICE-NUMBER");
				System.out.println("SERVICE-NUMBER : " + s);
				
				Object obj = list4Map.get("ELEMENTARY-STREAM");
				
				if(obj instanceof Map) {
					System.out.println("not list");
				} else if (obj instanceof List) {
					List list4innerList1 = (List) list4Map.get("ELEMENTARY-STREAM");
					System.out.println("ELEMENTARY-STREAM size : " + list4innerList1.size());
					
					for(int j = 0; j < list4innerList1.size(); j++) {
						Map innrtMap4 = (Map) list4innerList1.get(j);
						String result = (String) innrtMap4.get("AUDIO-LANGUAGE");
						System.out.println("AUDIO-LANGUAGE : " + result);
					}
				}
				
			}

		} catch (JSONException je) {
			System.out.println(je.toString());
		}

	}
	
	public static List getList(Map<String, Object> retMap, String path) {
		List resultList = new ArrayList();
		
		String[] tagArr = path.split("/");
		Map tmpMap = new HashMap();
		for(int i = 1; i < tagArr.length; i++) {
			System.out.println(i + " : " + tagArr [i]);
			if(i == 1) {
				tmpMap = (Map) retMap.get(tagArr[i]);
				continue;
			}

			if(i == (tagArr.length - 1)) {
				resultList = (List) tmpMap.get(tagArr[i]);
			}else {
				tmpMap = (Map) tmpMap.get(tagArr[i]);
			}
		}
		return resultList;
	}

}
