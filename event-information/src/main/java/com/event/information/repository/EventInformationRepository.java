package com.event.information.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.information.entity.EventInformation;

@Repository
public interface EventInformationRepository extends JpaRepository<EventInformation, Integer> {}
