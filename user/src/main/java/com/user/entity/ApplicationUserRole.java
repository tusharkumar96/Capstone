package com.user.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"applicationUsers"/*,"applicationPermissions" */})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"applicationUsers"/*,"applicationPermissions" */})
@Table(name = "ApplicationUserRole")
public class ApplicationUserRole implements Serializable {
  private static final long serialVersionUID = 2743015285246823972L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer roleId;

  private String role;

  private String roleDescription;

  @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
  private List<ApplicationUser> applicationUsers;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "ApplicationUserRolePermission",
      joinColumns = {@JoinColumn(name = "permissionId")},
      inverseJoinColumns = {@JoinColumn(name = "roleId")})
  private Set<ApplicationPermission> applicationPermissions;
}
