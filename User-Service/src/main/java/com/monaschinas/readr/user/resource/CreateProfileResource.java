package com.monaschinas.readr.user.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileResource {

  @NotNull
  @NotBlank
  private String userName;

  @NotNull
  @NotBlank
  private String imageUrl;

  @NotNull
  @NotBlank
  private String description;

  @NotNull
  private Long phone;

  @NotNull
  private Long roleId;

  @NotNull
  private Long userId;
}
