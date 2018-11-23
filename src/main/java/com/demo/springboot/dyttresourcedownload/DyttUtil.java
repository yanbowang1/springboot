package com.demo.springboot.dyttresourcedownload;

import com.demo.springboot.wyb.util.TxtDownLoadNew;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: user
 * @Date: 2018/11/16
 * @Description:
 */
@Slf4j
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
                String[] arr= s.split("◎");
                if(s.contains("IMDb评分")){
                    for(String ss : arr){
                        if(ss.contains("IMDb评分")){
                            result.add(ss.replace("IMDb评分", "").split("/")[0]);
                        }
                    }
                }else{
                    result.add("");
                }
                if (s.contains("豆瓣评分")){
                    for(String ss : arr){
                        if(ss.contains("豆瓣评分")){
                            result.add(ss.replace("豆瓣评分", "").split("/")[0]);
                        }
                    }
                }else{
                    result.add("");
                }
            }
            if(br.size() == 0){
                result.add("");
                result.add("");
            }
            // 链接
            String links = chapters.getElementsByTag("td").get(0).getElementsByTag("a").text();
            result.add(links);
            return result;
    }

    /**
     *
     * @param childrenUrl
     * @param movieName
     * @return
     */
    public static void openChildrenPageNew(String childrenUrl, String movieName, CountDownLatch latch) {
        Lock lock = new ReentrantLock();
        try{
            lock.lock();
            Thread.sleep(5000);
            DyttResourceReptile.lists.add(openChildrenPage(childrenUrl, movieName));
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            latch.countDown();
        }
    }


    /**
     * 获取栏目链接
     * @param startUrl
     */
    public static String findCommonLink(String startUrl, Map<String, String> headers){
        Document document = null;
        try {
            document = Jsoup.connect(startUrl).headers(headers).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取该专题电影总页数
        Elements pageNum = document.getElementsByClass("x");
        String pageTatal = "";
        for(Element item : pageNum){
            if(item.text().contains("条记录")){
                String[] arr = item.getElementsByTag("a").get(0)
                        .attr("href").split("_");
                pageTatal = arr[0] + "_" + arr[1] + "_";
            }
        }
        return pageTatal;
    }

    /**
     * 获取栏目总页数
     * @param document
     * @return
     */
    public static int getColumnTotalPage(Document document){
        // 获取该专题电影总页数
        Elements pageNum = document.getElementsByClass("x");
        String pageTotal = "";
        for(Element item : pageNum){
            if(item.text().contains("条记录")){
                pageTotal = item.text().split("/")[0]
                        .replace("共","")
                        .replace("页","");
                break;
            }
        }
        if("".equals(pageTotal)){
            return 1;
        }else{
            return Integer.parseInt(pageTotal);
        }
    }
    private static Lock lock = new ReentrantLock();
    /**
     * 获取页面中的链接目录
     */
    public static void getPageLinkAll(Document document, CountDownLatch latch){
        // 获取视频列表
        Elements chapters = document.getElementsByClass("co_content8");
        Element e = chapters.get(0);
        Elements list = e.getElementsByTag("b");
        List<List<String>> result = new ArrayList<>();
        List<String> movieName = new ArrayList<String>();
        List<String> movieLink = new ArrayList<String>();
        lock.lock();
        try{
            for(Element e1 : list){
                Element children = e1.getElementsByTag("a").get(1);
                // movie name
                DyttResourceReptile.nameList.add(children.getElementsByTag("a").text());
                // movie link address
                DyttResourceReptile.linkDetailsList.add(children.attr("href"));
            }
            log.info( DyttResourceReptile.nameList.size() + "++++++" + DyttResourceReptile.linkDetailsList.size());
        }finally{
            lock.unlock();// 释放锁
            latch.countDown();
        }
    }

    /**
     * 多线程获取电影链接页
     * @param document
     * @throws Exception
     */
    public static void dyttMovieLinkMultithreading(Document document, CountDownLatch latch) throws Exception {
        //latch.countDown();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.AbortPolicy());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                DyttUtil.getPageLinkAll(document, latch);
                //System.out.println(Thread.currentThread().getName());
            }
        });
        //log.info("活动数:" + executor.getActiveCount());
        //latch.countDown();
    }

    /**
     * 多线程获取电影详情页链接
     * @param childrenUrl
     * @param movieName
     * @param latch
     * @throws Exception
     */
    public static void dyttMovieChildrenMultithreading(String childrenUrl,
                                                       String movieName,
                                                       CountDownLatch latch) throws Exception {
        //latch.countDown();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.AbortPolicy());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                DyttUtil.openChildrenPageNew(childrenUrl, movieName, latch);
            }
        });
    }

    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<List<String>>();
        lists.add(openChildrenPage("/html/gndy/jddy/20181120/57797.html","haha"));
//        try {
//            ExcelDownLoad.writeWithHead("欧美电影", lists);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
