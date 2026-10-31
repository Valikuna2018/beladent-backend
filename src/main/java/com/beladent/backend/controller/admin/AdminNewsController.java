package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.NewsRequest;
import com.beladent.backend.dto.NewsResponse;
import com.beladent.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsService newsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public NewsResponse create(
            @RequestParam String titleKa,
            @RequestParam String titleEn,
            @RequestParam(required = false) String textKa,
            @RequestParam(required = false) String textEn,
            @RequestParam(required = false) String imageAltKa,
            @RequestParam(required = false) String imageAltEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        NewsRequest request = new NewsRequest();
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setTextKa(textKa);
        request.setTextEn(textEn);
        request.setImageAltKa(imageAltKa);
        request.setImageAltEn(imageAltEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return newsService.create(request, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public NewsResponse update(
            @PathVariable Long id,
            @RequestParam String titleKa,
            @RequestParam String titleEn,
            @RequestParam(required = false) String textKa,
            @RequestParam(required = false) String textEn,
            @RequestParam(required = false) String imageAltKa,
            @RequestParam(required = false) String imageAltEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        NewsRequest request = new NewsRequest();
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setTextKa(textKa);
        request.setTextEn(textEn);
        request.setImageAltKa(imageAltKa);
        request.setImageAltEn(imageAltEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return newsService.update(id, request, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }

    @GetMapping
    public List<NewsResponse> getAll() {
        return newsService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public NewsResponse getById(@PathVariable Long id) {
        return newsService.getById(id);
    }
}
