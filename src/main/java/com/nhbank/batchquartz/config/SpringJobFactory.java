package com.nhbank.batchquartz.config;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
// Quartz의 Job은 Spring DI가 적용되지 않음
// 따라서 직접 SpringJobFactory를 정의해주고 이를 QuartzConfig의 Scheduler에 세팅해줘서 Spring DI를 적용하여 Bean을 주입할 수 있도록 적용힘
public class SpringJobFactory implements JobFactory {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
		String jobName = bundle.getJobDetail().getKey().getName();
        return applicationContext.getBean(jobName, Job.class);
	}


}
