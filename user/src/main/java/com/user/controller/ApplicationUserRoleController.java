package com.user.controller;

import com.user.entity.ApplicationUserRole;
import com.user.exception.UserRoleCannotBeCreatedException;
import com.user.service.ApplicationUserRoleService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/userroles")
public class ApplicationUserRoleController {

  private final ApplicationUserRoleService applicationUserRoleService;

  @PostMapping
  public ResponseEntity createNewUserRole(@RequestBody String role) {
    ApplicationUserRole newUserRole = applicationUserRoleService.createNewUserRole(role);
    return Optional.ofNullable(newUserRole)
        .map(userRole -> ResponseEntity.created(URI.create("")).build())
        .orElseThrow(
            () ->
                new UserRoleCannotBeCreatedException(String.format("UserRole cannot be created")));
  }

  @PatchMapping("/{roleId}/permissions")
  public ResponseEntity addPermissionToRole(
      @PathVariable Integer roleId, @RequestBody List<Integer> applicationPermissions) {
    ApplicationUserRole applicationUserRole =
        applicationUserRoleService.addPermissionsToRole(roleId, applicationPermissions);
    return Optional.ofNullable(applicationUserRole)
        .map(userRole -> ResponseEntity.ok().build())
        .orElse(ResponseEntity.badRequest().build());
  }
}
