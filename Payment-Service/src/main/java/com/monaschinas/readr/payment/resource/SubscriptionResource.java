package com.monaschinas.readr.payment.resource;

import com.monaschinas.readr.payment.domain.model.Plan;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
public class SubscriptionResource {
    private Long id;
    private Date startedAt;
    private Date finishedAt;
    private Long userId;
    private Plan plan;
}
