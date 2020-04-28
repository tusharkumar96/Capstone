package com.user.service;

import com.google.common.collect.Sets;
import com.user.entity.ApplicationPermission;
import com.user.entity.ApplicationUser;
import com.user.entity.ApplicationUserGroup;
import com.user.exception.PermissionNotFoundException;
import com.user.exception.UserGroupAlreadyExistsException;
import com.user.exception.UserGroupNotFoundException;
import com.user.exception.UserNotFoundException;
import com.user.repository.ApplicationUserGroupRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationUserGroupService {

  private final ApplicationUserGroupRepository applicationUserGroupRepository;
  private final ApplicationUserService applicationUserService;
  private final ApplicationPermissionService applicationPermissionService;

  public ApplicationUserGroup createNewGroup(String groupName) {
    Optional<ApplicationUserGroup> userGroup =
        applicationUserGroupRepository.findByUserGroupName("GROUP_" + groupName.toUpperCase());
    userGroup.ifPresent(
        (group) -> {
          throw new UserGroupAlreadyExistsException(
              String.format("UserGroup %s already exists", groupName));
        });

    ApplicationUserGroup applicationUserGroup =
        ApplicationUserGroup.builder()
            .userGroupName("GROUP_" + groupName.toUpperCase())
            .userGroupDescritpion(groupName.toUpperCase())
            .build();

    return applicationUserGroupRepository.save(applicationUserGroup);
  }

  public ApplicationUserGroup addUsersToGroup(Integer userGroupId, List<Integer> userIds) {
    Optional<ApplicationUserGroup> applicationUserGroup =
        applicationUserGroupRepository.findById(userGroupId);
    ApplicationUserGroup userGroup =
        applicationUserGroup.orElseThrow(
            () -> new UserGroupNotFoundException("UserGroup not found"));
    List<ApplicationUser> users = applicationUserService.getUsers(userIds);
    List<Integer> ids =
        users.parallelStream().map(ApplicationUser::getUserId).collect(Collectors.toList());
    if (!ids.containsAll(userIds)) {
      throw new UserNotFoundException("User does not exist");
    }
    userGroup.getApplicationUsers().addAll(users);
    applicationUserGroupRepository.save(userGroup);
    return userGroup;
  }

  public ApplicationUserGroup addPermissionsToGroup(
      Integer userGroupId, List<Integer> permissionIds) {
    Optional<ApplicationUserGroup> applicationUserGroup =
        applicationUserGroupRepository.findById(userGroupId);
    ApplicationUserGroup userGroup =
        applicationUserGroup.orElseThrow(
            () -> new UserGroupNotFoundException("UserGroup not found"));
    List<ApplicationPermission> permissions =
        applicationPermissionService.getPermissions(permissionIds);
    List<Integer> ids =
        permissions
            .parallelStream()
            .map(ApplicationPermission::getPermissionId)
            .collect(Collectors.toList());
    if (!ids.containsAll(permissionIds)) {
      throw new PermissionNotFoundException("Permission does not exist");
    }
    userGroup.setApplicationPermissions(Sets.newHashSet(permissions));
    applicationUserGroupRepository.save(userGroup);
    return userGroup;
  }
}
