package com.example.whats_happened.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequestDto {
    private String api_id;
    private String to;
    private String msg;
    private int json;
}
