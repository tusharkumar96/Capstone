package com.event.information.job.reader;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.event.information.entity.EventInformation;

public class EventInformationRowMapper implements RowMapper<EventInformation> {
  @Override
  public EventInformation mapRow(RowSet rs) throws Exception {
    String eventId = rs.getColumnValue(0);
    String baseLocation = rs.getColumnValue(1);
    String beneficiaryName = rs.getColumnValue(2);
    String councilName = rs.getColumnValue(3);
    String eventName = rs.getColumnValue(4);
    String eventDescription = rs.getColumnValue(5);
    String eventDate = rs.getColumnValue(6);
    String employeeId = rs.getColumnValue(7);
    String employeeName = rs.getColumnValue(8);
    String volunteerHours = rs.getColumnValue(9);
    String travelHours = rs.getColumnValue(10);
    String livesImpacted = rs.getColumnValue(11);
    String businessUnit = rs.getColumnValue(12);
    String status = rs.getColumnValue(13);
    String IIEPCategory = rs.getColumnValue(14);
    EventInformation eventInformation = new EventInformation();
    eventInformation.setEventId(eventId);
    eventInformation.setBaseLocation(baseLocation);
    eventInformation.setBeneficiaryName(beneficiaryName);
    eventInformation.setCouncilName(councilName);
    eventInformation.setEventName(eventName);
    eventInformation.setEventDescription(eventDescription);
    eventInformation.setEventDate(eventDate);
    eventInformation.setEmployeeId(employeeId);
    eventInformation.setEmployeeName(employeeName);
    eventInformation.setVolunteerHours(volunteerHours);
    eventInformation.setTravelHours(travelHours);
    eventInformation.setLivesImpacted(livesImpacted);
    eventInformation.setBusinessUnit(businessUnit);
    eventInformation.setStatus(status);
    eventInformation.setIIEPCategory(IIEPCategory);
    return eventInformation;
  }
}
