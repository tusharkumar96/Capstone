package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.ApplicationPermission;

import java.util.Optional;

@Repository
public interface ApplicationPermissionRepository
    extends JpaRepository<ApplicationPermission, Integer> {

  Optional<ApplicationPermission> findByPermission(String permission);
}
