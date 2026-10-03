package com.example.rabbitmqapp.service;

import com.example.rabbitmqapp.config.RabbitMQConfig;
import com.example.rabbitmqapp.dto.EmailMessageRequest;
import com.example.rabbitmqapp.dto.SmsMessageRequest;
import com.example.rabbitmqapp.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public String sendEmailNotification(EmailMessageRequest emailMessageRequest) {
        log.info("Sending email notification");
        String messageId = UUID.randomUUID().toString();
        Message message = Message.builder()
                .id(messageId)
                .type("EMAIL")
                .recipient(emailMessageRequest.getRecipient())
                .subject(emailMessageRequest.getSubject())
                .content(emailMessageRequest.getContent())
                .priority("HIGH")
                .timestamp(LocalDateTime.now())
                .build();
        try {
            log.info("Sending email-notification message {} with the message id {} to RabbitMQ", message, messageId);
            sendNotification(message, RabbitMQConfig.EMAIL_NOTIFICATION_ROUTING_KEY);
            log.info("Email Notification Send To RabbitMQ");
            return messageId;
        } catch (Exception e) {
            log.error("Failed To Send Email Notification Message with exception message {} ", e.getMessage(), e);
            throw new RuntimeException("Failed To Send Email Message");
        }
    }

    public String sendSmsNotification(SmsMessageRequest smsMessageRequest) {
        log.info("Sending sms notification");
        String messageId = UUID.randomUUID().toString();
        Message message = Message.builder()
                .id(messageId)
                .type("SMS")
                .recipient(smsMessageRequest.getRecipient())
                .subject("SMS Notification")
                .content(smsMessageRequest.getContent())
                .priority("HIGH")
                .timestamp(LocalDateTime.now())
                .build();
        try {
            log.info("Sending sms-notification message {} with the message id {} to RabbitMQ", message, messageId);
            sendNotification(message, RabbitMQConfig.SMS_NOTIFICATION_ROUTING_KEY);
            log.info("Sms Notification Send To RabbitMQ");
            return messageId;
        } catch (Exception e) {
            log.error("Failed To Send Sms Notification Message with exception message {} ", e.getMessage(), e);
            throw new RuntimeException("Failed To Send Sms Message");
        }
    }

    public void sendNotification(Message message, String routingKey) {
        log.info("Sending message {} with the message id {} to RabbitMQ", message, message.getId());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.APP_EXCHANGE,
                routingKey,
                message
        );
    }


}
