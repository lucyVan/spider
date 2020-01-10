package com.spider.controler;

import com.spider.download.DownloadManager;
import com.spider.parser.ParserManager;
import com.spider.persistence.PersistenceManager;
import com.spider.pojo.CoursesResultEntity;
import com.spider.schedule.ScheduleManager;
import com.spider.ui.UIManager;

import java.util.List;

public class SystemControler {
    public static void main(String[] args) throws Exception {
        //拿到种子url
        List<String> seedUrl = UIManager.getSeedUrlFromFile();
//		String url = "https://www.bilibili.com/v/technology/speech_course/?spm_id_from=333.827.b_7072696d6172795f6d656e75.49#/all/default/0/1/";
        for (String url : seedUrl) {
            //交给任务调度层，用于后续的下载分发
            ScheduleManager.addSeedUrlToTaskList(url);
            //下载拿到的任务url，没有则返回空
            String htmlSource = DownloadManager.getHtmlSource(url);
            //解析下载下来的htmlsource
            if (htmlSource != null) {
                List<CoursesResultEntity> resultEntityList = ParserManager.parseHtml(htmlSource);
                PersistenceManager.persist(resultEntityList);
                try {
                    Thread.sleep(600000);
                } catch (Exception e) {
                    System.exit(0);//退出程序
                }
            } else {
                System.out.println("没有找到要解析的htmlSorce,本轮任务结束！");
            }
        }
    }
}
