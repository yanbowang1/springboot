package com.demo.springboot.conditionaldemo;

/**
 * @Auther: user
 * @Date: 2018/11/7
 * @Description:
 */
public class WindowsListService implements ListService{
    @Override
    public String showListcmd() {
        return "dir";
    }
}
