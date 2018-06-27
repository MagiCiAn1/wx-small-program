package com.srg.exchange.util;


import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by srg
 *
 * @date 2018/6/2
 */
public class WXOpenidUtil {
    public static String oauth2GetOpenid(String code) {

        ResourceBundle resource = ResourceBundle.getBundle("datasource");
        String appId = resource.getString("wx.appId");
        String appSecret = resource.getString("wx.appSecret");

        //授权（必填）
        String grant_type = resource.getString("wx.grantType");
        //URL
        String requestUrl = resource.getString("wx.url");
        //请求参数
        String params = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        String Openid =String.valueOf(json.get("openid"));
        //System.out.println(Openid);
        return Openid;
    }

    public static String get(String url,String param){
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            //System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
