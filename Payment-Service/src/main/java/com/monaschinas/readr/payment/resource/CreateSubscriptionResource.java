package com.monaschinas.readr.payment.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
public class CreateSubscriptionResource {
    @NotNull
    private Date startedAt;

    @NotNull
    private Date finishedAt;

    //Relationships
    @NotNull
    private Long userId;

    @NotNull
    private Long planId;
}
