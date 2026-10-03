package com.example.rabbitmqapp.controller;

import com.example.rabbitmqapp.dto.EmailMessageRequest;
import com.example.rabbitmqapp.dto.SmsMessageRequest;
import com.example.rabbitmqapp.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageProducer messageProducer;

    @PostMapping("/email-notification")
    public ResponseEntity<Map<String, String>> sendEmailNotification(@RequestBody EmailMessageRequest emailMessageRequest) {
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "messageId", messageProducer.sendEmailNotification(emailMessageRequest),
                "message", "Email-Notification sent to RabbitMQ successfully"
        ));
    }

    @PostMapping("/sms-notification")
    public ResponseEntity<Map<String, String>> sendSmsNotification(@RequestBody SmsMessageRequest smsMessageRequest) {
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "messageId", messageProducer.sendSmsNotification(smsMessageRequest),
                "message", "Sms-Notification sent to RabbitMQ successfully"
        ));
    }
}
