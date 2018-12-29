package com.goldbee.luckdraw.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldbee.luckdraw.constant.enums.ResponseResult;
import com.goldbee.luckdraw.rq.RecordRQ;
import com.goldbee.luckdraw.service.RecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 中奖纪录保存表 前端控制器
 * </p>
 *
 * @author chenxm66777123
 * @since 2018-12-29
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/record")
@Api(value = "record", tags = "中奖纪录处理接口")
public class RecordController {


	@Autowired
	private RecordService recordService;

	@PostMapping(value = "saveRecord")
	@ApiOperation(value = "保存中奖纪录接口", notes = "保存中奖纪录接口")
	public ResponseResult<String> saveRecord(@RequestBody() List<RecordRQ> recordRQList){
		return recordService.saveRecord(recordRQList);
	}
}

