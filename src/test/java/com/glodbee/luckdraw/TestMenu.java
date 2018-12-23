package com.glodbee.luckdraw;

import com.goldbee.luckdraw.constant.CommonConstant;
import com.goldbee.luckdraw.utils.MenuUtil;
import com.goldbee.luckdraw.utils.WechatUtils;

public class TestMenu {

	public static void main(String[] args) {
		
		String accessToken  = WechatUtils.getAccessToken(CommonConstant.grant_type, CommonConstant.appId, CommonConstant.appsecret);
		String menu = MenuUtil.initMenu();
		System.out.println(menu);
		int result = MenuUtil.createMenu(accessToken,menu);
		if(result==0){
			System.out.println("菜单创建成功");
		}else{
			System.out.println("错误码"+result);
		}
	}
}
