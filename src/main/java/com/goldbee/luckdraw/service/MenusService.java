package com.goldbee.luckdraw.service;

import com.baomidou.mybatisplus.service.IService;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.entity.Menus;

/**
 * <p>
 * 按钮表 服务类
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-23
 */
public interface MenusService extends IService<Menus> {

	/**
	 * @Description 创建按钮
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	public ResponseResult<String> createMenu();

}
