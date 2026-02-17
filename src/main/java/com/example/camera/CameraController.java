package com.example.camera;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CameraController {

    @GetMapping("/capture")
    public String capture() {
        UploadImage.sendImage();  // Kameradan rasm oladi va Telegramga yuboradi
        return "Sabr:";
    }
}

