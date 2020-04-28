package com.event.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "EventInformation")
public class EventInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer eventInformationId;

  private String eventId;

  private String baseLocation;

  private String beneficiaryName;

  private String councilName;

  private String eventName;

  private String eventDescription;

  private String eventDate;

  private String employeeId;

  private String employeeName;

  private String volunteerHours;

  private String travelHours;

  private String livesImpacted;

  private String businessUnit;

  private String status;

  private String IIEPCategory;
}
