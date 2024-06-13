package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.service.SmsService;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    @GetMapping(value = "/send-sms")
    public ResponseEntity<String> sendSMS(@RequestParam("phoneNumber") String phoneNumber) {
        return ResponseEntity.ok(smsService.sendSms(phoneNumber));
    }
}
