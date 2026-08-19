package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.ContactInfoResponse;
import com.beladent.backend.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/contact-info")
@RequiredArgsConstructor
public class PublicContactInfoController {

    private final ContactInfoService contactInfoService;

    @GetMapping
    public ContactInfoResponse getContactInfo() {
        return contactInfoService.getContactInfo();
    }
}