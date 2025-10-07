package com.edigest.journalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

public class TransactionConfig {
    @Bean
    public PlatformTransactionManager add(PlatformTransactionManager txManager) {
        return txManager;
    }
}
