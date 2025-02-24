//package com.nhbank.batchquartz.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class DataSourceConfig {
//	
//	@Bean(name = "batchDataSource")
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource batchDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	
//	@Bean(name = "batchTransactionManager")
//	public PlatformTransactionManager batchTransactionManager(@Qualifier("batchDataSource") DataSource batchDataSource) {
//		return new DataSourceTransactionManager(batchDataSource);
//	}
//	
//}
