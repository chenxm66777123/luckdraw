package com.goldbee.luckdraw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbee.luckdraw.service.MessageService;
import com.goldbee.luckdraw.utils.CheckUtil;

@RestController
public class LoginController {

	@Autowired
	private MessageService messageService;

	@GetMapping(value = "wx")
	public void login(HttpServletRequest request,HttpServletResponse response){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			  out = response.getWriter();
			if(CheckUtil.checkSignature(signature, timestamp, nonce)){
				out.write(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	
	@PostMapping(value = "wx")
	public String wechatServicePost(HttpServletRequest request, HttpServletResponse response){
				// 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
				try {
					request.setCharacterEncoding("UTF-8");
					// 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
					response.setCharacterEncoding("UTF-8"); 
					String respXml = messageService.messageHandel(request, response);
				
					return respXml;

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "........."; 
	}
}
