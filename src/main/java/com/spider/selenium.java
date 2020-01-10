package com.spider;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class selenium {
    //selenium:模拟浏览器操作
    //tess4j: ORC文字识别
    public static void main( String[] args ) throws TesseractException {
        System.out.println( "Hello World!" );
        System.setProperty("webdriver.chrome.driver", "C:/ASoftWare/Programming/chromedriver/chromedriver.exe");

//        File imageFile = new File("c:\\1.png");
//        ITesseract instance = new Tesseract();
//        instance.setDatapath("C:\\Dev\\tessdata");
//        String price = instance.doOCR(imageFile);
//        System.out.println(price);



        String url = "https://maoyan.com/films/1279731";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);

        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[2]/div/span[1]"));
//        String price = element.getText();
        TakesScreenshot takesScreenShot = (TakesScreenshot) element;
        File imageFile = takesScreenShot.getScreenshotAs(OutputType.FILE);

        driver.close();


        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Dev\\tessdata");
        String price = instance.doOCR(imageFile);
        System.out.println(price);


        System.out.println("END.");





//        WebElement kwElement = driver.findElement(By.id("kw"));
//        kwElement.sendKeys("天津");
//        WebElement btnSend = driver.findElementById("su");
//        String btnSendText = btnSend.getText();
//        System.out.println(btnSendText);
//        btnSend.click();

    }
}
