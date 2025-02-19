package com.nhbank.batchquartz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jobs")
public class JobProperties {
    private List<JobConfig> list;

    @Getter
    @Setter
    public static class JobConfig {
        private String name;
        private String jobClass;
        private String schedule;
    }
}
	