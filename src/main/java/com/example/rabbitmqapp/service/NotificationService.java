package com.example.rabbitmqapp.service;

import com.example.rabbitmqapp.entity.Notification;
import com.example.rabbitmqapp.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByRecepient(String recepient) {
        return notificationRepository.findNotificationByRecipientOrderByCreatedAtDesc(recepient);
    }
}
