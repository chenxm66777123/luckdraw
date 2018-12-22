package com.goldbee.luckdraw.service.impl;

import org.springframework.stereotype.Service;

import com.goldbee.luckdraw.service.UsersService;
import com.goldbee.luckdraw.utils.RequestUtils;

@Service
public class UsersServiceImpl implements UsersService {

	/**
	 * @Description 通过openid获取用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	@Override
	public String getUserInfoByOpenId(String access_token,String openId) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid=\""+openId+"\"&lang=zh_CN";
		String msg = RequestUtils.sendGet(url, null);
		System.out.println(msg);
		return null;
	}

}
