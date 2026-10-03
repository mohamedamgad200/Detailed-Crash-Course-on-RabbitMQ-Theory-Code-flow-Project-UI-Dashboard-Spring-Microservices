package com.example.rabbitmqapp.controller;

import com.example.rabbitmqapp.entity.Notification;
import com.example.rabbitmqapp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/getNotifications")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/getNotificationsByRecepient")
    public ResponseEntity<List<Notification>> getNotificationsByRecepient(@RequestParam String recepient){
        return ResponseEntity.ok(notificationService.getNotificationsByRecepient(recepient));
    }
}
