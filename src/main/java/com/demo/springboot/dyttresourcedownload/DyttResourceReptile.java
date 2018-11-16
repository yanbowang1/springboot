package com.demo.springboot.dyttresourcedownload;

import com.demo.springboot.wyb.util.TxtDownLoadNew;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: user
 * @Date: 2018/11/16
 * @Description: dytt resource now
 * movie name + sources （欧美评分/豆瓣评分）
 */
public class DyttResourceReptile {

    public static void dyttMovieLink() throws Exception {
        // 首页baseUrl
        String baseUrl = "http://www.ygdy8.net/html/gndy/oumei/index.html";
        Map<String,String> headers = TxtDownLoadNew.getBrowerInit();
        Document document = Jsoup.connect(baseUrl).headers(headers).get();
        Elements chapters = document.getElementsByClass("co_content8");
        Element e = chapters.get(0);
        Elements list = e.getElementsByTag("b");//.tagName("href").text();
        List<List<String>> lists = new ArrayList<List<String>>();
        for(Element e1 : list){
            Element children = e1.getElementsByTag("a").get(1);
            // movie nam
            String name = children.getElementsByTag("a").text();
            // movie link address
            String link = children.attr("href");
            lists.add(DyttUtil.openChildrenPage(link,
                    name));
        }
        ExcelDownLoad.writeWithHead("欧美电影", lists);
    }

    public static void main(String[] args) throws Exception {
        dyttMovieLink();
    }
}
