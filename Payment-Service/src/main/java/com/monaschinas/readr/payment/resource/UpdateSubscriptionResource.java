package com.monaschinas.readr.payment.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
public class UpdateSubscriptionResource {
    private Long id;

    @NotNull
    private Date startedAt;

    @NotNull
    private Date finishedAt;
}
