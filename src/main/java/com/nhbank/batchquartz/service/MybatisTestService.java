package com.nhbank.batchquartz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nhbank.batchquartz.dto.TestDTO;
import com.nhbank.batchquartz.mapper.TestMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MybatisTestService {
	
	private final TestMapper testMapper;
	private static final Logger logger = LoggerFactory.getLogger(MybatisTestService.class);
	
	public void printTest() {
		List<TestDTO> dataList = testMapper.findAll();
		 for (TestDTO data : dataList) {
			 logger.info("📌 데이터 조회: {}", data);
			 
		 }
	}
	
}
