package com.example.springbatch.jobInstance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobInstance {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobIns(){
        return jobBuilderFactory.get("JobInstance")
            .start(JobStep1())
            .next(JobStep2())
            .build();
    }

    @Bean
    public Step JobStep1(){
        return stepBuilderFactory.get("JobInstanceStep1")
            .tasklet((a,b)->{
                System.out.println("JobInstance Step1()");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    public Step JobStep2(){
        return stepBuilderFactory.get("JobInstanceStep2")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("JobInstance Step2()");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }


}
