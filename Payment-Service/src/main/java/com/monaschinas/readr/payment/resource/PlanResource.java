package com.monaschinas.readr.payment.resource;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
public class PlanResource {
    private Long id;
    private String name;
    private String description;
    private Long amount;
}
