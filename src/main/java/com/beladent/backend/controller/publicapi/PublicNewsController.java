package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.NewsResponse;
import com.beladent.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/news")
@RequiredArgsConstructor
public class PublicNewsController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsResponse> getAllActive() {
        return newsService.getAllActiveForPublic();
    }

    @GetMapping("/{id}")
    public NewsResponse getById(@PathVariable Long id) {
        return newsService.getById(id);
    }
}