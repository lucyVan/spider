package com.spider.download;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class CharsetCheckUtil {
	public static String getCharset(String url) throws Exception{
		URL urlobj = new URL(url);
		URLConnection urlConn =  urlobj.openConnection();
		Map<String,List<String>> allHeaderMap = urlConn.getHeaderFields();
		List<String> str = allHeaderMap.get("Content-Type");
		for (String string : str) {
			string = string.toLowerCase();
			if(string.contains("utf-8")){
				return "utf-8";
			}
			if(string.contains("gbk")){
				return "gbk";
			}
		}
		return null;
	}
}
