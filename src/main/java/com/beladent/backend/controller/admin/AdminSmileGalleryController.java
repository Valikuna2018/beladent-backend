package com.beladent.backend.controller.admin;
import com.beladent.backend.dto.*;
import com.beladent.backend.service.SmileGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@RestController @RequestMapping("/api/admin/smile-gallery") @RequiredArgsConstructor
public class AdminSmileGalleryController {
 private final SmileGalleryService service;
 @PostMapping(consumes=MediaType.MULTIPART_FORM_DATA_VALUE) public SmileGalleryResponse create(@RequestParam String titleKa,@RequestParam String titleEn,@RequestParam(required=false)String textKa,@RequestParam(required=false)String textEn,@RequestParam(required=false)String imageAltKa,@RequestParam(required=false)String imageAltEn,@RequestParam(required=false)Integer sortOrder,@RequestParam(required=false)Boolean active,@RequestParam("file")MultipartFile file){return service.create(req(titleKa,titleEn,textKa,textEn,imageAltKa,imageAltEn,sortOrder,active),file);}
 @PutMapping(value="/{id}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE) public SmileGalleryResponse update(@PathVariable Long id,@RequestParam String titleKa,@RequestParam String titleEn,@RequestParam(required=false)String textKa,@RequestParam(required=false)String textEn,@RequestParam(required=false)String imageAltKa,@RequestParam(required=false)String imageAltEn,@RequestParam(required=false)Integer sortOrder,@RequestParam(required=false)Boolean active,@RequestParam(value="file",required=false)MultipartFile file){return service.update(id,req(titleKa,titleEn,textKa,textEn,imageAltKa,imageAltEn,sortOrder,active),file);}
 @DeleteMapping("/{id}")public void delete(@PathVariable Long id){service.delete(id);}@GetMapping public List<SmileGalleryResponse> all(){return service.admin();}
 private SmileGalleryRequest req(String a,String b,String c,String d,String altKa,String altEn,Integer o,Boolean x){SmileGalleryRequest r=new SmileGalleryRequest();r.setTitleKa(a);r.setTitleEn(b);r.setTextKa(c);r.setTextEn(d);r.setImageAltKa(altKa);r.setImageAltEn(altEn);r.setSortOrder(o);r.setActive(x);return r;}
}
