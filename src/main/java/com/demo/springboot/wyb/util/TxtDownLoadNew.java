package com.demo.springboot.wyb.util;

import com.demo.springboot.wyb.TxtDownLoad;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TxtDownLoadNew {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://www.i7wx.com/book/31/31679/";
        Map<String,String> headers = getBrowerInit();
        Document document = Jsoup.connect(baseUrl).headers(headers).get();
        Elements chapters = document.getElementById("readerlist").getElementsByTag("li");
        int count = 0;
        List<Integer> chapterList = new ArrayList<Integer>();
        Map<Integer, String> titleMap = new HashMap<Integer, String>();
        Map<Integer, String> contentMap = new HashMap<Integer, String>();
        for(Element element : chapters){
            count ++;
            System.out.println(" 第"+count+ "次循环");
            if(count < 2){
                continue;
            }
            Element chapterA = element.getElementsByTag("a").get(0);
            String title = chapterA.text();
            String url = chapterA.attr("href");
            String content = TxtDownLoad.getContent(baseUrl+url,headers);
            chapterList.add(count);
            titleMap.put(count, title);
            contentMap.put(count, content);
        }
        String fileName = "e:/jf.txt";
        FileWriter fw = new FileWriter(fileName, true);
        for (int i :chapterList){
            fw.write("\n");
            fw.write(titleMap.get(i));
            fw.write("\n");
            fw.write(contentMap.get(i));
        }
        fw.flush();
        fw.close();
    }

    /**
     * 浏览器 头设置
     * @return
     */
    public static Map<String, String>getBrowerInit(){
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "www.xiaoshuoli.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3464.0 Safari/537.36");
        return headers;
    }
}
