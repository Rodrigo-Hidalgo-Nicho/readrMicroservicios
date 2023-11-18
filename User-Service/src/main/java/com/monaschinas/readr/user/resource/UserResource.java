package com.monaschinas.readr.user.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
