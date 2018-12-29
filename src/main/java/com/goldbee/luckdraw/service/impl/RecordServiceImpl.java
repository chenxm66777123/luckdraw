package com.goldbee.luckdraw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.goldbee.luckdraw.constant.enums.ResCodeEnum;
import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.dao.mapper.RecordMapper;
import com.goldbee.luckdraw.entity.Record;
import com.goldbee.luckdraw.rq.RecordRQ;
import com.goldbee.luckdraw.service.RecordService;

/**
 * <p>
 * 中奖纪录保存表 服务实现类
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-29
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

	@Autowired
	private RecordMapper recordMapper;

	/**
	 * @Description 保存中奖纪录接口
	 * @author chenxm66777123
	 * @Date 2018年12月29日
	 * @version 1.0.0
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseResult<String> saveRecord(List<RecordRQ> recordRQList) {

		int i = recordMapper.batchSaveRecords(recordRQList);
		if (i < 1) {
			return ResponseResult.buildResponseResult(ResCodeEnum.ERROR_SYSTEM);
		}
		return ResponseResult.buildResponseResult(ResCodeEnum.SUCCESS);
	}

}
