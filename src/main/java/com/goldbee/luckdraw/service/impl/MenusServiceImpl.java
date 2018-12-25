package com.goldbee.luckdraw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.goldbee.luckdraw.constant.CommonConstant;
import com.goldbee.luckdraw.constant.enums.EnumMenus;
import com.goldbee.luckdraw.constant.enums.ResCodeEnum;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.dao.mapper.MenusMapper;
import com.goldbee.luckdraw.entity.Button;
import com.goldbee.luckdraw.entity.Buttons;
import com.goldbee.luckdraw.entity.ClickButton;
import com.goldbee.luckdraw.entity.Menus;
import com.goldbee.luckdraw.entity.ViewButton;
import com.goldbee.luckdraw.service.MenusService;
import com.goldbee.luckdraw.utils.RequestUtils;
import com.goldbee.luckdraw.utils.WechatUtils;

import net.sf.json.JSONObject;

/**
 * <p>
 * 按钮表 服务实现类
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-23
 */
@Service
public class MenusServiceImpl extends ServiceImpl<MenusMapper, Menus> implements MenusService {

	@Autowired
	private MenusMapper menusMapper;

	/**
	 * @Description 创建按钮
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	@Override
	public ResponseResult<String> createMenu() {
		int result = Integer.MIN_VALUE;
		String accessToken = WechatUtils.getAccessToken(CommonConstant.grant_type, CommonConstant.appId, CommonConstant.appsecret);
		String url = CommonConstant.CTRATE_MENU_URL.replaceAll("ACCESS_TOKEN", accessToken);
		String menu = initMenu();
		JSONObject json = RequestUtils.sendPostForJson(menu, url, 0);
		if (json != null) {
			result = json.getInt("errcode");
		}
		if (result != 0) {
			return ResponseResult.buildResponseResult(ResCodeEnum.ERROR_SYSTEM, "错误码" + result);
		}
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS, "菜单创建成功");
	}

	/**
	 * @Description 初始化按钮
	 * @author chenxm66777123
	 * @Date 2018年12月23日
	 * @version 1.0.0
	 */
	public String initMenu() {
		Buttons returnButtons = new Buttons();
		//获取一级菜单
		List<Menus> menusOneList = menusMapper.selectList(new EntityWrapper<>(new Menus().setLevel(EnumMenus.Level.one.getKey())));
		// 封装菜单
		List<Button> buttonList = new ArrayList<>();
		// 一级菜单
		for (Menus menus : menusOneList) {
			//点击型按钮
			if(menus.getBtnType().equals(EnumMenus.ButtonType.click.getKey())) {
				// 创建点击一级菜单
				ClickButton button = new ClickButton();
				button.setName(menus.getName());
				button.setKey(menus.getKey());
				button.setType(menus.getType());
				//获取一级菜单下的子菜单
				List<Menus> menusChildList = menusMapper.selectList(new EntityWrapper<>(new Menus().setPid(menus.getId())));
				List<Button> buttonChildList = new ArrayList<>();
				for (Menus menusChild : menusChildList) {
					if(menusChild.getBtnType().equals(EnumMenus.ButtonType.click.getKey())) {
						// 创建点击一级菜单
						ClickButton buttonChild = new ClickButton();
						buttonChild.setName(menusChild.getName());
						buttonChild.setKey(menusChild.getKey());
						buttonChild.setType(menusChild.getType());
						//添加菜单
						buttonChildList.add(buttonChild);
					}
					//点击型按钮
					if(menusChild.getBtnType().equals(EnumMenus.ButtonType.view.getKey())) {
						// 创建点击一级菜单
						ViewButton buttonChild = new ViewButton();
						buttonChild.setName(menusChild.getName());
						buttonChild.setKey(menusChild.getKey());
						buttonChild.setType(menusChild.getType());
						buttonChild.setUrl(menusChild.getContent());
						//添加菜单
						buttonChildList.add(buttonChild);
					}
				}
				button.setSub_button(buttonChildList);	
				//添加菜单
				buttonList.add(button);
			}
			
			//跳转型按钮只有一级菜单
			if(menus.getBtnType().equals(EnumMenus.ButtonType.view.getKey())) {
				// 创建点击一级菜单
				ViewButton button = new ViewButton();
				button.setName(menus.getName());
				button.setKey(menus.getKey());
				button.setType(menus.getType());
				button.setUrl(menus.getContent());
				//添加菜单
				buttonList.add(button);
			}
			
		}
		returnButtons.setButton(buttonList);
		return JSONObject.fromObject(returnButtons).toString();
	}

}
