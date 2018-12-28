package com.goldbee.luckdraw.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.service.IService;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.entity.Users;
import com.goldbee.luckdraw.rq.UserRQ;

import net.sf.json.JSONObject;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-22
 */
public interface UsersService extends IService<Users> {

	/**
	 * @Description 保存用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	@SuppressWarnings("rawtypes")
	public ResponseResult saveUserInfo(String openId) ;
	
	
	
	/**
	 * @Description 保存用户信息（已经拿到信息用户以后直接保存）
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	public ResponseResult<String> saveUserInfo(JSONObject json) ;

	
	/**
	 * @Description 获取所有用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	public ResponseResult<List<Users>> getAllUsers() ;
	
	
	/**
	 * @Description 签到成功后通过openId修改用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	public ResponseResult<String> updateUserByOpenId(@RequestBody() UserRQ userRQ);
	
}
