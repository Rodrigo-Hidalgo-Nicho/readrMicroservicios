package com.monaschinas.readr.user.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResource {
  private Long id;
  private String userName;
  private String imageUrl;
  private String description;
  private Long phone;
  private RoleResource role;
  private UserResource user;
}
