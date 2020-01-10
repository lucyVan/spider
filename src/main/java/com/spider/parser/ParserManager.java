package com.spider.parser;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.spider.download.DownloadManager;
import com.spider.pojo.CoursesResultEntity;
import com.spider.utils.RandTime;

public class ParserManager {

	public static List<CoursesResultEntity> parseHtml(String htmlSource) throws Exception {
		List<CoursesResultEntity> StrList = new ArrayList<CoursesResultEntity>();
		String regex = "<ul\\sclass=\"vd-list mod-2[\\s\\S]*</ul>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlSource);
		String Source = null;
		if (matcher.find()) {
			Source = matcher.group();
		} else {
			return null;
		}
		regex = "<li>[\\s\\S]*?</li>";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(Source);
		while (matcher.find()) {
			regex = "<div class=\"r\"><a\\shref[\\s\\S]*?</div>";
			Pattern pattern2 = Pattern.compile(regex);
			Matcher matcher2 = pattern2.matcher(matcher.group());
			while (matcher2.find()) {
				CoursesResultEntity result = new CoursesResultEntity();
				String regexx = "//www.bilibili.com/video/[\\s\\S]*?/";
				Pattern pattern3 = Pattern.compile(regexx);
				Matcher matcher3 = pattern3.matcher(matcher2.group());
				if (matcher3.find()) {
					String s = matcher3.group();
					StringBuilder sb = new StringBuilder(s);
					sb.insert(6, "i");
					s = sb.insert(0, "http:").toString();
					WebClient webClient = new WebClient();
					webClient.getOptions().setJavaScriptEnabled(false);
					HtmlPage page = webClient.getPage(s);
					s = page.asXml().toString();
//					System.out.println(s.contains("parseVideo"));
//					File file = new File("scores.txt");
//			 		if (file.exists()) { // 检查scores.txt是否存在
//			 			System.out.println("File already exists");
//			 			System.exit(1); // 如果存在则退出程序
//			 		}
//			 		// 如果不存在则创建一个新文件
//			 		try (PrintWriter output = new PrintWriter(file);) {
//			 			output.print(s);
//			 			// 若没有try-with-resources结构则必须使用 close() 关闭文件，否则数据就不能正常地保存在文件中
//			 			// output.close();
//			 		}
					String regexxx = "//api.bilibili.com[\\s\\S]*?;";
					Pattern pattern4 = Pattern.compile(regexxx);
					Matcher matcher4 = pattern4.matcher(s);
					if(matcher4.find()){
						s = matcher4.group();
					}
//					System.out.println(55);
					s = s.substring(0,s.length()-3);
					sb = new StringBuilder(s);
					s = sb.insert(0, "http:").toString();
					s = DownloadManager.getJSScource(s);
					regexxx = "http:[\\s\\S]*?\"";
					pattern4 = Pattern.compile(regexxx);
					matcher4 = pattern4.matcher(s);
					if(matcher4.find()){
						String ss = matcher4.group();
						ss = ss.replace("&amp;", "&");
						ss = ss.substring(0,ss.length()-1);
						ss = ss.replace("\\/","/");
						result.setCurl(ss);
					}
					regexxx = "img\":\"https:[\\s\\S]*?.jpg";
					pattern4 = Pattern.compile(regexxx);
					matcher4 = pattern4.matcher(s);
					if(matcher4.find()){
						String ss = matcher4.group();
						ss = ss.substring(6);
						ss = ss.replace("\\/","/");
						result.setCimage(ss);
					}
				}
				regexx = "title=\"[\\s\\S]*?\"";
				pattern3 = Pattern.compile(regexx);
				matcher3 = pattern3.matcher(matcher2.group());
				if (matcher3.find()) {
					String s = matcher3.group();
					result.setCname(s.substring(7, s.length() - 1));
				}
				regexx = "<div class=\"v-desc\">[\\s\\S]*?</div>";
				pattern3 = Pattern.compile(regexx);
				matcher3 = pattern3.matcher(matcher2.group());
				if (matcher3.find()) {
					String s = matcher3.group();
					result.setCinfo(s.substring(20, s.length() - 6));
				}
				Random ra = new Random();
				result.setTid(ra.nextInt(20) + 1);
				result.setSid(ra.nextInt(8) + 1);
				result.setCtime(RandTime.randomDate("2019-01-01", "2019-06-30"));
				StrList.add(result);
			}
		}
		return StrList;

	}

	public static void main(String[] args) throws Exception {
		String url = "https://www.bilibili.com/v/technology/speech_course/?spm_id_from=333.827.b_7072696d6172795f6d656e75.49#/all/default/0/2/";
		String htmlSource = DownloadManager.getHtmlSource(url);
		List<CoursesResultEntity> resultList = parseHtml(htmlSource);
		for (CoursesResultEntity coursesResultEntity : resultList) {
			System.out.println(coursesResultEntity);
		}
	}
}
