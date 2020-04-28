package com.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString(exclude = {"userGroups"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"userGroups"})
@Builder
@Table(name = "ApplicationUser")
public class ApplicationUser implements Serializable, UserDetails, CredentialsContainer {
  private static final long serialVersionUID = -8081529646963419776L;

  @Id
  @Column(nullable = false, unique = true, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;

  private String firstName;

  private String lastName;

  private String preferredName;

  private String userName;

  private String password;

  private Boolean isAccountNonExpired;

  private Boolean isAccountNonLocked;

  private Boolean isCredentialsNonExpired;

  private Boolean isEnabled;

  @ManyToOne
  @JoinColumn(name = "roleId", referencedColumnName = "roleId")
  private ApplicationUserRole userRole;

  @ManyToMany(mappedBy = "applicationUsers", fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<ApplicationUserGroup> userGroups;

  @Override
  public void eraseCredentials() {
    password = null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<SimpleGrantedAuthority> grantedAuthorities =
        getUserGroups().stream()
            .map(applicationUserGroup -> applicationUserGroup.getApplicationPermissions())
            .flatMap(Set::stream)
            .map(
                applicationPermission ->
                    new SimpleGrantedAuthority(applicationPermission.getPermission()))
            .collect(Collectors.toSet());
    userRole.getApplicationPermissions().stream()
        .map(
            applicationPermission ->
                new SimpleGrantedAuthority(applicationPermission.getPermission()))
        .forEach(grantedAuthorities::add);
    grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
    return grantedAuthorities;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}
