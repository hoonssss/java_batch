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

@Configuration
@RequiredArgsConstructor
public class StepExecutionConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job StepExceutionJob(){
        return jobBuilderFactory.get("StepExceutionJob_start")
            .start(StepException_1())
            .next(StepException_2())
            .next(StepException_3())
            .build();
    }

    @Bean
    public Step StepException_1(){
        return stepBuilderFactory.get("Step_1")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("Step 1");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

    @Bean
    public Step StepException_2(){
        return stepBuilderFactory.get("Step_2")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    //throw new RuntimeException("Step 2");
                    return RepeatStatus.FINISHED;
                }
            })
            //.allowStartIfComplete(true) // 이미 완료된 Step도 다시 시작 가능
            .build();
    }

    public Step StepException_3(){
        return stepBuilderFactory.get("Step_3")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

}
