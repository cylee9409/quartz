package com.nhbank.batchquartz.config;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class QuartzConfig {
	
	@Autowired
	private JobConfig jobConfig;
	
	@Autowired
    private SpringJobFactory springJobFactory;

    @Bean
    public Scheduler scheduler() throws Exception {
    	
    	SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    	Scheduler scheduler = schedulerFactory.getScheduler();
    	scheduler.setJobFactory(springJobFactory);
    	
    	for (int i = 0; i < jobDetails().size(); i++) {
    		
    		if (scheduler.checkExists(jobDetails().get(i).getKey())) {
    			scheduler.deleteJob(jobDetails().get(i).getKey());
    		}
    		
			scheduler.scheduleJob(jobDetails().get(i), jobTriggers(jobDetails()).get(i));
		}
    	
    	scheduler.start();
    	
        return scheduler;
    }
    
    @Bean
    public List<JobDetail> jobDetails() {
    	
        List<JobDetail> jobDetails = new ArrayList<>();
        
        for (JobConfig.JobInfo job : jobConfig.getList()) {
            try {
                Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(job.getJobClass());
                
                JobDetail jobDetail = JobBuilder.newJob(jobClass)
                        .withIdentity(job.getName())
                        .storeDurably()
                        .build();
                
                jobDetails.add(jobDetail);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("❌ Job 클래스를 찾을 수 없음: " + job.getJobClass(), e);
            }
        }
        return jobDetails;
    }

    @Bean
    public List<Trigger> jobTriggers(List<JobDetail> jobDetails) {
        List<Trigger> triggers = new ArrayList<>();
        
        for (JobDetail jobDetail : jobDetails) {
            String jobName = jobDetail.getKey().getName();
            
            String cronExpression = jobConfig.getList().stream()
                    .filter(j -> j.getName().equals(jobName))
                    .map(JobConfig.JobInfo::getSchedule)
                    .findFirst()
                    .orElse("*/1 * * * * ?"); // 기본값
                        
            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity("trigger-" + jobName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .build();
            triggers.add(trigger);
        }
        return triggers;
    }
}
