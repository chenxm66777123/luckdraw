package com.goldbee.luckdraw.utils;

import com.goldbee.luckdraw.entity.Button;
import com.goldbee.luckdraw.entity.ClickButton;
import com.goldbee.luckdraw.entity.Menu;
import com.goldbee.luckdraw.entity.ViewButton;

import net.sf.json.JSONObject;

/**
 * @Description 按钮工具类
 * @author chenxm66777123
 * @Date 2018年12月23日
 * @version 1.0.0
 */
public class MenuUtil {

	private static final String CTRATE_MENU_URL  = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 创建菜单
	 * @param accessToken 
	 * @param Menu 菜单json格式字符串
	 * @return
	 */
	public static int createMenu(String accessToken,String Menu){
		int result = Integer.MIN_VALUE;
		String url = CTRATE_MENU_URL.replaceAll("ACCESS_TOKEN", accessToken);
		JSONObject json = RequestUtils.sendHttpJsonMsg(Menu, url, 0);
		if(json!=null){
			result = json.getInt("errcode");
		}
		return result;
	}
	
	/**
	 * @Description 初始化按钮
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	public static String initMenu(){
		//创建点击一级菜单
		ClickButton button11 = new ClickButton();
		button11.setName("往期活动");
		button11.setKey("11");
		button11.setType("click");
		
		//创建跳转型一级菜单
		ViewButton button21 = new ViewButton();
		button21.setName("百度一下");
		button21.setType("view");
		button21.setUrl("https://www.baidu.com");
		
		//创建其他类型的菜单与click型用法一致
		ClickButton button31 = new ClickButton();
		button31.setName("拍照发图");
		button31.setType("pic_photo_or_album");
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("发送位置");
		button32.setKey("32");
		button32.setType("location_select");
		
		//封装到一级菜单
		Button button = new Button();
		button.setName("菜单");
		button.setType("click");
		button.setSub_button(new Button[]{button31,button32});
		
		//封装菜单
		Menu menu = new Menu();
		menu.setButton(new Button[]{button11,button21,button});
		return JSONObject.fromObject(menu).toString();
	}
}
