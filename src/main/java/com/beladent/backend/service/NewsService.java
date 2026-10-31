package com.beladent.backend.service;

import com.beladent.backend.dto.NewsRequest;
import com.beladent.backend.dto.NewsResponse;
import com.beladent.backend.model.News;
import com.beladent.backend.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final FileStorageService fileStorageService;

    public NewsResponse create(NewsRequest request, MultipartFile file) {

        String imageUrl = null;

        if (file != null && !file.isEmpty()) {
            imageUrl = fileStorageService.saveFile(file, "news");
        }

        News news = News.builder()
                .titleKa(request.getTitleKa())
                .titleEn(request.getTitleEn())
                .textKa(request.getTextKa())
                .textEn(request.getTextEn())
                .imageUrl(imageUrl)
                .imageAltKa(request.getImageAltKa())
                .imageAltEn(request.getImageAltEn())
                .sortOrder(request.getSortOrder())
                .active(request.getActive())
                .build();

        return mapToResponse(newsRepository.save(news));
    }

    public NewsResponse update(Long id, NewsRequest request, MultipartFile file) {

        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));

        String oldImageUrl = news.getImageUrl();

        news.setTitleKa(request.getTitleKa());
        news.setTitleEn(request.getTitleEn());
        news.setTextKa(request.getTextKa());
        news.setTextEn(request.getTextEn());
        news.setImageAltKa(request.getImageAltKa());
        news.setImageAltEn(request.getImageAltEn());
        news.setSortOrder(request.getSortOrder());
        news.setActive(request.getActive());

        if (file != null && !file.isEmpty()) {
            String newImageUrl = fileStorageService.saveFile(file, "news");
            news.setImageUrl(newImageUrl);

            if (oldImageUrl != null && !oldImageUrl.isBlank()) {
                fileStorageService.deleteFile(oldImageUrl);
            }
        }

        return mapToResponse(newsRepository.save(news));
    }

    public void delete(Long id) {

        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));

        if (news.getImageUrl() != null && !news.getImageUrl().isBlank()) {
            fileStorageService.deleteFile(news.getImageUrl());
        }

        newsRepository.delete(news);
    }

    public NewsResponse getById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));

        return mapToResponse(news);
    }

    public List<NewsResponse> getAllForAdmin() {
        return newsRepository.findAllByOrderBySortOrderAscCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<NewsResponse> getAllActiveForPublic() {
        return newsRepository.findByActiveTrueOrderBySortOrderAscCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private NewsResponse mapToResponse(News news) {
        return NewsResponse.builder()
                .id(news.getId())
                .titleKa(news.getTitleKa())
                .titleEn(news.getTitleEn())
                .textKa(news.getTextKa())
                .textEn(news.getTextEn())
                .imageUrl(news.getImageUrl())
                .imageAltKa(news.getImageAltKa())
                .imageAltEn(news.getImageAltEn())
                .sortOrder(news.getSortOrder())
                .active(news.getActive())
                .createdAt(news.getCreatedAt())
                .updatedAt(news.getUpdatedAt())
                .build();
    }
}
