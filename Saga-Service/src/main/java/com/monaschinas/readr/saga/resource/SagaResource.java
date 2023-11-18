package com.monaschinas.readr.saga.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SagaResource {
    private Long id;
    private String title;
    private String synopsis;
    private SagaStatusResource sagaStatus;
}
