package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.ApplicationUserGroup;

import java.util.Optional;

@Repository
public interface ApplicationUserGroupRepository
    extends JpaRepository<ApplicationUserGroup, Integer> {

  Optional<ApplicationUserGroup> findByUserGroupName(String userGroupName);
}
