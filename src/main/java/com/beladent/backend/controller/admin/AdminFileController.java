package com.beladent.backend.controller.admin;

import com.beladent.backend.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/files")
@RequiredArgsConstructor
public class AdminFileController {

    private final FileStorageService fileStorageService;

    @PostMapping(
            value = "/upload/{folder}",
            consumes = "multipart/form-data"
    )
    public Map<String, String> uploadFile(
            @PathVariable String folder,
            @RequestParam("file") MultipartFile file
    ) {

        String imageUrl = fileStorageService.saveFile(file, folder);

        return Map.of(
                "imageUrl",
                imageUrl
        );
    }
}