package com.nhbank.batchquartz.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nhbank.batchquartz.service.MybatisTestService;

import lombok.RequiredArgsConstructor;

@Component("MybatisTestJob")
@RequiredArgsConstructor
public class MybatisTestJob implements Job {
	
	 private static final Logger logger = LoggerFactory.getLogger(MybatisTestJob.class);

	 private final MybatisTestService mybatisTestService;
	 
	 JobFactory jobFactory;
	 
	 @Override
	 public void execute(JobExecutionContext context) throws JobExecutionException {
		 logger.info("MybatisTestJob.java: 💡 Quartz Job 실행됨! 현재 시간: {}", LocalDateTime.now());
		 
	     // Service 호출
	     mybatisTestService.printTest();
		 
	 }
}

