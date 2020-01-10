package com.spider.controler;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class DownScource {
    public static void main(String[] args) throws FileNotFoundException {
    	System.setProperty("webdriver.chrome.driver", "C:/ASoftWare/Programming/driver1/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bilibili.com/v/technology/speech_course/?spm_id_from=333.109.b_7072696d6172795f6d656e75.49#/");
        String str = driver.getPageSource();
        driver.quit();
        File file = new File("scores.txt");
 		if (file.exists()) { // 检查scores.txt是否存在
 			System.out.println("File already exists");
 			System.exit(1); // 如果存在则退出程序
 		}
 		// 如果不存在则创建一个新文件
 		try (PrintWriter output = new PrintWriter(file);) {
 			output.print(str);
 			// 若没有try-with-resources结构则必须使用 close() 关闭文件，否则数据就不能正常地保存在文件中
 			// output.close();
 		}
    }
}