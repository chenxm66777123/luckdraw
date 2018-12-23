package com.goldbee.luckdraw.entity;
 
import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 

@Getter
@Setter
@NoArgsConstructor
public class MessageInfo implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private String fromUserName;           // 发送发微信账号
	
	private String toUserName;             // 接收方微信账号
	
	private String weixinUserName;         // 微信用户名
	
	private String messageType;            // 消息类型


}
