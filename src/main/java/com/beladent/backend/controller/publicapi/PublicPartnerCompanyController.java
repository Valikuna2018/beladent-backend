package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.PartnerCompanyResponse;
import com.beladent.backend.service.PartnerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/public/partners") @RequiredArgsConstructor
public class PublicPartnerCompanyController {
    private final PartnerCompanyService service;
    @GetMapping public List<PartnerCompanyResponse> all() { return service.getAllActive(); }
}
