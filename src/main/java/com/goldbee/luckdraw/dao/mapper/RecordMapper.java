package com.goldbee.luckdraw.dao.mapper;

import com.goldbee.luckdraw.entity.Record;
import com.goldbee.luckdraw.rq.RecordRQ;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 中奖纪录保存表 Mapper 接口
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-29
 */
public interface RecordMapper extends BaseMapper<Record> {

	

	/**
	 * @Description 批量保存接口
	 * @author chenxm66777123
	 * @Date 2018年12月29日
	 * @version 1.0.0
	 */
	public int batchSaveRecords(List<RecordRQ> recordRQList);
	
}
