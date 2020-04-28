package com.event.summary.job.writer;

import com.event.summary.entity.EventSummary;
import com.event.summary.repository.EventSummaryRepository;

import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EventSummaryWriter implements ItemWriter<EventSummary> {
  private final EventSummaryRepository eventSummaryRepository;

  @Override
  public void write(List<? extends EventSummary> list) {
    eventSummaryRepository.saveAll(list);
  }
}
