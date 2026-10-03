package com.example.rabbitmqapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsMessageRequest {
    private String recipient;
    private String content;
}
