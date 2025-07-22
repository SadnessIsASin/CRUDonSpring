package com.example.whats_happened.controllers;

import com.example.whats_happened.dto.SmsRequestDto;
import com.example.whats_happened.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SMSController {

    private final SMSService smsService;

    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/sms")
    public void sendSMS(@RequestBody SmsRequestDto smsRequestDto) {
        log.info("Sending SMS");
        smsService.sendSms(smsRequestDto.getTo(), smsRequestDto.getMsg());
    }

}
