package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.ApplicationUserRole;

import java.util.Optional;

@Repository
public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Integer> {

  Optional<ApplicationUserRole> findByRole(String role);
}
