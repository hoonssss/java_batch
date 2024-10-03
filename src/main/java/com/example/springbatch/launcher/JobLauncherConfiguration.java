package com.example.springbatch.launcher;

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

@RequiredArgsConstructor
@Configuration
public class JobLauncherConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobLauncherConfig(){
        return jobBuilderFactory.get("jobLauncher")
            .start(LauncherStep1())
            .next(LauncherStep1())
            .build();
    }

    @Bean
    public Step LauncherStep1() {
        return stepBuilderFactory.get("launcherStep1")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("launcherStep1");
                    return RepeatStatus.FINISHED;
                }
            }).build();
    }

    @Bean
    public Step LauncherStep2() {
        return stepBuilderFactory.get("launcherStep2")
            .tasklet((a,b)->{
                System.out.println("launcherStep2");
                return RepeatStatus.FINISHED;
        }).build();
    }

}
