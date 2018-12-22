package com.goldbee.luckdraw.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.goldbee.luckdraw.dao.mapper.UsersMapper;
import com.goldbee.luckdraw.entity.ResCodeEnum;
import com.goldbee.luckdraw.entity.ResponseResult;
import com.goldbee.luckdraw.entity.Users;
import com.goldbee.luckdraw.service.UsersService;
import com.goldbee.luckdraw.utils.RequestUtils;

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
	public ResponseResult getUserInfoByOpenIdAndSaveInfo(String access_token,String openId) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
		String msg = RequestUtils.sendGet(url, null);
		System.out.println(msg);
		JSONObject json = JSONObject.parseObject(msg);
		//openid
		String openid = json.getString("openid");
		//头像地址
		String headimgurl = json.getString("headimgurl");
		//微信昵称
		String nickname = json.getString("nickname");
		//微信昵称
		String sex = json.getString("sex");
		Users user = new Users();
		user.setOpenid(openid);
		user.setNickName(nickname);
		user.setHeadimgurl(headimgurl);
		user.setSex(sex);
		user.setCreateTime(new Date());
		usersMapper.insert(user);
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS);
	}
}
