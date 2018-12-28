package com.goldbee.luckdraw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.service.MenusService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 按钮表 前端控制器
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-23
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menus")
public class MenusController {

	@Autowired
	private MenusService menusService;
	
	@GetMapping(value = "createMenu")
	@ApiOperation(value="微信公众号开发者模式创建自定义按钮",notes="微信公众号开发者模式创建自定义按钮")
	public ResponseResult<String> createMenu() {
	
		return menusService.createMenu();
	}
}

