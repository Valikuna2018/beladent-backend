package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.AboutResponse;
import com.beladent.backend.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/about")
@RequiredArgsConstructor
public class PublicAboutController {

    private final AboutService aboutService;

    @GetMapping
    public AboutResponse getAbout() {
        return aboutService.getAbout();
    }
}