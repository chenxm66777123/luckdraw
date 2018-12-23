package com.goldbee.luckdraw;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.goldbee.luckdraw.constant.BaseScanPackagesConstant;
import com.goldbee.luckdraw.constant.MapperScanPackagesConstant;



@SpringBootApplication
@ComponentScan(basePackages={BaseScanPackagesConstant.BUSINESS_BASEPACKAGE})
@MapperScan(basePackages = {MapperScanPackagesConstant.BUSINESS_MAPPER_BASEPACKAGE})
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
