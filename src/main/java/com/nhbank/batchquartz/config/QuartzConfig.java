package com.nhbank.batchquartz.config;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class QuartzConfig {
    private final JobProperties jobProperties;
    
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(List<JobDetail> jobDetails, List<Trigger> triggers) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobDetails(jobDetails.toArray(new JobDetail[0]));
        factoryBean.setTriggers(triggers.toArray(new Trigger[0]));
        return factoryBean;
    }
    
    @Bean
    public List<JobDetail> jobDetails() {
        List<JobDetail> jobDetails = new ArrayList<>();
        for (JobProperties.JobConfig job : jobProperties.getList()) {
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
            String cronExpression = jobProperties.getList().stream()
                    .filter(j -> j.getName().equals(jobName))
                    .map(JobProperties.JobConfig::getSchedule)
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
