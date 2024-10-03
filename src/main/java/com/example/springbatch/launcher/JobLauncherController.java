package com.example.springbatch.launcher;

import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
비동기 test를 위하여 web의존성 추가 후 test
 */
@RestController
public class JobLauncherController {

    //@Qualifier("jobLauncherConfig")
    @Autowired
    private Job jobLauncherConfig;

    @Autowired
    private JobLauncher jobLauncher;

    //비동기
    @Autowired
    private BasicBatchConfigurer basicBatchConfigurer;

    @PostMapping("/batch")
    public String launch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
            .addString("id", member.getId())
            .addDate("date", new Date())
            .toJobParameters();

        //비동기
        SimpleJobLauncher jobLauncher1 = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
        jobLauncher1.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher1.run(jobLauncherConfig, jobParameters);
        return "batch_Test_Id";
    }

}
