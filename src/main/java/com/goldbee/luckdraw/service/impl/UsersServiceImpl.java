package com.goldbee.luckdraw.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.goldbee.luckdraw.constant.CommonConstant;
import com.goldbee.luckdraw.constant.enums.ResCodeEnum;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.dao.mapper.UsersMapper;
import com.goldbee.luckdraw.entity.Users;
import com.goldbee.luckdraw.service.UsersService;
import com.goldbee.luckdraw.utils.WechatUtils;

import net.sf.json.JSONObject;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-22
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

	
	
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * @Description 通过openid获取用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseResult saveUserInfo(String openId) {
		String access_token = WechatUtils.getAccessToken(CommonConstant.grant_type, CommonConstant.appId, CommonConstant.appsecret);
		JSONObject json = WechatUtils.getUserInfoByOpenId(access_token, openId);
		//openid
		String openid = json.getString("openid");
		//头像地址
		String headimgurl = json.getString("headimgurl");
		//微信昵称
		String nickname = json.getString("nickname");
		//微信昵称
		String sex = json.getString("sex");

		Users returnUsers = usersMapper.selectOne(new Users().setOpenid(openid));
		//已经存在不在添加
		if(returnUsers == null) {
			Users user = new Users();
			user.setOpenid(openid);
			user.setNickName(nickname);
			user.setHeadimgurl(headimgurl);
			user.setSex(sex);
			user.setCreateTime(new Date());
			usersMapper.insert(user);
		}else {
			returnUsers.setNickName(nickname);
			returnUsers.setHeadimgurl(headimgurl);
			returnUsers.setSex(sex);
			returnUsers.setCreateTime(new Date());
			usersMapper.updateById(returnUsers);
		}
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS);
	}
}
