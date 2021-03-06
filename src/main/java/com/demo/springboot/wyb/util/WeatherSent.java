package com.demo.springboot.wyb.util;

import java.util.List;

public class WeatherSent {
    /**
     * 发送的内容
     * @param cityId "101010200" eg
     * @param cityName "海淀" eg
     * @return
     */
    public static String sendContent(String cityId, String cityName){
        List<String> list = WeatherPush.getWeather(cityId, cityName);
        StringBuffer sb = new StringBuffer();
        for(String s : list){
            sb.append(s+ "\n");
        }
        return  sb.toString().substring(
                0,sb.indexOf("时") -2)+ sb.toString().substring(
                sb.indexOf("时")+ 1,
                sb.length());
    }

    public static void main(String[] args) {
        String s = sendContent("101010200", "海淀");
        System.out.println(s);
    }

}
