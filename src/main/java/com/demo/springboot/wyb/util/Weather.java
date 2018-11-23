package com.demo.springboot.wyb.util;

/**
 * @author: user
 * @Date: 2018/11/19
 * @Description:
 */
public enum Weather {
    /**
     *文件
     */
    FILE("文件"),

    /**
     *
     */
    EDIT("编辑");

    /**
     *
     */
    private String name;

    /**
     *
     * @param name
     */
    private Weather(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(Weather.FILE);
    }
}
