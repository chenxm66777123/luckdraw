package com.goldbee.luckdraw.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import net.sf.json.JSONObject;


/**
 * @Description 请求处理类
 * @author chenxm66777123
 * @Date 2018年12月20日
 * @version 1.0.0
 */
public class RequestUtils {

	
	/**
	 * @Description httpGet请求
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	//由于这类方法经常被用到,因此建议写在一个工具类里面,设置为静态方法,方便调用。
    //url表示请求链接,param表示json格式的请求参数
    public static String sendGet(String url, Object param) {
        String result = "";      
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setDoOutput(true);
            // 建立实际的连接
            connection.connect();
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常!" + e);
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
    
    /**
	 * @Description httpPost请求(form请求)
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
    public static String sendPostForForm(Map<String, String> params, String path,int timeOut) {
		int retryCount = 1;

		try {
			 // 构建请求参数  
	        StringBuffer sb = new StringBuffer();  
	        if (params != null) {  
	            for (Entry<String, String> e : params.entrySet()) {  
	                sb.append(e.getKey());  
	                sb.append("=");  
	                sb.append(e.getValue());  
	                sb.append("&");  
	            }  
	            sb.substring(0, sb.length() - 1);  
	        }  
			byte[] b = sb.toString().getBytes("UTF-8");
			HttpClient httpClient = new HttpClient();
			httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			PostMethod webMethod = new PostMethod(path);
			webMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(retryCount, false));
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length,"application/x-javascrip; charset=utf-8");
			webMethod.setRequestEntity(re);
			webMethod.setRequestHeader("Accept-Encoding", "UTF-8");
			webMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);
			httpClient.getParams().setConnectionManagerTimeout(timeOut);
			httpClient.getParams().setSoTimeout(timeOut);
			httpClient.executeMethod(webMethod);
			// String responseSOAP = webMethod.getResponseBodyAsString();
			BufferedReader in = new BufferedReader(new InputStreamReader(webMethod.getResponseBodyAsStream(), "UTF-8"));
			
			String line;
			StringBuilder returnsb = new StringBuilder();
			while ((line = in.readLine()) != null) {// 循环读取流
				returnsb.append(line);
			}
			in.close();
			// 释放连接
			if (webMethod != null) {
				webMethod.releaseConnection();
			}
			String responseSOAP = returnsb.toString();
			return responseSOAP;
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    /**
	 * @Description httpPost请求(application/json请求)
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
    public static JSONObject sendPostForJson(String sendJosn, String path,int timeOut) {
		int retryCount = 1;

		try {
			String requestSOAP = sendJosn;
			byte[] b = requestSOAP.getBytes("UTF-8");
			HttpClient httpClient = new HttpClient();
			httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			PostMethod webMethod = new PostMethod(path);
			webMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(retryCount, false));
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length,"application/x-javascrip; charset=utf-8");
			webMethod.setRequestEntity(re);
			webMethod.setRequestHeader("Accept-Encoding", "UTF-8");
			webMethod.setRequestHeader("Content-Type","application/json;charset=UTF-8");
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);
			httpClient.getParams().setConnectionManagerTimeout(timeOut);
			httpClient.getParams().setSoTimeout(timeOut);
			httpClient.executeMethod(webMethod);
			// String responseSOAP = webMethod.getResponseBodyAsString();
			BufferedReader in = new BufferedReader(new InputStreamReader(webMethod.getResponseBodyAsStream(), "UTF-8"));
			
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {// 循环读取流
				sb.append(line);
			}
			in.close();
			// 释放连接
			if (webMethod != null) {
				webMethod.releaseConnection();
			}
			String responseSOAP = sb.toString();
			return JSONObject.fromObject(responseSOAP);
		} catch (Exception e) {
			JSONObject json = new JSONObject();
			json.put("errcode", e.getMessage());
			return json;
		}
	}
    
}
