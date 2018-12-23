package com.goldbee.luckdraw.service;

import com.baomidou.mybatisplus.service.IService;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.entity.Users;

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

}
