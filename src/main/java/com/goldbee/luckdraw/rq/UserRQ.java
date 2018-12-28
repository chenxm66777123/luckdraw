package com.goldbee.luckdraw.rq;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("用户请求入参")
public class UserRQ implements Serializable{

	private static final long serialVersionUID = 2285278944616439669L;
	
	@ApiModelProperty(value = "微信用户唯一标识")
    @NotEmpty(message = "微信用户唯一标识不能为空")
	private String openid;

	@ApiModelProperty(value = "微信昵称")
	private String nickName;

	@ApiModelProperty(value = "微信头像")
	private String headimgurl;

	@ApiModelProperty(value = "性别 0 女 1 男")
	private String sex;

	@ApiModelProperty(value = "真实姓名")
    @NotEmpty(message = "真实姓名不能为空")
	private String realName;

}



