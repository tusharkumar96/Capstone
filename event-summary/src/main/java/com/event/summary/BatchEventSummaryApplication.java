package com.event.summary;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchEventSummaryApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchEventSummaryApplication.class, args);
  }
}
