package com.event.summary.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("event.summary")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelFileProperties {

  private String fileLocation;
  private String outputFileLocation;
  private Boolean shouldMoveFile;
}
