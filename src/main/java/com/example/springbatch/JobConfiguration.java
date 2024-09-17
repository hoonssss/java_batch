package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobCig(){
        return jobBuilderFactory.get("JobConfig")
            .start(JobConfigStep1())
            .next(JobConfigStep2())
            .build();
    }

    @Bean
    public Step JobConfigStep1(){
        return stepBuilderFactory.get("JobConfigStep1")
            .tasklet((ta1,ta2)->{
                System.out.println("JobConfigStep1");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    public Step JobConfigStep2(){
        return stepBuilderFactory.get("JobConfigStep2")
            .tasklet((a,b)->{
                System.out.println("JobConfigStep2");
                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
