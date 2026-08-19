package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.ContactInfoRequest;
import com.beladent.backend.dto.ContactInfoResponse;
import com.beladent.backend.service.ContactInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/contact-info")
@RequiredArgsConstructor
public class AdminContactInfoController {

    private final ContactInfoService contactInfoService;

    @GetMapping
    public ContactInfoResponse getContactInfo() {
        return contactInfoService.getContactInfo();
    }

    @PutMapping
    public ContactInfoResponse update(@Valid @RequestBody ContactInfoRequest request) {
        return contactInfoService.update(request);
    }
}