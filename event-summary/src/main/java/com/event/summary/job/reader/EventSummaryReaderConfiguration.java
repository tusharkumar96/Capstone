package com.event.summary.job.reader;

import com.event.summary.entity.EventSummary;
import com.event.summary.properties.ExcelFileProperties;

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
public class EventSummaryReaderConfiguration {

  private final ResourceLoader resourceLoader;
  private final ExcelFileProperties excelFileProperties;

  @Bean
  ItemStreamReader<EventSummary> eventSummaryReader() {
    File file = new File(excelFileProperties.getFileLocation());
    String absolutePath = file.getAbsolutePath();
    PoiItemReader<EventSummary> poiItemReader = new PoiItemReader<>();
    poiItemReader.setLinesToSkip(1);
    UrlResource resource = (UrlResource) resourceLoader.getResource("file://" + absolutePath);
    poiItemReader.setResource(resource);
    poiItemReader.setRowMapper(eventSummaryRowMapper());
    return poiItemReader;
  }

  private RowMapper<EventSummary> eventSummaryRowMapper() {
    return new EventSummaryRowMapper();
  }
}
