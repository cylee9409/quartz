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
public class SpringJobFactory implements JobFactory {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
		String jobName = bundle.getJobDetail().getKey().getName();
        return applicationContext.getBean(jobName, Job.class);
	}


}
