package com.beladent.backend.controller.publicapi;
import com.beladent.backend.dto.SmileGalleryResponse;
import com.beladent.backend.service.SmileGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/public/smile-gallery") @RequiredArgsConstructor
public class PublicSmileGalleryController {private final SmileGalleryService service;@GetMapping public List<SmileGalleryResponse> all(){return service.active();}}
