package com.example.whats_happened.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsStatusDto {
    private String status;
    private int status_code;
    private String sms_id;
    private String status_text;
}
