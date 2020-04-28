package com.event.summary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.summary.entity.EventSummary;

@Repository
public interface EventSummaryRepository extends JpaRepository<EventSummary, Integer> {}
