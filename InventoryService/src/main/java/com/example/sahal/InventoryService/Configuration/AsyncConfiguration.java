package com.example.sahal.InventoryService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfiguration {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(3000);
        executor.setThreadNamePrefix("EmployeeThread");
        executor.initialize();
        return executor;
    }
}
