package com.goldbee.luckdraw.rq;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("中奖记录入参")
public class RecordRQ implements Serializable {

	private static final long serialVersionUID = -6976623076228191521L;

	@ApiModelProperty(value = "获奖人openid")
    private String awardOpenid;

	@ApiModelProperty(value = "获奖人姓名")
    private String awardName;

	@ApiModelProperty(value = " 奖项（一等奖/二等奖...）")
    private String awardLevel;


  
}
