package com.zhdanov.recipebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class UserDto {
  public Long id;
  public String firstName;
  public String lastName;
  public String email;
  @JsonIgnoreProperties
  public String password;
}
