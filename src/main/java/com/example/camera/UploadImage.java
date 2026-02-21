package com.example.camera;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.File;

public class UploadImage {

    public static void sendImage(String filePath) {
        try {
            String token = "8275555727:AAHcaJV4EULcJRy1QP5pHqzOvPa-LjTEkOM";
            String chatId = "-5220917100";
            String url = "https://api.telegram.org/bot" + token + "/sendPhoto";

            File file = new File(filePath);

            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("chat_id", chatId);
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