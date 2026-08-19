package com.beladent.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.max-size}")
    private long maxFileSize;

    @Value("${app.upload.allowed-types}")
    private String allowedTypes;

    public String saveFile(MultipartFile file, String folder) {
        try {
            validateFile(file);

            String originalFilename = file.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            }

            String fileName = UUID.randomUUID() + extension;

            Path folderPath = Paths.get(uploadDir, folder);
            Files.createDirectories(folderPath);

            Path filePath = folderPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + folder + "/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Could not save file: " + e.getMessage());
        }
    }

    public void deleteFile(String imageUrl) {
        try {
            if (imageUrl == null || imageUrl.isBlank()) {
                return;
            }

            String relativePath = imageUrl.replace("/uploads/", "");
            Path filePath = Paths.get(uploadDir, relativePath);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }

        } catch (Exception e) {
            System.err.println("Could not delete file: " + e.getMessage());
        }
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (file.getSize() > maxFileSize) {
            throw new RuntimeException("File size exceeds the maximum allowed limit");
        }

        String contentType = file.getContentType();

        if (contentType == null) {
            throw new RuntimeException("File type is unknown");
        }

        List<String> allowedTypeList = Arrays.stream(allowedTypes.split(","))
                .map(String::trim)
                .toList();

        if (!allowedTypeList.contains(contentType)) {
            throw new RuntimeException("File type is not allowed. Allowed types: " + allowedTypes);
        }
    }
}