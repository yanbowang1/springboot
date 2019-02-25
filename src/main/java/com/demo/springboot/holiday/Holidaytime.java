package com.demo.springboot.holiday;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Holidaytime {
    /**
     * 国家法定节假日List
     * @return
     */
    public static List<String> get2019Holiday(){
        List<String> day = new ArrayList<>();
        // 清明节
        day.add("04-05");
        day.add("04-06");
        day.add("04-07");

        // 劳动节
        day.add("05-01");

        // 端午节
        day.add("06-07");
        day.add("06-08");
        day.add("06-09");

        // 中秋节
        day.add("09-13");
        day.add("09-14");
        day.add("09-15");

        // 国庆节
        day.add("10-01");
        day.add("10-02");
        day.add("10-03");
        day.add("10-04");
        day.add("10-05");
        day.add("10-06");
        day.add("10-07");
        return day;
    }

    /**
     * 调休日期List
     * @return
     */
    public static List<String> workingDay(){
        List<String> holiday = new ArrayList<>();
        holiday.add("09-29");
        holiday.add("10-12");
        return  holiday;
    }

    /**
     * 判断是否是假期
     * @return
     */
    public static boolean isHoliday(){
        // 日期 01-01
        String date = LocalDateTime.now().toString().substring(4,9);
        // 星期几
        String week = LocalDateTime.now().getDayOfWeek().toString();
        // 判断调休日
        for(String workingDay : workingDay()) {
            if(workingDay.equals(date)){
                return false;
            }
        }

        // 判断法定假期
        for(String workingDay : get2019Holiday()) {
            if(workingDay.equals(date)){
                return true;
            }
        }

        // 判断周末
        if("Sunday".toUpperCase().equals(date)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Holidaytime holidaytime = new Holidaytime();
        System.out.println(holidaytime.isHoliday());
    }
}
