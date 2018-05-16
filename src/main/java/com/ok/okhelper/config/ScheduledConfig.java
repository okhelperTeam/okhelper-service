package com.ok.okhelper.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Author: zc
 * Date: 2018/4/30
 * Description:
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {



    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(setTaskExecutors());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor setTaskExecutors() {
        // 5个线程来处理。
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        return scheduledExecutorService;
    }
}
