package com.goldbee.luckdraw;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.goldbee.luckdraw.enums.BaseScanPackagesConstant;

@SpringBootApplication
@ComponentScan(basePackages={BaseScanPackagesConstant.BUSINESS_BASEPACKAGE})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
