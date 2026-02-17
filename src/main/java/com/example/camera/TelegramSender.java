package com.example.camera;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class TelegramSender {

    private static final String TOKEN = "8275555727:AAHcaJV4EULcJRy1QP5pHqzOvPa-LjTEkOM"; // Bu yerga token qo'y
    private static final String CHAT_ID = "-5220917100"; // Bu yerga chat_id qo'y

    public static void sendToTelegram(File file) {
        try {
            String url = "https://api.telegram.org/bot" + TOKEN + "/sendPhoto";

            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("chat_id", CHAT_ID);
            body.add("photo", new FileSystemResource(file));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            System.out.println("Telegram response: " + response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
