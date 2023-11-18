package com.monaschinas.readr.saga.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("SagaMicroServiceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public SagaMapper sagaMapper(){ return new SagaMapper(); }

    @Bean
    public SagaStatusMapper sagaStatusMapper(){ return new SagaStatusMapper(); }
}
