package com.user.controller;

import com.user.entity.ApplicationUserGroup;
import com.user.exception.UserGroupCannotBeCreatedException;
import com.user.service.ApplicationUserGroupService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/usergroups")
public class ApplicationUserGroupController {

  private final ApplicationUserGroupService applicationUserGroupService;

  @PostMapping
  //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity createNewGroup(@RequestBody String groupName) {
    ApplicationUserGroup newGroup = applicationUserGroupService.createNewGroup(groupName);
    return Optional.ofNullable(newGroup)
        .map(group -> ResponseEntity.created(URI.create("")).build())
        .orElseThrow(() -> new UserGroupCannotBeCreatedException("User group cannot be created"));
  }

  @PatchMapping("/{userGroupId}/users")
  public ResponseEntity addUsersToGroup(
      @PathVariable Integer userGroupId, @RequestBody List<Integer> userIds) {
    ApplicationUserGroup applicationUserGroup =
        applicationUserGroupService.addUsersToGroup(userGroupId, userIds);
    return Optional.ofNullable(applicationUserGroup)
        .map(group -> ResponseEntity.ok().build())
        .orElse(ResponseEntity.badRequest().build());
  }

  @PatchMapping("/{userGroupId}/permissions")
  public ResponseEntity addPermissionsToGroup(
      @PathVariable Integer userGroupId, @RequestBody List<Integer> permissionIds) {
    ApplicationUserGroup applicationUserGroup =
        applicationUserGroupService.addPermissionsToGroup(userGroupId, permissionIds);
    return Optional.ofNullable(applicationUserGroup)
        .map(group -> ResponseEntity.ok().build())
        .orElse(ResponseEntity.badRequest().build());
  }
}
