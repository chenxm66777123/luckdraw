package com.goldbee.luckdraw.entity;

import java.io.Serializable;

import com.goldbee.luckdraw.dto.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @ClassName ResponseResult
 * @Description 封装响应数据的bean
 * @author zhigang.zhou
 * @Date 2018年10月26日 下午1:16:35
 * @version 1.0.0
 * @param <E>
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ResponseResult<E> implements Serializable {

    private static final long serialVersionUID = 3468129702817605150L;

    /**
     * @code : 响应状态码
     */
    private Integer code;

    /**
     * @msg : 该状态码对应的提示信息
     */
    private String msg;

    /**
     * @data : 响应数据
     */
    private E data;

    /**
     * 分页信息
     */
    private Page page;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <E> ResponseResult<E> buildResponseResult(ResCodeEnum resCodeEnum) {
        return new ResponseResult<E>(resCodeEnum.code, resCodeEnum.msg);
    }

    public static <E> ResponseResult<E> buildResponseResult(ResCodeEnum resCodeEnum, E data) {
        return new ResponseResult<E>(resCodeEnum.code, resCodeEnum.msg).setData(data);
    }

    public static <E> ResponseResult<E> buildResponseResult(Integer code, String msg) {
        return new ResponseResult<E>(code, msg);
    }

    public static <E> ResponseResult<E> buildResponseResult(Integer code, String msg, E data) {
        return new ResponseResult<E>(code, msg, data, null);
    }

    public static <E> ResponseResult<E> buildResponseResult(Integer code, E data, Page page) {
        return new ResponseResult<E>(code, null, data, page);
    }

    public static <E> ResponseResult<E> buildResponseResult(ResCodeEnum resCodeEnum, E data, Page page) {
        return new ResponseResult<E>(resCodeEnum.code, null, data, page);
    }

    public static <E> ResponseResult<E> buildResponseResult(Integer code, String msg, E data, Page page) {
        return new ResponseResult<E>(code, msg, data, page);
    }

}
