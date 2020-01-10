package com.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class jsoup {
    //jsoup爬虫示例
    public static void main(String[] args) throws Exception {
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA";
        Document doc = Jsoup.connect(url).get();
        //Document doc = Jsoup.parse(new URL(url).openStream(), "gbk", url);
        Elements elements = doc.getElementsByClass("gl-item");
        for (Element headline : elements) {
//            Phone phone = new Phone();
//            phone.setSrc(headline.select("div[class='p-img']").select("img").attr("source-data-lazy-img"));
//            phone.setName(headline.select("div[class='p-name p-name-type-2']").select("em").text());
//            phone.setPrice(headline.select("div[class='p-price']").select("i").text());
//            phoneMapper.InsertInfo(phone);
        }
    }

}
