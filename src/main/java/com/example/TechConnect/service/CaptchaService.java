package com.example.TechConnect.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.HashMap;
import java.util.Map;

@Service
public class CaptchaService {
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verify(String response) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", response);

        Map result = restTemplate.postForObject(
                GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}",
                null, Map.class, body);

        return (Boolean) result.get("success");
    }
}
