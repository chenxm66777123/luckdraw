package com.goldbee.luckdraw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MessageService {

	/**
	 * @Description 微信消息处理
	 * @author chenxm66777123
	 * @Date 2018年12月18日
	 * @version 1.0.0
	 */
	public String messageHandel(HttpServletRequest request,HttpServletResponse response);


}
