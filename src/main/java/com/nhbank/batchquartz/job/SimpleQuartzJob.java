package com.nhbank.batchquartz.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleQuartzJob implements Job {
	
	 private static final Logger logger = LoggerFactory.getLogger(SimpleQuartzJob.class);

	    @Override
	    public void execute(JobExecutionContext context) throws JobExecutionException {
	        logger.info("SimpleQuartzJob.java: 💡 Quartz Job 실행됨! 현재 시간: {}", LocalDateTime.now());
	    }

}
