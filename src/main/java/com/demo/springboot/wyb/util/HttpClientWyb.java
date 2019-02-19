package com.demo.springboot.wyb.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: user
 * @date: 2018/12/14
 * @description:
 */
public class HttpClientWyb {

    /**
     * post请求(用于key-value格式的参数)
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params){

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
/*            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }*/
            //request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){	//请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            }
            else{	//
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();

            return null;
        }
    }

    // 构建唯一会话Id
    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String url = "http://10.218.36.19/api/v1/app-categories/?q=isKey=='是'&page=0&pageSize=9999";
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("q","isKey=='是'");
//        params.put("page","0");
//        params.put("pageSize","9999");
//        String result = doPost(url,params);
//        System.out.println(result);
        doGet();
    }

    public static String doGet(){
        CloseableHttpClient httpCilent2 = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet2 = new HttpGet("http://10.218.36.19/api/v1/app-categories/?q=isKey=='是'&page=0&pageSize=9999");
        httpGet2.setConfig(requestConfig);
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpCilent2.execute(httpGet2);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
                System.out.println(srtResult);
            }else if(httpResponse.getStatusLine().getStatusCode() == 400){
                System.out.println("server throw 400");
            }else if(httpResponse.getStatusLine().getStatusCode() == 500){
                System.out.println("server throw 500");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpCilent2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public static void doPost(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(1000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).setRedirectsEnabled(true).build();

        HttpPost httpPost = new HttpPost("http://10.218.36.19/api/v1/app-categories");
        //设置超时时间
        httpPost.setConfig(requestConfig);
        //装配post请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        //list.add(new BasicNameValuePair("age", "20"));  //请求参数
        //list.add(new BasicNameValuePair("name", "zhangsan")); //请求参数
        list.add(new BasicNameValuePair("q", "isKey=='是'")); //请求参数
        list.add(new BasicNameValuePair("page", "0"));  //请求参数
        list.add(new BasicNameValuePair("pageSize", "9999")); //请求参数

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
            //设置post求情参数
            httpPost.setEntity(entity);
            System.out.println("请求" + httpPost.getURI().toString());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String strResult = "";
            if(httpResponse != null){
                System.out.println(httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                    strResult = "Error Response: " + httpResponse.getStatusLine().toString();
                } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                    strResult = "Error Response: " + httpResponse.getStatusLine().toString();
                } else {
                    strResult = "Error Response: " + httpResponse.getStatusLine().toString();
                }
            }else{

            }
            System.out.println(strResult);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close(); //释放资源
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
