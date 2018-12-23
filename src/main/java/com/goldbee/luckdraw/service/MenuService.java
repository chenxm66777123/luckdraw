package com.goldbee.luckdraw.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface MenuService {
	
	/**
	 * @Description 获取菜单
	 * @author chenxm66777123
	 * @Date 2018年12月18日
	 * @version 1.0.0
	 */
    public JSONObject getMenu(String accessToken);
    
	/**
	 * @Description 微信消息处理
	 * @author chenxm66777123
	 * @Date 2018年12月18日
	 * @version 1.0.0
	 */
    public int createMenu(Map<String, Object> menu, String accessToken);
    
	/**
	 * @Description 微信消息处理
	 * @author chenxm66777123
	 * @Date 2018年12月18日
	 * @version 1.0.0
	 */
    public int deleteMenu(String accessToken);


}
