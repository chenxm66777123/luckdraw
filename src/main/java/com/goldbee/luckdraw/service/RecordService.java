package com.goldbee.luckdraw.service;

import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.entity.Record;
import com.goldbee.luckdraw.rq.RecordRQ;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 中奖纪录保存表 服务类
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-29
 */
public interface RecordService extends IService<Record> {
	
	/**
	 * @Description 保存中奖纪录接口
	 * @author chenxm66777123
	 * @Date 2018年12月29日
	 * @version 1.0.0
	 */
	public ResponseResult<String> saveRecord(List<RecordRQ> recordRQList);

}
