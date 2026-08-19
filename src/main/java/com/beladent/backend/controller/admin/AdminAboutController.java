package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.AboutRequest;
import com.beladent.backend.dto.AboutResponse;
import com.beladent.backend.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/about")
@RequiredArgsConstructor
public class AdminAboutController {

    private final AboutService aboutService;

    @GetMapping
    public AboutResponse getAbout() {
        return aboutService.getAbout();
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AboutResponse update(
            @RequestParam String titleKa,
            @RequestParam String titleEn,
            @RequestParam(required = false) String descriptionKa,
            @RequestParam(required = false) String descriptionEn,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        AboutRequest request = new AboutRequest();
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setDescriptionKa(descriptionKa);
        request.setDescriptionEn(descriptionEn);
        return aboutService.update(request, file);
    }
}
