package com.event.summary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "EventSummary")
public class EventSummary {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer eventSummaryId;

  private String eventId;

  private String eventMonth;

  private String baseLocation;

  private String beneficiaryName;

  private String venueAddress;

  private String councilName;

  private String project;

  private String category;

  private String eventName;

  private String eventDescription;

  private String eventDate;

  private String totalVolunteersCount;

  private String totalVolunteerHours;

  private String totalTravelHours;

  private String overallVolunteerHours;

  private String livesImpacted;

  private String activityType;

  private String status;

  private String eventPocId;

  private String eventPocName;

  private String pocContactNumber;
}
