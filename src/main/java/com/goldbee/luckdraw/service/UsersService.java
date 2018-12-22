package com.goldbee.luckdraw.service;

import org.springframework.stereotype.Service;

public interface UsersService {

	/**
	 * @Description 通过openid获取用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	public String getUserInfoByOpenId(String access_token,String openId) ;

}
