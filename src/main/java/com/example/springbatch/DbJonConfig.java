package com.example.springbatch;

import lombok.RequiredArgsConstructor;
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
import org.w3c.dom.ls.LSOutput;

@RequiredArgsConstructor
@Configuration
public class DbJonConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jon(){
        return jobBuilderFactory.get("HelloJob")
            .start(step1())
            .next(step2())
            .build();
    }

    public Step step1(){
        return stepBuilderFactory.get("helloDbStep1")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("helloDbStep1");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

    public Step step2(){
        return stepBuilderFactory.get("step2")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("helloDbStep2");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

}
