package com.goldbee.luckdraw.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description 素材类型
 * @author chenxm66777123
 * @Date 2018年12月23日
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class Material {
	//type		是	素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	//offset	是	从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	//count		是	返回素材的数量，取值在1到20之间
	private String type;
	
	private Integer offset;
	
	private Integer count;
}
