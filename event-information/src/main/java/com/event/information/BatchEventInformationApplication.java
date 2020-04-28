package com.event.information;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchEventInformationApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchEventInformationApplication.class, args);
  }
}
