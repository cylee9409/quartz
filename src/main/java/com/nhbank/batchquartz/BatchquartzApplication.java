package com.nhbank.batchquartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nhbank.batchquartz.mapper")
public class BatchquartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchquartzApplication.class, args);
	}

}
