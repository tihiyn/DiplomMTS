package ru.mts.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }

    public String sendSms(String phoneNumber) {
        Twilio.init(accountSid, authToken);

        String generatedCode = generateVerificationCode();

        if (phoneNumber != null && phoneNumber.startsWith("8") && phoneNumber.length() == 11) {
            phoneNumber = "+7" + phoneNumber.substring(1);
        }

        System.out.println(phoneNumber);

        Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber), "Код подтверждения: " + generatedCode).create();

        return generatedCode;
    }
}
