package com.example.springbatch.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobRepositoryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job jobRepositoryTest(){
        return jobBuilderFactory.get("jobRepositoryStart")
            .start(jobRepositoryStep1())
            .next(jobRepositoryStep2())
            .listener(jobExecutionListener)
            .build();
    }

    @Bean
    public Step jobRepositoryStep1(){
        return stepBuilderFactory.get("jobRepositoryStep1")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

    @Bean
    public Step jobRepositoryStep2(){
        return stepBuilderFactory.get("jobRepositoryStep2")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }


}
