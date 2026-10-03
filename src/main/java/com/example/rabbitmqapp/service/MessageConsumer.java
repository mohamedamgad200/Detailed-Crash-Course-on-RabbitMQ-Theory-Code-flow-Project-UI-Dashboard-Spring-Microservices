package com.example.rabbitmqapp.service;

import com.example.rabbitmqapp.config.RabbitMQConfig;
import com.example.rabbitmqapp.entity.Message;
import com.example.rabbitmqapp.entity.Notification;
import com.example.rabbitmqapp.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void handelEmail(Message message) {
        log.info("Email-notification received: Id={}, Recipient={}, subject={}",
                message.getId(), message.getRecipient(), message.getSubject());
        try {
            Thread.sleep(2000);
            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("SENT")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
            log.info("Email-notification processed and saved with id {}.", message.getId());
        } catch (Exception e) {
            log.error("Failed to process the email message {}", e.getMessage(), e);
            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("FAILED")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.SMS_QUEUE)
    public void handelSms(Message message) {
        log.info("Sms-notification received: Id={}, Recipient={}, content={}",
                message.getId(), message.getRecipient(), message.getContent());
        try {
            Thread.sleep(500);
            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("SENT")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
            log.info("Sms-notification processed and saved with id {}.", message.getId());
        } catch (Exception e) {
            log.error("Failed to process the sms message {}", e.getMessage(), e);
            Notification notification = Notification.builder()
                    .messageId(message.getId())
                    .type(message.getType())
                    .recipient(message.getRecipient())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .status("FAILED")
                    .processedAt(LocalDateTime.now())
                    .build();
            notificationRepository.save(notification);
        }
    }
}
