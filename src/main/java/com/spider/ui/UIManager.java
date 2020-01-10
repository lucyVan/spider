package com.spider.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UIManager {
	public static List<String> getSeedUrlFromFile() throws Exception{
		String seedFilePath="seeds.txt";
		String charset = "utf-8";
		List<String> seedUrlList = UIManager.getFileLineList(seedFilePath, charset);
		return seedUrlList;
	}
	
	public static List<String> getFileLineList(String filePath,String charset) throws Exception{
		File fileObj = new File(filePath);
		FileInputStream fis = new FileInputStream(fileObj);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		List<String> lineList = new ArrayList<String>();
		String tmp = null;
		while((tmp = br.readLine())!=null){
			tmp = tmp.trim();
			if(tmp.length()>0){
				lineList.add(tmp);
			}
		}
		br.close();
		return lineList;
	}
}
