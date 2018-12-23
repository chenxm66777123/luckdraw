package com.goldbee.luckdraw.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 按钮表
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-23
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Menus extends Model<Menus> {

    private static final long serialVersionUID = 1L;

    /**
     * 级别
     */
    private Integer id;
    /**
     * 父级id
     */
    private Integer pid;
    /**
     * 按钮菜单KEY值
     */
    private String key;
    /**
     * 按钮菜单标题
     */
    private String name;
    private String content;
    /**
     * 按钮菜单的响应动作类型
     */
    private String type;
    /**
     * 按钮类型 1点击 2 跳转型
     */
    private Integer btnType;
    /**
     * 按钮等级 一级按钮 二级按钮
     */
    private Integer level;
    /**
     * 创建时间
     */
    private Date createTime;


  
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

  
}
