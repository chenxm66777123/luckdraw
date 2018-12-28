package com.goldbee.luckdraw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.entity.TextMessage;
import com.goldbee.luckdraw.entity.Users;
import com.goldbee.luckdraw.rq.UserRQ;
import com.goldbee.luckdraw.service.UsersService;
import com.goldbee.luckdraw.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Api(value = "user", tags = "用户处理接口")
public class UserController {

	@Autowired
	private UsersService usersService;

	@GetMapping(value = "getAllUsers")
	@ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
	public ResponseResult<List<Users>> getAllUsers() {
		return usersService.getAllUsers();
	}


	@PostMapping(value = "updateUserByOpenId")
	@ApiOperation(value = "签到成功后通过openId修改用户信息", notes = "签到成功后通过openId修改用户信息")
	public ResponseResult<String> updateUserByOpenId(@RequestBody() UserRQ userRQ) {
		return usersService.updateUserByOpenId(userRQ);
	}

	
	
	@PostMapping(value = "sendMsg")
	@ApiOperation(value = "微信公众号开发者模式接受消息接口", notes = "微信公众号开发者模式接入接口")
	public void sendMsg(HttpServletRequest request, HttpServletResponse response,String respContent,String openId) throws IOException {
		request.setCharacterEncoding("UTF-8");
		// 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName("gh_22c254dd6c52");
			//需要关注这个公众号
			textMessage.setFromUserName(openId);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(respContent);
			String respXml = MessageUtil.textMessageToXml(textMessage);
			out.println(respXml);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
