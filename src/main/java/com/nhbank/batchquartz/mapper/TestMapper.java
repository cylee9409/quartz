package com.nhbank.batchquartz.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nhbank.batchquartz.dto.TestDTO;

@Mapper
public interface TestMapper {
	List<TestDTO> findAll();
	Long retrieveNextId();
	int registerTest(TestDTO testDTO);
	int countTestData();
}