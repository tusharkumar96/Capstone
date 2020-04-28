package com.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"applicationUserGroups", "applicationUserRoles"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"applicationUserGroups", "applicationUserRoles"})
@Builder
@Table(name = "ApplicationPermission")
public class ApplicationPermission implements Serializable {
  private static final long serialVersionUID = -1694014883505297939L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer permissionId;

  private String permission;

  private String permissionDescription;

  @ManyToMany(mappedBy = "applicationPermissions", fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<ApplicationUserGroup> applicationUserGroups;

  @ManyToMany(mappedBy = "applicationPermissions", fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<ApplicationUserRole> applicationUserRoles;
}
