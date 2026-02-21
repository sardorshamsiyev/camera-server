package com.example.camera;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@RestController
public class CameraController {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody Map<String, String> payload) throws Exception {
        String base64 = payload.get("image").split(",")[1];
        byte[] bytes = Base64.getDecoder().decode(base64);
        Files.write(Paths.get("uploaded.jpg"), bytes);
        UploadImage.sendImage("uploaded.jpg");
        return ResponseEntity.ok("Rasm yuborildi!");
    }
}