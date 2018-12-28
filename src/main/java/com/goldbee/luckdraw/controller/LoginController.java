package com.goldbee.luckdraw.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbee.luckdraw.constant.CommonConstant;
import com.goldbee.luckdraw.service.MessageService;
import com.goldbee.luckdraw.utils.CheckUtil;
import com.goldbee.luckdraw.utils.RequestUtils;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
public class LoginController {

	@Autowired
	private MessageService messageService;

	@GetMapping(value = "wx")
	@ApiOperation(value = "微信公众号开发者模式接入接口", notes = "微信公众号开发者模式接入接口")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
				out.write(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	@PostMapping(value = "wx")
	@ApiOperation(value = "微信公众号开发者模式接受消息接口", notes = "微信公众号开发者模式接入接口")
	public void wechatServicePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		// 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String respXml = messageService.messageHandel(request, response);
			out.println(respXml);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * @Description 扫描二维码跳转方法
	 * @author chenxm66777123
	 * @Date 2018年12月27日
	 * @version 1.0.0
	 */
	@GetMapping(value = "getOpenIdByCode")
	public void getOpenIdByCode(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String openid = "";
		// 1 .获取参数code
		String code = request.getParameter("code");
		// 2. 根据code获取向微信官方api发请求，获取当前微信用户的json数据
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + CommonConstant.appId + "&secret="
				+ CommonConstant.appsecret + "&code=" + code + "&grant_type=authorization_code";
		JSONObject wxUser = RequestUtils.sendPostForJson("", url, 300);
		// 3. 获取json数据中的openid
		openid = wxUser.getString("openid");
		System.out.println(openid);

	}

}
