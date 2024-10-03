package com.example.springbatch.executionContext;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ExecutionContextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("ExecutionContextConfigurationJob")
            .start(executionStep1())
            .next(executionStep2())
            .next(executionStep3())
            .next(executionStep4())
            .build();
    }

    @Bean
    public Step executionStep1(){
        return stepBuilderFactory.get("ExecutionContextConfigurationStep1")
            .tasklet(executionContextTasklet1)
            .build();
    }

    @Bean
    public Step executionStep2(){
        return stepBuilderFactory.get("ExecutionContextConfigurationStep2")
            .tasklet(executionContextTasklet2)
            .build();
    }

    @Bean
    public Step executionStep3(){
        return stepBuilderFactory.get("ExecutionContextConfigurationStep3")
            .tasklet(executionContextTasklet3)
            .build();
    }

    @Bean
    public Step executionStep4(){
        return stepBuilderFactory.get("ExecutionContextConfigurationStep4")
            .tasklet(executionContextTasklet4)
            .build();
    }
}
