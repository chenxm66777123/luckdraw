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
import lombok.experimental.Accessors;

/**
 * <p>
 * 中奖纪录保存表
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-29
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@TableName("record")
public class Record extends Model<Record> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 获奖人openid
     */
    private String awardOpenid;
    /**
     * 获奖人姓名
     */
    private String awardName;
    /**
     * 奖项（一等奖/二等奖...）
     */
    private String awardLevel;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 状态标识 0无效 1有效
     */
    private String status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

 
}
