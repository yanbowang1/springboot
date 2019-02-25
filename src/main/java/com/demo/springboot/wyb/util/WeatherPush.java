package com.demo.springboot.wyb.util;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * @author: user
 * @date: 2018/11/27
 * @description:
 */
public class WeatherPush {
    /**
     * 获取
     * @param cityId
     * @param cityName
     * @return 天气，温度， 风力， 风向， 穿衣， 空气质量， 建议；
     */
    public static List<String> getWeather(String cityId, String cityName){
        List<String> result = new ArrayList<String>();
        result.add(cityName + "天气");
        // 浏览器头
        Map<String,String> headers = TxtDownLoadNew.getBrowerInit();
        try {
            Document document = Jsoup.connect(
                    "http://www.weather.com.cn/weather/"
                            + cityId
                            + ".shtml"
                    ).headers(headers).get();
            Elements bodyElements = document.getElementsByTag("body");
            getWeatherFromWebPage(result, bodyElements);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void getWeatherFromWebPage(List<String> result, Elements bodyElements) {
        // 当前温度：
        //Elements tagTodyss = bodyElements.getElementsByClass("con today clearfix");
        //String nowTips = bodyElements
        for(Element e : bodyElements){
            Elements tagTody = e.getElementsByClass("sky skyid lv4 on");
            Set<String> windSet = new HashSet<>();
            String nowTips = e.getElementsByClass("con today clearfix").get(0)
                    .getElementsByClass("left fl").get(0).
                            getElementById("7d").getElementById("hidden_title").val();
            Element infoss= e.getElementsByClass("con today clearfix").get(0)
                    .getElementsByClass("left fl").get(0).getElementById("today");
            Element infos = null;
            for (Element ein : tagTody){
                Elements nowWeather = ein.getElementsByClass("sk");
                for(Element einItem : nowWeather) {
                    einItem.getElementsByClass("em").tagName("span").text();
                    System.out.println();
                }
            }

            result.add(" " + nowTips);
            for(Element e1 : tagTody) {
                Elements info = e1.getElementsByTag("p");
                StringBuffer windDirection = new StringBuffer();
                // 天气 温度 风力
                for(Element e2 : info){
//                    if(e2.hasClass("wea")){
//                        result.add(" 天气: " + e2.text());
//                    }
//                    if(e2.hasClass("tem")){
//                        result.add(" 温度: " + e2.text());
//                    }
//                    if(e2.hasClass("win")){
//                        windDirection.append(e2.text());
//                    }
                }
//                result.add(" 风力: " + windDirection.toString().replace("<","小于")
//                        .replace(">", "大于"));
                Elements winds = e1.getElementsByTag("span");
                // 风向
                for(Element wind : winds){

                    if(!StringUtil.isBlank(wind.attr("title"))){
                        windSet.add(wind.attr("title"));
                        //result.add(" 风向:" + wind.attr("title"));
                    }

                }
            }
            //StringBuffer winds = new StringBuffer(" 风向：");
//            if(windSet.size() > 1){
//                for (String s : windSet){
//                    winds.append(s+ "|");
//                }
//                if (winds.toString().indexOf("|") == 2){
//                    result.add(winds.toString().substring(0, winds.toString().length())
//                            .replace("|", "转"));
//                }
//            }else {
//                for (String s : windSet){
//                    winds.append(s+ "|");
//                }
//            }
//            for (String s : windSet){
//                winds.append(s+ "|");
//            }
//            if (winds.toString().indexOf("|") == 2){
//                result.add(winds.toString().substring(0, winds.toString().length())
//                        .replace("|", "转"));
//            }else{
//                result.add(winds.toString().replace("|", ""));
//            }
            // 穿衣
            //getClothesInformation(result, e);
            // 空气标准，是否户外运动建议
            getAirInformation(result, e);
        }
    }

    public static void getClothesInformation(List<String> result, Element e) {
        Elements clothes = e.getElementsByTag("li");
        for(int i = 0; i< clothes.size(); i++){
            //Element e3 = ;
            if(clothes.get(i).getElementById("chuanyi") != null){
                result.add(" 穿衣建议:" + clothes.get(i).getElementById("chuanyi")
                        .getElementsByTag("p").text());
            }
        }
    }

    public static void getAirInformation(List<String> result, Element e) {
        Element tagAir = e.getElementsByClass("li6").get(0);
        result.add(" 空气质量:" + tagAir.getElementsByTag("span").get(0).text());
        //result.add(" 建议:" + tagAir.getElementsByTag("p").get(0).text());
    }

    public static String List2String(String cityNo){
        List<String> list = getWeather("101010200", "海淀");
        StringBuffer bf = new StringBuffer();
        for(String s : list){
            bf.append(s + "/n");
        }
        return  bf.toString();
    }
    public static void main(String[] args) {
        List<String> list = getWeather("101010200", "海淀");
        for(String s : list){
            System.out.println(s.replace("<","小于"));
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<String> list1 = getWeather("101080201", "包头");
        for(String s : list1){
            System.out.println(s);
        }
    }
}
