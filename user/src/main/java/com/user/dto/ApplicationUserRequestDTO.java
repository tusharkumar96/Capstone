package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApplicationUserRequestDTO implements Serializable {
  private static final long serialVersionUID = 668903296870913389L;

  private String firstName;

  private String lastName;

  private String preferredName;

  private String userName;

  private String password;
}
