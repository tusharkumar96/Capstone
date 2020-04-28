package com.user.service;

import com.user.entity.ApplicationPermission;
import com.user.entity.ApplicationUserRole;
import com.user.exception.PermissionNotFoundException;
import com.user.exception.UserRoleAlreadyExistsException;
import com.user.exception.UserRoleNotFoundException;
import com.user.repository.ApplicationUserRoleRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationUserRoleService {

  private final ApplicationUserRoleRepository applicationUserRoleRepository;
  private final ApplicationPermissionService applicationPermissionService;

  @Transactional
  public ApplicationUserRole createNewUserRole(String role) {
    Optional<ApplicationUserRole> applicationUserRole =
        applicationUserRoleRepository.findByRole("ROLE_" + role.toUpperCase());
    applicationUserRole.ifPresent(
        (userRole) -> {
          throw new UserRoleAlreadyExistsException(
              String.format("UserRole %s already exists", userRole));
        });
    ApplicationUserRole userRole =
        ApplicationUserRole.builder()
            .role("ROLE_" + role.toUpperCase())
            .roleDescription(role.toUpperCase())
            .build();
    return applicationUserRoleRepository.save(userRole);
  }

  public ApplicationUserRole getApplicationUserRole(String userRole) {
    Optional<ApplicationUserRole> applicationUserRole =
        applicationUserRoleRepository.findByRole(userRole);
    return applicationUserRole.orElseThrow(
        () -> new UserRoleNotFoundException(String.format("UserRole %s does not exist", userRole)));
  }

  @Transactional
  public ApplicationUserRole addPermissionsToRole(Integer roleId, List<Integer> permissions) {
    List<ApplicationPermission> applicationPermissions =
        applicationPermissionService.getPermissions(permissions);
    List<Integer> userPermissions =
        applicationPermissions
            .parallelStream()
            .map(ApplicationPermission::getPermissionId)
            .collect(Collectors.toList());
    if (!userPermissions.containsAll(permissions)) {
      throw new PermissionNotFoundException("Permission cannot be found");
    }
    ApplicationUserRole applicationUserRole =
        applicationUserRoleRepository
            .findById(roleId)
            .orElseThrow(() -> new UserRoleNotFoundException("UserRole cannot be found"));
    applicationUserRole.getApplicationPermissions().addAll(applicationPermissions);
    return applicationUserRole;
  }
}
