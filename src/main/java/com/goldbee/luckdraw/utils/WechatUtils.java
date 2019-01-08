package com.goldbee.luckdraw.utils;

import net.sf.json.JSONObject;

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
		String url ="https://api.weixin.qq.com/sns/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret+"";
		//请求返回消息
		String msg = RequestUtils.sendGet(url, null);
		System.out.println("accressToken-----------："+msg);
		//转为Json格式
		JSONObject json = JSONObject.fromObject(msg);
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
			//获取用户信息接口https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
			String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
			//请求返回消息
			String msg = RequestUtils.sendGet(url, null);
			System.out.println("msg信息--------------:" + msg);
			//转为Json格式
			JSONObject json = JSONObject.fromObject(msg);
			return json;
	}
	
	
	/**
	 * @Description 获取素材列表返回Json
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	public static JSONObject getBatchgetMaterial(String access_token,String params) {
			//获取用户信息接口
			String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+access_token+"";
			//请求返回消息
			JSONObject json = RequestUtils.sendPostForJson(params, url, 0);			
			//转为Json格式
			return json;
	}
	
	
	
}
