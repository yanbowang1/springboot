package com.demo.springboot.wyb;

public class Robot {
    public static String AiMain(String str){
        str = str.replace("吗", "");
        str = str.replace("?", "");
        str = str.replace("!", "");
        return str;
    }
}
