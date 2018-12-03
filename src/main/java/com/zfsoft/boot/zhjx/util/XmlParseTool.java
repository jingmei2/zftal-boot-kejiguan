package com.zfsoft.boot.zhjx.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;

public class XmlParseTool {

	public static Map<String, String> doXMLParse(String inputinfo) throws JDOMException, IOException {
		if (null == inputinfo || "".equals(inputinfo)) {
			return null;
		}
		Map<String, String> m = new HashMap<String, String>();
		InputStream in = new ByteArrayInputStream(inputinfo.getBytes("utf-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			m.put(k, v);
		}
		in.close();
		return m;
	}

	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}

	public static String RequestXml2String(Map<String, String> params, boolean iscdata) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = params.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("CreateTime".equalsIgnoreCase(k) || "ArticleCount".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			} else {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
