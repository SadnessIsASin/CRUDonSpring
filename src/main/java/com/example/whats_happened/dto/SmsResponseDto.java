package com.example.whats_happened.dto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsResponseDto {
    private String status;
    private int status_code;
    private Map<String, SmsStatusDto> sms;
    private BigDecimal balance;
}
