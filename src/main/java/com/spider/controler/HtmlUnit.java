 package com.spider.controler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
  * Created by xuyh at 2017/11/6 14:03.
  */
 public class HtmlUnit{
     @Test
     public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
    	 WebClient wc=new WebClient(BrowserVersion.CHROME);
    	 wc.setJavaScriptTimeout(5000);  
         wc.getOptions().setUseInsecureSSL(true);//接受任何主机连接 无论是否有有效证书  
         wc.getOptions().setJavaScriptEnabled(true);//设置支持javascript脚本   
         wc.getOptions().setCssEnabled(false);//禁用css支持  
         wc.getOptions().setThrowExceptionOnScriptError(true);//js运行错误时不抛出异常  
         wc.getOptions().setTimeout(5000);//设置连接超时时间  
         wc.getOptions().setDoNotTrackEnabled(false);   
         HtmlPage page=wc.getPage("https://www.bilibili.com/video/av16699594");  
         String pagexml = page.asXml();
         System.out.println(page.asXml());
         File file = new File("scores.txt");
         System.out.println(1);
 		if (file.exists()) { // 检查scores.txt是否存在
 			System.out.println("File already exists");
 			System.exit(1); // 如果存在则退出程序
 		}
 		// 如果不存在则创建一个新文件
 		try (PrintWriter output = new PrintWriter(file);) {
 			output.print(pagexml);
 			// 若没有try-with-resources结构则必须使用 close() 关闭文件，否则数据就不能正常地保存在文件中
 			// output.close();
 		}
     }
 }