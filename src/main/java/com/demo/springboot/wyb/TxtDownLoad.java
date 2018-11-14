package com.demo.springboot.wyb;

import com.demo.springboot.wyb.util.DeleteJS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class TxtDownLoad {public static void main(String[] args) throws IOException {
    String baseUrl = "https://www.i7wx.com/book/51/51685/";
    Map<String,String> headers = new HashMap<String,String>();
    headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
    headers.put("Accept-Encoding", "gzip, deflate, br");
    headers.put("Accept-Language", "zh-CN,zh;q=0.9");
    headers.put("Cache-Control", "max-age=0");
    headers.put("Connection", "keep-alive");
    headers.put("Host", "www.xiaoshuoli.com");
    headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3464.0 Safari/537.36");
    Document document = Jsoup.connect(baseUrl).headers(headers).get();
    Elements chapters = document.getElementById("readerlist").getElementsByTag("li");
    int count = 0;
    for(Element element : chapters){
        count ++;
        System.out.println(" 第 "+count+ "次循环");
        if(count < 227){
            continue;
        }
        /*if(count <= 12){
            continue;
        }*/
        Element chapterA = element.getElementsByTag("a").get(0);
        String title = chapterA.text();
        String url = chapterA.attr("href");
        String content = getContent(baseUrl+url,headers);
        String fileName = "e:/yzrd.txt";
        FileWriter fw = new FileWriter(fileName, true);
        fw.write("\n");
        fw.write(title);
        fw.write("\n");
        fw.write(content);
        fw.flush();
        fw.close();
        try {
            Thread.sleep(new Random().nextInt(5 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public static String getContent(String url,Map<String,String> headers){
    StringBuffer result = new StringBuffer();
    BufferedReader br = null;
    try {
        Document document = Jsoup.connect(url).headers(headers).get();
        String datas = DeleteJS.delHTMLTag2(document.getElementById("content").toString().replace("&nbsp;", "").replace("<br>", ""));
        br = new BufferedReader(new StringReader(datas));
        String str = null;
        while((str = br.readLine()) != null) {
            if(!"".equals(str.trim())) {
                result.append(str);
                result.append("\n");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if(br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result.toString();
}}
