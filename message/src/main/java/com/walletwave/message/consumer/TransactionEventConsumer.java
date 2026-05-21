package com.walletwave.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletwave.message.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionEventConsumer {

    private final ObjectMapper objectMapper;

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 2000, multiplier = 2.0),
            retryTopicSuffix = "-retry",
            dltTopicSuffix = "-dlt",
            autoCreateTopics = "true"
    )
    @KafkaListener(
            topics = "${wallet-wave.kafka.topics.transaction}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic
    ) throws JsonProcessingException {

        log.info("Received transaction message from topic {}: {}", topic, message);

        TransactionCreatedEvent event =
                objectMapper.readValue(message, TransactionCreatedEvent.class);

        validate(event);

        log.info("Transaction event processed successfully. transactionId={}", event.id());
    }

    private void validate(TransactionCreatedEvent event) {
        if (event.id() == null || event.id().isBlank()) {
            throw new IllegalArgumentException("Transaction id cannot be empty");
        }

        if (event.amount() == null || event.amount().signum() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }

        if (event.accountId() == null || event.accountId().isBlank()) {
            throw new IllegalArgumentException("Account id cannot be empty");
        }
    }

    @DltHandler
    public void handleDlt(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Headers MessageHeaders headers
    ) {
        log.error("Message moved to DLT. topic={}, payload={}", topic, message);
        log.error("DLT headers={}", headers);
    }
}