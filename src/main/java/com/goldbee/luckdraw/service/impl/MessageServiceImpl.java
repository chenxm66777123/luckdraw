package com.goldbee.luckdraw.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldbee.luckdraw.constant.CommonConstant;
import com.goldbee.luckdraw.entity.Material;
import com.goldbee.luckdraw.entity.MessageInfo;
import com.goldbee.luckdraw.entity.TextMessage;
import com.goldbee.luckdraw.service.MessageService;
import com.goldbee.luckdraw.service.UsersService;
import com.goldbee.luckdraw.utils.MessageUtil;
import com.goldbee.luckdraw.utils.WechatUtils;

import net.sf.json.JSONObject;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private UsersService usersService;
	/**
	 * @Description 微信消息处理
	 * @author chenxm66777123
	 * @Date 2018年12月18日
	 * @version 1.0.0
	 */
	@Override
	public String messageHandel(HttpServletRequest request, HttpServletResponse response) {
		MessageInfo messageInfo = new MessageInfo();
		// 返回给微信服务器的消息,默认为null
		String respMessage = null;

		try {

			// 默认返回的文本消息内容
			String respContent = null;
			// xml分析
			// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> map = MessageUtil.xmlToMap(request);
			// 发送方账号
			String fromUserName = map.get("FromUserName");
			messageInfo.setFromUserName(fromUserName);
			// 接受方账号（公众号）
			String toUserName = map.get("ToUserName");
			messageInfo.setToUserName(toUserName);
			// 消息类型
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			messageInfo.setMessageType(msgType);
			// 默认回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			//textMessage.setFuncFlag(0);

			// 分析用户发送的消息类型，并作出相应的处理

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "有了人海，相遇才显得那么意外。/握手\r\n" + 
						"\r\n" + 
						"您想了解什么呢？可以直接给小编留言哦~/转圈/转圈/转圈\r\n" + 
						"\r\n" + 
						"小编一定知无不言言无不尽~/飞吻/飞吻/飞吻";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
				textMessage.setContent(respContent + content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
				textMessage.setContent(respContent + content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
				textMessage.setContent(respContent + content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				textMessage.setContent(respContent + content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				textMessage.setContent(respContent + content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

			// 事件推送(当用户主动点击菜单，或者扫面二维码等事件)
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

				// 事件类型
				String eventType = map.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// respMessage = MessageUtil.followResponseMessageModel(messageInfo);
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// MessageUtil.cancelAttention(fromUserName);
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {

				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {

				}
				// 自定义菜单（点击菜单拉取消息）
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {

					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey=map.get("EventKey");
//					if(eventKey.equals("luckdraw")) {
//						usersService.saveUserInfo(fromUserName);
//						respContent = "恭喜！！年会报名成功！！";
//						textMessage.setContent(respContent);
//						respMessage = MessageUtil.textMessageToXml(textMessage);
//	
//					}
					//公司动态
					if(eventKey.equals("company")) {
						Material material = new Material();
						material.setType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
						material.setOffset(1);
						material.setCount(10);
						String access_token = WechatUtils.getAccessToken(CommonConstant.grant_type, CommonConstant.appId, CommonConstant.appsecret);
						WechatUtils.getBatchgetMaterial(access_token, JSONObject.fromObject(material).toString());
								
					}
				}
				// 自定义菜单（(自定义菜单URl视图)）
				else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
					
					String eventKey=map.get("EventKey");
					//年会报名入口
					if(eventKey.equals("http://luck.beesrv.com:3000/index.html")) {
						String access_token = WechatUtils.getAccessToken(CommonConstant.grant_type, CommonConstant.appId, CommonConstant.appsecret);
						JSONObject json = WechatUtils.getUserInfoByOpenId(access_token, fromUserName);
						//openid
						String openid = json.getString("openid");
						//头像地址
						String headimgurl = json.getString("headimgurl");
						//微信昵称
						String nickname = json.getString("nickname");
						//微信昵称
						String sex = json.getString("sex");
						
						StringBuffer strBuffer = new StringBuffer(eventKey);
						strBuffer.append("?nickname="+nickname+"");
						strBuffer.append("&headimgurl="+headimgurl+"");
						strBuffer.append("&openid="+openid+"");
						strBuffer.append("&sex="+sex+"");
						map.put("EventKey", strBuffer.toString());
						usersService.saveUserInfo(json);
						System.out.println("抽奖跳转地址："+ map);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			respMessage = null;
		} finally {
			if (null == respMessage) {
				// respMessage = MessageUtil.systemErrorResponseMessageModel(messageInfo);
			}
		}

		return respMessage;
	}

}
