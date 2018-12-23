package com.goldbee.luckdraw.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TextMessage {

	private String MsgType;

	private String ToUserName;

	private String FromUserName;

	private long CreateTime;

	private String Content;

	private Integer FuncFlag;
	


}
