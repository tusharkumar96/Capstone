package com.user.service;

import com.user.dto.ApplicationUserRequestDTO;
import com.user.entity.ApplicationUser;
import com.user.entity.ApplicationUserRole;
import com.user.exception.UserAlreadyExistsException;
import com.user.repository.ApplicationUserRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserService {

  private final ApplicationUserRepository applicationUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserRoleService applicationUserRoleService;

  public ApplicationUser createNewUser(ApplicationUserRequestDTO applicationUserRequestDTO) {
    Optional<ApplicationUser> existingUser =
        applicationUserRepository.findByUserName(applicationUserRequestDTO.getUserName());
    existingUser.ifPresent(
        (user) -> {
          throw new UserAlreadyExistsException(
              String.format(
                  "User with username %s already exists", applicationUserRequestDTO.getUserName()));
        });
    ApplicationUserRole role_newuser =
        applicationUserRoleService.getApplicationUserRole("ROLE_NEWUSER");
    ApplicationUser applicationUser =
        ApplicationUser.builder()
            .firstName(applicationUserRequestDTO.getFirstName())
            .lastName(applicationUserRequestDTO.getLastName())
            .preferredName(applicationUserRequestDTO.getPreferredName())
            .userName(applicationUserRequestDTO.getUserName())
            .password(passwordEncoder.encode(applicationUserRequestDTO.getPassword()))
            .isAccountNonExpired(true)
            .isAccountNonLocked(true)
            .isCredentialsNonExpired(true)
            .isEnabled(true)
            .userRole(role_newuser)
            .build();
    return applicationUserRepository.save(applicationUser);
  }

  public List<ApplicationUser> getApplicationUsers() {
    return applicationUserRepository.findAll();
  }

  public Optional<ApplicationUser> getApplicationUserById(Integer id) {
    return applicationUserRepository.findById(id);
  }

  List<ApplicationUser> getUsers(List<Integer> userIds) {
    return applicationUserRepository.findAllById(userIds);
  }
}
