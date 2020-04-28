package com.event.summary.job.reader;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.event.summary.entity.EventSummary;

public class EventSummaryRowMapper implements RowMapper<EventSummary> {
  @Override
  public EventSummary mapRow(RowSet rs) {
    EventSummary eventSummary = new EventSummary();
    String eventId = rs.getColumnValue(0);
    String month = rs.getColumnValue(1).toUpperCase();
    String baseLocation = rs.getColumnValue(2);
    String beneficiaryName = rs.getColumnValue(3);
    String venueAddress = rs.getColumnValue(4);
    String councilName = rs.getColumnValue(5);
    String project = rs.getColumnValue(6);
    String category = rs.getColumnValue(7);
    String eventName = rs.getColumnValue(8);
    String eventDescription = rs.getColumnValue(9);
    String eventDate = rs.getColumnValue(10);
    String totalVolunteersCount = rs.getColumnValue(11);
    String totalVolunteerHours = rs.getColumnValue(12);
    String totalTravelHours = rs.getColumnValue(13);
    String overallVolunteeringHours = rs.getColumnValue(14);
    String livesImpacted = rs.getColumnValue(15);
    String activityType = rs.getColumnValue(16);
    String status = rs.getColumnValue(17);
    String pocId = rs.getColumnValue(18);
    String pocName = rs.getColumnValue(19);
    String pocContactNumber = rs.getColumnValue(20);
    eventSummary.setEventId(eventId);
    eventSummary.setEventMonth(month);
    eventSummary.setBaseLocation(baseLocation);
    eventSummary.setBeneficiaryName(beneficiaryName);
    eventSummary.setVenueAddress(venueAddress);
    eventSummary.setCouncilName(councilName);
    eventSummary.setProject(project);
    eventSummary.setCategory(category);
    eventSummary.setEventName(eventName);
    eventSummary.setEventDescription(eventDescription);
    eventSummary.setEventDate(eventDate);
    eventSummary.setTotalVolunteersCount(totalVolunteersCount);
    eventSummary.setTotalVolunteerHours(totalVolunteerHours);
    eventSummary.setTotalTravelHours(totalTravelHours);
    eventSummary.setOverallVolunteerHours(overallVolunteeringHours);
    eventSummary.setLivesImpacted(livesImpacted);
    eventSummary.setActivityType(activityType);
    eventSummary.setStatus(status);
    eventSummary.setEventPocId(pocId);
    eventSummary.setEventPocName(pocName);
    eventSummary.setPocContactNumber(pocContactNumber);
    return eventSummary;
  }
}
