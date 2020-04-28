package com.user.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode/*(exclude = {"applicationUsers","applicationPermissions"})*/
@Builder
@Table(name = "ApplicationUserGroup")
public class ApplicationUserGroup implements Serializable {
  private static final long serialVersionUID = 6589318399339211123L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userGroupId;

  private String userGroupName;

  private String userGroupDescritpion;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "ApplicationGroupMember",
      joinColumns = {@JoinColumn(name = "userGroupId")},
      inverseJoinColumns = {@JoinColumn(name = "userId")})
  private Set<ApplicationUser> applicationUsers;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "ApplicationUserGroupPermission",
      joinColumns = {@JoinColumn(name = "userGroupId")},
      inverseJoinColumns = {@JoinColumn(name = "permissionId")})
  private Set<ApplicationPermission> applicationPermissions;
}
