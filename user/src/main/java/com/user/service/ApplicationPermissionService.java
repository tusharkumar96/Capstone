package com.user.service;

import com.user.entity.ApplicationPermission;
import com.user.exception.PermissionAlreadyExistsException;
import com.user.repository.ApplicationPermissionRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationPermissionService {

  private final ApplicationPermissionRepository applicationPermissionRepository;

  public ApplicationPermission createNewApplicationPermission(String permission) {

    Optional<ApplicationPermission> applicationPermission =
        applicationPermissionRepository.findByPermission("PERMISSION_" + permission.toUpperCase());
    applicationPermission.ifPresent(
        (var) -> {
          throw new PermissionAlreadyExistsException(
              String.format("ApplicationPermission %s already exists", permission));
        });
    ApplicationPermission newPermission =
        ApplicationPermission.builder()
            .permission("PERMISSION_" + permission.toUpperCase())
            .permissionDescription(permission.toUpperCase())
            .build();
    return applicationPermissionRepository.save(newPermission);
  }

  public List<ApplicationPermission> getPermissions(List<Integer> permissionIds) {
    return applicationPermissionRepository.findAllById(permissionIds);
  }
}
