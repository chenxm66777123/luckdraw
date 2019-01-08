package com.goldbee.luckdraw.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-22
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@TableName("users")
@ToString
public class Users extends Model<Users> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 微信用户唯一标识
	 */
	private String openid;
	/**
	 * 微信昵称
	 */
	private String nickName;
	/**
	 * 微信头像
	 */
	private String headimgurl;
	/**
	 * 性别 0 女 1 男
	 */
	private String sex;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 电话号码
	 */
	private String telphone;
	/**
	 * 创建时间
	 */
	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
