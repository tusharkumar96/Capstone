package com.event.summary.job;

import com.event.summary.entity.EventSummary;
import com.event.summary.job.tasklet.FileMoveTasklet;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EventSummaryJob {

  private final ItemReader<EventSummary> eventSummaryItemReader;
  private final ItemWriter<EventSummary> eventSummaryWriter;
  private final FileMoveTasklet fileMoveTasklet;

  @Bean
  public Job saveEventSummaryJob(
      JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    Step step1 =
        stepBuilderFactory
            .get("Event-Summary-Load-Step")
            .<EventSummary, EventSummary>chunk(200)
            .reader(eventSummaryItemReader)
            .writer(eventSummaryWriter)
            .build();
    Step step2 = stepBuilderFactory.get("Event-Summary-Move-Step").tasklet(fileMoveTasklet).build();
    return jobBuilderFactory
        .get("EventSummaryLoad")
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .next(step2)
        .build();
  }
}
