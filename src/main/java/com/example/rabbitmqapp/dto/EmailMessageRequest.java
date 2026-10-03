package com.example.rabbitmqapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailMessageRequest {
    private String recipient;
    private String subject;
    private String content;
}
