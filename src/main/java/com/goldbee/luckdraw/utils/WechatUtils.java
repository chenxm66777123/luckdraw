package com.goldbee.luckdraw.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 微信工具类
 * @author chenxm66777123
 * @Date 2018年12月23日
 * @version 1.0.0
 */
public class WechatUtils {

	
	 /**
	 * @Description 获取accesstoken消息
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	 public static String getAccessToken(String grant_type,String appid,String secret) {
		//获取accesstoken接口
		String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret+"";
		//请求返回消息
		String msg = RequestUtils.sendGet(url, null);
		System.out.println(msg);
		//转为Json格式
		JSONObject json = JSONObject.parseObject(msg);
		String access_token = json.getString("access_token");
		return access_token;
	 }

	 
	/**
	 * @Description 获取用户信息返回Json
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	public static JSONObject getUserInfoByOpenId(String access_token,String openId) {
			//获取用户信息接口
			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
			//请求返回消息
			String msg = RequestUtils.sendGet(url, null);
			System.out.println(msg);
			//转为Json格式
			JSONObject json = JSONObject.parseObject(msg);
			return json;
	}
}