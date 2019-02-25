package com.swjtu;

import com.swjtu.lang.Lang;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        Querier<AbstractTranslator> querierTrans = new Querier<>();                   // 获取查询器

        querierTrans.setParams(Lang.RU, Lang.ZH, "");    // 设置参数

        querierTrans.attach(new GoogleTranslator());                                  // 向查询器中添加 Google 翻译器

        List<String> result = querierTrans.execute();                                 // 执行查询并接收查询结果


    }
    public static void translate(String s) {
        Querier<AbstractTranslator> querierTrans = new Querier<>();                   // 获取查询器

        querierTrans.setParams(Lang.RU, Lang.ZH, s);    // 设置参数

        querierTrans.attach(new GoogleTranslator());                                  // 向查询器中添加 Google 翻译器

        List<String> result = querierTrans.execute();

        for (String str : result) {
            System.out.println(str);
        }
    }
}
