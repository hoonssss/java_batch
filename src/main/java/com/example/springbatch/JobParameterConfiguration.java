package com.example.springbatch;

import java.awt.image.RasterOp;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
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
public class JobParameterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job JobParameterConfig(){
        return jobBuilderFactory.get("jobParameterConfig")
            .start(JobParameterConfigStep_1())
            .next(JobParameterConfigStep_2())
            .build();
    }

    @Bean
    public Step JobParameterConfigStep_1(){
        return stepBuilderFactory.get("JobParameterConfig_1")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                    JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
                    jobParameters.getString("String");
                    jobParameters.getDate("Date");
                    jobParameters.getLong("Long");
                    jobParameters.getDouble("Double");

                    Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();

                    System.out.println("JobParameterConfig_1 start");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }

    @Bean
    public Step JobParameterConfigStep_2(){
        return stepBuilderFactory.get("JobParameterConfig_2")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("JobParameterConfig_2 start");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }


}
