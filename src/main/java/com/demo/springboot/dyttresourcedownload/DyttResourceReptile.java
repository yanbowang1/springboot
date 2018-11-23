package com.demo.springboot.dyttresourcedownload;

import com.demo.springboot.wyb.util.TxtDownLoadNew;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author: user
 * @Date: 2018/11/16
 * @Description: dytt resource now
 * movie name + sources
 */
@Slf4j
public class DyttResourceReptile {
    public static List<List<String>> linkList = new ArrayList<List<String>>();
    public static List<String> nameList = new ArrayList<String>();
    public static List<String> linkDetailsList = new ArrayList<String>();
    public static int count = 0;
    public static List<List<String>> lists = new ArrayList<List<String>>();
    public static void dyttMovieLink() throws Exception {

        // 公有Url
        String baseUrl = "http://www.ygdy8.net";
        String startUrl = baseUrl  + "/html/gndy/oumei/"+ "index.html";
        // 浏览器头
        Map<String,String> headers = TxtDownLoadNew.getBrowerInit();
        Document document = Jsoup.connect(startUrl).headers(headers).get();
        /** 种类目录总页数 **/
        int pageTotalSize = 50;//DyttUtil.getColumnTotalPage(document);
        log.info("总页数获取结束");
        count = pageTotalSize;
        /**子页面链接**/
        String commonLink = DyttUtil.findCommonLink(startUrl, headers);
        log.info("获取子页面链接结束");

        CountDownLatch latch = new CountDownLatch(count);
        for(int i = 0; i < pageTotalSize; i++){
            if(i != 0){
                document = null;
                document = Jsoup.connect(baseUrl + "/html/gndy/oumei/"
                        + commonLink + (i + 1) + ".html").headers(headers).get();
            }
            DyttUtil.dyttMovieLinkMultithreading(document, latch);
        }
        while(latch.getCount() != 0){}
        log.info("目录整理完毕......");
        CountDownLatch latchChildren = new CountDownLatch(nameList.size());
        for(int i = 0; i < nameList.size(); i++){
            //log.info("数据采集进度：" + Double.valueOf(i)/Double.valueOf(nameList.size()));
            //lists.add(DyttUtil.openChildrenPage(linkDetailsList.get(i), nameList.get(i)));
            //Thread.sleep(5000);
            if(i%10 == 0){
                log.info("休眠...." + i + "  还有未执行：" + latchChildren.getCount());
                //Thread.sleep(5000);
            }
            //log.info(linkDetailsList.get(i)+"             " +nameList.get(i));
            DyttUtil.dyttMovieChildrenMultithreading(linkDetailsList.get(i), nameList.get(i),latchChildren);
        }
        while(latchChildren.getCount() != 0){}
        log.info("数据采集结束......");
        // 写入excel 表格
        ExcelDownLoad.writeWithHead("欧美电影", lists);
        log.info("结束写入文件" + lists.size());
    }

    public static void main(String[] args) {
        try {
            dyttMovieLink();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
