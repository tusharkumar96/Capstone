package com.user.controller;

import com.user.entity.ApplicationPermission;
import com.user.exception.handler.PermissionCannotBeCreatedException;
import com.user.service.ApplicationPermissionService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/permissions")
public class ApplicationPermissionController {

  private final ApplicationPermissionService applicationPermissionService;

  @PostMapping
  public ResponseEntity createNewPermission(@RequestBody String permission) {
    ApplicationPermission newApplicationPermission =
        applicationPermissionService.createNewApplicationPermission(permission);
    return Optional.ofNullable(newApplicationPermission)
        .map(applicationPermission -> ResponseEntity.created(URI.create("")).build())
        .orElseThrow(
            () ->
                new PermissionCannotBeCreatedException("ApplicationPermission cannot be created"));
  }
}
