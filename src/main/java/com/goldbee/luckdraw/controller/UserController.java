package com.goldbee.luckdraw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.entity.Users;
import com.goldbee.luckdraw.rq.UserRQ;
import com.goldbee.luckdraw.service.UsersService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
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

	
}
