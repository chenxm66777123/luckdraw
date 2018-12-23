package com.goldbee.luckdraw.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description 跳转类型
 * @author chenxm66777123
 * @Date 2018年12月23日
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ViewButton extends Button{
	
	private String key;//菜单KEY值
	
	private String url;//网页链接


}
