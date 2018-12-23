package com.goldbee.luckdraw.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description 按钮
 * @author chenxm66777123
 * @Date 2018年12月23日
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class Button {

	private String name;//菜单标题
	
	private String type;//菜单的响应动作类型
	
	private Button[] sub_button;
}
