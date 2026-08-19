package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.*;
import com.beladent.backend.service.PartnerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController @RequestMapping("/api/admin/partners") @RequiredArgsConstructor
public class AdminPartnerCompanyController {
    private final PartnerCompanyService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PartnerCompanyResponse create(@RequestParam(required=false) String name, @RequestParam(required=false) Integer sortOrder,
            @RequestParam(required=false) Boolean active, @RequestParam("file") MultipartFile file) {
        PartnerCompanyRequest request = request(name, sortOrder, active);
        return service.create(request, file);
    }
    @PutMapping(value="/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PartnerCompanyResponse update(@PathVariable Long id, @RequestParam(required=false) String name,
            @RequestParam(required=false) Integer sortOrder, @RequestParam(required=false) Boolean active,
            @RequestParam(value="file", required=false) MultipartFile file) {
        return service.update(id, request(name, sortOrder, active), file);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
    @GetMapping public List<PartnerCompanyResponse> all() { return service.getAllForAdmin(); }
    @GetMapping("/{id}") public PartnerCompanyResponse one(@PathVariable Long id) { return service.getById(id); }
    private PartnerCompanyRequest request(String name, Integer sortOrder, Boolean active) {
        PartnerCompanyRequest request = new PartnerCompanyRequest();
        request.setName(name); request.setSortOrder(sortOrder); request.setActive(active); return request;
    }
}
