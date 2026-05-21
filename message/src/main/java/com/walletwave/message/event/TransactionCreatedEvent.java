package com.walletwave.message.event;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionCreatedEvent(
        String id,
        BigDecimal amount,
        String accountId,
        Instant createdAt
) {
}