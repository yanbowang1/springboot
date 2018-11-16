package com.demo.springboot.dyttresourcedownload;

import com.demo.springboot.wyb.util.TxtDownLoadNew;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: user
 * @Date: 2018/11/16
 * @Description:
 */
public class DyttUtil {
    /**
     *
     * @param childrenUrl
     * @param movieName
     * @return
     */
    public static List<String> openChildrenPage(String childrenUrl, String movieName) {
        List<String> result = new ArrayList<String>();
        result.add(movieName);
        String baseUrl = "http://www.ygdy8.net" + childrenUrl;
        Map<String,String> headers = TxtDownLoadNew.getBrowerInit();
        Document document = null;
        try {
            document = Jsoup.connect(baseUrl).headers(headers).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element chapters = document.getElementById("Zoom");
        Elements br = chapters.getElementsByTag("p").get(0).getElementsContainingText("IMDb");
        for(String s : br.eachText()){
            int flag = 0;
            if(!s.contains("IMDb")){
                flag = 1;
            }else if (!s.contains("豆瓣评分")){
                flag = 2;
            }else if(!s.contains("IMDb") && !s.contains("豆瓣评分")){
                flag = 12;
            }
            String[] arr= s.split("◎");
            for(int i = 0; i < arr.length; i++){
                if(flag == 1){
                    result.add("");
                }
                if(arr[i].contains("IMDb")){
                    result.add(arr[i]);
                }
                if(arr[i].contains("豆瓣评分")){
                    result.add(arr[i]);
                }
                if(flag == 2){
                    result.add("");
                }
                if(flag == 12){
                    result.add("");
                    result.add("");
                }
            }
        }
        // link
        String links = chapters.getElementsByTag("td").get(0).getElementsByTag("a").text();
        result.add(links);
        return result;
    }

    public static void main(String[] args) {
        openChildrenPage("/html/gndy/dyzz/20181114/57774.html","haha");
    }
}
