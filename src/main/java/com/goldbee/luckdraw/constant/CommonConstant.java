package com.goldbee.luckdraw.constant;

/**
 * @ClassName 微信接口相关常量类
 * @Description 
 * @author chenxm66777123
 * @Date 2018年12月23日
 * @version 1.0.0
 */
public class CommonConstant {

	public static final String token = "goldbeeluckdraw";

	public static final String grant_type = "client_credential";
	 
	public static final String appId = "wx590602ef804f391d";

	public static final String appsecret = "91c2ea3d0a52fbcaeeeb516a2596dc77";
	
	public static final String CTRATE_MENU_URL  = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static final String groupUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN"; //这个地址是根据分组id来群发消息

	public static final String groupUrl1 = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN"; //这个地址是根据openid来群发消息

}
