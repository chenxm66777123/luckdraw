package com.goldbee.luckdraw.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ResCodeEnum
 * @Description (1)、code为0时：
 *                    0->请求成功，程序可以继续往下执行；
 *              (2)、code为正数时：
 *                    1到20->给tost提示，程序可以继续往下执行；
 *                    21到40：弹确认框，用户点击确认之后程序可以继续往下执行；
 *                    41到60：跳转业务逻辑页面。
 *              (3)、code为负数时： 
 *                    -10到-1->弹窗错误提示框，并阻止程序继续往下执行；
 *                    -20到-11->跳转错误页面
 * @author zhigang.zhou
 * @Date 2018年10月26日 下午1:16:35
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ResCodeEnum {
    SUCCESS(0, "成功"), 
	ERROR_SYSTEM(-1, "系统繁忙,请稍后再试"), 
	ERROR_NOFOUND(-1, "无法找到相应的数据"), 
	HYSTRIX_ENABLED(-1000, "无法找到相应的数据，该服务被熔断"), 
	ERROR_PARAMETER(-1, "参数错误"),
	PARAMETER_INCOMPLETE(-1, "参数不全"),
	ERROR_NOAUTH(-10, "没有权限"),
	ERROR_DUPLICATE_VALUE(-9,"手机号码已被使用"),
	NO_TWICE(-1,"报价次数不能超过两次"),
	FAILED_TO_GET_USER_INFO(-2,"token有误,获取用户信息失败"),
	FAILED_LOGIN_INFORMATION(-2,"登录信息有误，请重新登录"),
    BUSINESS_NO_ORDERBIDDING(-1,"报价单不存在或已被删除"),
    BUSINESS_NO_CLEARING(-1,"结算单不存在"),
    BUSINESS_NO_PRODUCT(-1,"询价单不存在或已被删除"),
    BUSINESS_NO_SALE(-1,"销售产品不存在或已被删除"),
    BUSINESS_NO_TOTAL(-1,"可供数量为0，无法接受申请"),
    BUSINESS_BUY_THAN_TOTAL(-1,"购买数量大于可供数量"),
	BUSINESS_ACCEPTED(-1,"已经接受报价,不能重复接受"),
	QUOTED_ACCEPTED(-1,"报价已经被接受,不能修改"),
	NO_URL(-1,"未找到下载地址"),
	CONTRACT_CREATED(-1,"合同已被生成,请勿重复提交"),
	INQ_FALSE(-1,"询价单保存错误"),
    SUBSCRIB_THAN_FIVE(-1,"最多只能订阅五个关键字"),
	USER_LOGIN_USER_INFO_EMPTY(-1,"获取当前登录用户公司id，公司名称失败"),
	GET_COMPANY_ERROR(-1,"获取公司信息失败");
	public Integer code;

	public String msg;

}
