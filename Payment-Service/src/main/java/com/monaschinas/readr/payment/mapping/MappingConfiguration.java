package com.monaschinas.readr.payment.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("paymentMicroServiceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PlanMapper planMapper(){ return new PlanMapper(); }

    @Bean
    public SubscriptionMapper subscriptionMapper() { return new SubscriptionMapper(); }
}
