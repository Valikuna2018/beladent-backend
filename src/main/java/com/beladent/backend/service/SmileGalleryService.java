package com.beladent.backend.service;
import com.beladent.backend.dto.*;
import com.beladent.backend.model.SmileGalleryItem;
import com.beladent.backend.repository.SmileGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service @RequiredArgsConstructor
public class SmileGalleryService {
    private final SmileGalleryRepository repository; private final FileStorageService fileStorageService;
    public SmileGalleryResponse create(SmileGalleryRequest r, MultipartFile file){
        if(file==null||file.isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Image is required");
        SmileGalleryItem i=SmileGalleryItem.builder().titleKa(r.getTitleKa()).titleEn(r.getTitleEn()).textKa(r.getTextKa()).textEn(r.getTextEn())
                .imageUrl(fileStorageService.saveFile(file,"smile-gallery")).sortOrder(r.getSortOrder()).active(r.getActive()).build();return map(repository.save(i));}
    public SmileGalleryResponse update(Long id,SmileGalleryRequest r,MultipartFile file){
        SmileGalleryItem i=repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Smile gallery item not found"));
        i.setTitleKa(r.getTitleKa());i.setTitleEn(r.getTitleEn());i.setTextKa(r.getTextKa());i.setTextEn(r.getTextEn());i.setSortOrder(r.getSortOrder());i.setActive(r.getActive());
        if(file!=null&&!file.isEmpty()){String old=i.getImageUrl();i.setImageUrl(fileStorageService.saveFile(file,"smile-gallery"));if(old!=null&&!old.isBlank())fileStorageService.deleteFile(old);}return map(repository.save(i));}
    public void delete(Long id){SmileGalleryItem i=repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Smile gallery item not found"));if(i.getImageUrl()!=null)fileStorageService.deleteFile(i.getImageUrl());repository.delete(i);}
    public List<SmileGalleryResponse> admin(){return repository.findAllByOrderBySortOrderAsc().stream().map(this::map).toList();}
    public List<SmileGalleryResponse> active(){return repository.findByActiveTrueOrderBySortOrderAsc().stream().map(this::map).toList();}
    private SmileGalleryResponse map(SmileGalleryItem i){return SmileGalleryResponse.builder().id(i.getId()).titleKa(i.getTitleKa()).titleEn(i.getTitleEn()).textKa(i.getTextKa()).textEn(i.getTextEn()).imageUrl(i.getImageUrl()).sortOrder(i.getSortOrder()).active(i.getActive()).createdAt(i.getCreatedAt()).updatedAt(i.getUpdatedAt()).build();}
}
