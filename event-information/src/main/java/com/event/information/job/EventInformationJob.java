package com.event.information.job;

import com.event.information.entity.EventInformation;
import com.event.information.job.tasklet.FileMoveTasklet;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EventInformationJob {
  private final ItemStreamReader<EventInformation> eventInformationReader;
  private final ItemWriter<EventInformation> eventInformationWriter;
  private final FileMoveTasklet fileMoveTasklet;
  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job saveEventInformationJob() {
    Step step =
        stepBuilderFactory
            .get("Event-Infomation-Load-Step")
            .<EventInformation, EventInformation>chunk(200)
            .reader(eventInformationReader)
            .writer(eventInformationWriter)
            .build();
    TaskletStep fileMoveStep =
        stepBuilderFactory.get("Event-Information-Move-Step").tasklet(fileMoveTasklet).build();
    return jobBuilderFactory
        .get("Event-Information-Job")
        .incrementer(new RunIdIncrementer())
        .start(step)
        .next(fileMoveStep)
        .build();
  }
}
