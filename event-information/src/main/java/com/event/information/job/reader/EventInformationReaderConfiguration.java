package com.event.information.job.reader;

import com.event.information.entity.EventInformation;
import com.event.information.properties.ExcelFileProperties;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import java.io.File;

@Configuration
@AllArgsConstructor
public class EventInformationReaderConfiguration {
  private final ResourceLoader resourceLoader;
  private final ExcelFileProperties excelFileProperties;

  @Bean
  public ItemStreamReader<EventInformation> eventInformationReader() {
    System.err.println(excelFileProperties);
    File file = new File(excelFileProperties.getFileLocation());
    String absolutePath = file.getAbsolutePath();
    UrlResource resource = (UrlResource) resourceLoader.getResource("file://" + absolutePath);
    PoiItemReader<EventInformation> poiItemReader = new PoiItemReader<>();
    poiItemReader.setLinesToSkip(1);
    poiItemReader.setResource(resource);
    poiItemReader.setRowMapper(eventInformationRowMapper());
    return poiItemReader;
  }

  private RowMapper<EventInformation> eventInformationRowMapper() {
    return new EventInformationRowMapper();
  }
}
