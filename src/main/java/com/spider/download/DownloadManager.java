package com.spider.download;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DownloadManager {
    public static String getHtmlSource(String url) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/ASoftWare/Programming/chromedriver/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        String str = driver.getPageSource();
        driver.quit();
        return str;
    }

    public static String getJSScource(String url) {
        System.setProperty("webdriver.chrome.driver", "C:/ASoftWare/Programming/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Cookie cookie1 = new Cookie("LIVE_BUVID", "AUTO4915605764083735", "/", null);
        Cookie cookie2 = new Cookie("buvid3", "C42D6FA5-6136-422E-A274-61C80BB8C71A110249infoc", "/", null);
        driver.get(url);
        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.get(url);
        String s = driver.getPageSource();
        driver.quit();
        return s;
    }

    public static void main(String[] args) {
        String url = "http://api.bilibili.com/playurl?callback=callbackfunction&aid=56712272&page=\"+page+\"&platform=html5&quality=1&vtype=mp4&type=jsonp";
        System.out.println(getJSScource(url));
    }
}