package com.goldbee.luckdraw.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.goldbee.luckdraw.constant.CommonConstant;
import com.goldbee.luckdraw.constant.enums.ResCodeEnum;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.dao.mapper.UsersMapper;
import com.goldbee.luckdraw.entity.Users;
import com.goldbee.luckdraw.rq.UserRQ;
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
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseResult<String> saveUserInfo(String openId) {
		String access_token = WechatUtils.getAccessToken(CommonConstant.grant_type, CommonConstant.appId,
				CommonConstant.appsecret);
		JSONObject json = WechatUtils.getUserInfoByOpenId(access_token, openId);
		// openid
		String openid = json.getString("openid");
		// 头像地址
		String headimgurl = json.getString("headimgurl");
		// 微信昵称
		String nickname = json.getString("nickname");
		// 微信昵称
		String sex = json.getString("sex");

		Users returnUsers = usersMapper.selectOne(new Users().setOpenid(openid));
		// 已经存在不在添加
		if (returnUsers == null) {
			Users user = new Users();
			user.setOpenid(openid);
			user.setNickName(nickname);
			user.setHeadimgurl(headimgurl);
			user.setSex(sex);
			user.setCreateTime(new Date());
			usersMapper.insert(user);
		} else {
			returnUsers.setNickName(nickname);
			returnUsers.setHeadimgurl(headimgurl);
			returnUsers.setSex(sex);
			returnUsers.setCreateTime(new Date());
			usersMapper.updateById(returnUsers);
		}
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS);
	}

	/**
	 * @Description 保存用户信息（已经拿到信息用户以后直接保存）
	 * @author chenxm66777123
	 * @Date 2018年12月20日
	 * @version 1.0.0
	 */
	@Override
	public ResponseResult<String> saveUserInfo(JSONObject json) {

		// openid
		String openid = json.getString("openid");
		// 头像地址
		String headimgurl = json.getString("headimgurl");
		// 微信昵称
		String nickname = json.getString("nickname");
		// 微信昵称
		String sex = json.getString("sex");

		Users returnUsers = usersMapper.selectOne(new Users().setOpenid(openid));
		// 已经存在不在添加
		if (returnUsers == null) {
			Users user = new Users();
			user.setOpenid(openid);
			user.setNickName(nickname);
			user.setHeadimgurl(headimgurl);
			user.setSex(sex);
			user.setCreateTime(new Date());
			usersMapper.insert(user);
		} else {
			returnUsers.setNickName(nickname);
			returnUsers.setHeadimgurl(headimgurl);
			returnUsers.setSex(sex);
			returnUsers.setCreateTime(new Date());
			usersMapper.updateById(returnUsers);
		}

		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS);
	}

	/**
	 * @Description 获取所有用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月28日
	 * @version 1.0.0
	 */
	@Override
	public ResponseResult<List<Users>> getAllUsers() {
		List<Users> userList = usersMapper.selectList(null);
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS, userList);
	}

	/**
	 * @Description 签到成功后通过openId修改用户信息
	 * @author chenxm66777123
	 * @Date 2018年12月28日
	 * @version 1.0.0
	 */
	@Override
	public ResponseResult<String> updateUserByOpenId(UserRQ userRQ) {
		Users returnUsers = usersMapper.selectOne(new Users().setOpenid(userRQ.getOpenid()));
		// 已经存在不在添加
		if (returnUsers == null) {
			Users user = new Users();
			BeanUtils.copyProperties(userRQ, user);
			user.setCreateTime(new Date());
			usersMapper.insert(user);
		} else {
			// 设置真实姓名
			returnUsers.setRealName(userRQ.getRealName());
			returnUsers.setCreateTime(new Date());
			usersMapper.updateById(returnUsers);
		}
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS);
	}
}
