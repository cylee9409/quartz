package com.nhbank.batchquartz.job;

import java.time.LocalDateTime;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nhbank.batchquartz.dto.TestDTO;
import com.nhbank.batchquartz.service.MybatisTestService;

import lombok.RequiredArgsConstructor;

@Component("MybatisTestJob")
@RequiredArgsConstructor
@DisallowConcurrentExecution //Quartz에서 같은 Job 클래스의 여러 인스턴스가 동시에 실행되지 않도록 보장하는 어노테이션
public class MybatisTestJob implements Job {
	
	 private static final Logger logger = LoggerFactory.getLogger(MybatisTestJob.class);

	 private final MybatisTestService mybatisTestService;
	 
	 JobFactory jobFactory;
	 
	 @Override
	 public void execute(JobExecutionContext context) throws JobExecutionException {
		 logger.info("MybatisTestJob.java: 💡 Quartz Job 실행됨! 현재 시간: {}", LocalDateTime.now());
		 
		 TestDTO registerTest = new TestDTO();
		 registerTest.setId(mybatisTestService.retrieveNextId());
		 registerTest.setName("테스트 데이터 " + registerTest.getId());
		 
		 // 테스트 등록
		 mybatisTestService.registerTest(registerTest);
		 
	     // 테스트 조회
	     mybatisTestService.printTest();
	     logger.info("📌 데이터 건수: {}", mybatisTestService.countTest());
	     
	     TestDTO registerTest2 = new TestDTO();
	     registerTest2.setId(mybatisTestService.retrieveNextId());
		 registerTest2.setName("테스트 데이터 " + registerTest2.getId());
		 
		 // 테스트 등록
		 mybatisTestService.registerTest(registerTest2);
		 
	     // 테스트 조회
	     mybatisTestService.printTest();
	     logger.info("📌 데이터 건수: {}", mybatisTestService.countTest());
	     
//	     if (true) {
//             throw new RuntimeException("🔥 Quartz Job 강제 예외 발생!");
//         }
//	     
	     TestDTO registerTest3 = new TestDTO();
	     registerTest3.setId(mybatisTestService.retrieveNextId());
		 registerTest3.setName("테스트 데이터 " + registerTest3.getId());
		 
		 // 테스트 등록
		 mybatisTestService.registerTest(registerTest3);
		 
	     // 테스트 조회
	     mybatisTestService.printTest();
	     logger.info("📌 데이터 건수: {}", mybatisTestService.countTest());
		 
	 }
}

