package com.user.controller;

import com.user.dto.ApplicationUserRequestDTO;
import com.user.entity.ApplicationUser;
import com.user.exception.UserCannotBeCreatedException;
import com.user.service.ApplicationUserService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class ApplicationUserController {

  private final ApplicationUserService applicationUserService;

  @PostMapping
  public ResponseEntity createNewUser(
      @RequestBody ApplicationUserRequestDTO applicationUserRequestDTO) {
    ApplicationUser newUser = applicationUserService.createNewUser(applicationUserRequestDTO);
    return Optional.ofNullable(newUser)
        .map(user -> ResponseEntity.created(URI.create("")).build())
        .orElseThrow(() -> new UserCannotBeCreatedException("User cannot be created"));
  }

  @GetMapping
  public ResponseEntity<List<ApplicationUser>> getAllUsers() {
    List<ApplicationUser> applicationUsers = applicationUserService.getApplicationUsers();
    return Optional.ofNullable(applicationUsers)
        .map(users -> ResponseEntity.ok().body(users))
        .orElseThrow(() -> new UserCannotBeCreatedException("User cannot be created"));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApplicationUser> getUser(@PathVariable Integer userId) {
    Optional<ApplicationUser> applicationUser =
        applicationUserService.getApplicationUserById(userId);
    return applicationUser
        .map(user -> ResponseEntity.ok().body(user))
        .orElseThrow(() -> new UserCannotBeCreatedException("User cannot be created"));
  }
}
