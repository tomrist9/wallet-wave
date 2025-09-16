package com.walletwave.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ConditionalOnProperty(
        name = "spring.data.jpa.auditing.enabled",
        havingValue = "true",
        matchIfMissing = true
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class JpaAuditingConfig {
}
