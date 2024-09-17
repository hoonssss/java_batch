package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job customJob_1(){
        return jobBuilderFactory.get("CustomJob")
            .start(customStep1())
            .next(customStep2())
            .build();
    }

    @Bean
    public Step customStep1(){
        return stepBuilderFactory.get("CustomStep1")
            .tasklet(new CustomTasklet())
            .build();
    }

    @Bean
    public Step customStep2(){
        return stepBuilderFactory.get("CustomStep2")
            .tasklet((a,b)->{
                System.out.println("CustomStep2");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

}
