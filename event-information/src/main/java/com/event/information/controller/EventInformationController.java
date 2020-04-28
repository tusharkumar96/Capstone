package com.event.information.controller;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/job")
public class EventInformationController {
  private final JobLauncher jobLauncher;
  private final Job job;

  @PostMapping
  public BatchStatus runSaveEventInformationJob()
      throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
          JobRestartException, JobInstanceAlreadyCompleteException {
    Map<String, JobParameter> jobParameterMap = new LinkedHashMap<>();
    jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
    JobParameters jobParameters = new JobParameters(jobParameterMap);
    JobExecution jobExecution = jobLauncher.run(job, jobParameters);
    return jobExecution.getStatus();
  }
}
