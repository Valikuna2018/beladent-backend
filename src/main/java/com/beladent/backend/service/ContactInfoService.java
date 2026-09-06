package com.beladent.backend.service;

import com.beladent.backend.dto.ContactInfoRequest;
import com.beladent.backend.dto.ContactInfoResponse;
import com.beladent.backend.model.ContactInfo;
import com.beladent.backend.repository.ContactInfoRepository;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    public ContactInfoResponse getContactInfo() {
        ContactInfo contactInfo = getOrCreateContactInfo();
        return mapToResponse(contactInfo);
    }

    public ContactInfoResponse update(ContactInfoRequest request) {
        ContactInfo contactInfo = getOrCreateContactInfo();

        contactInfo.setPhone1(request.getPhone1());
        contactInfo.setPhone2(request.getPhone2());
        contactInfo.setEmail(request.getEmail());
        contactInfo.setAddressKa(request.getAddressKa());
        contactInfo.setAddressEn(request.getAddressEn());
        contactInfo.setFooterDescriptionKa(request.getFooterDescriptionKa());
        contactInfo.setFooterDescriptionEn(request.getFooterDescriptionEn());
        contactInfo.setFacebookUrl(request.getFacebookUrl());
        contactInfo.setInstagramUrl(request.getInstagramUrl());
        contactInfo.setLinkedinUrl(request.getLinkedinUrl());
        contactInfo.setYoutubeUrl(request.getYoutubeUrl());
        contactInfo.setGoogleMapsUrl(request.getGoogleMapsUrl());
        contactInfo.setMondaySaturdayOpen(request.getMondaySaturdayOpen());
        contactInfo.setMondaySaturdayClose(request.getMondaySaturdayClose());
        contactInfo.setSundayOpen(request.getSundayOpen());
        contactInfo.setSundayClose(request.getSundayClose());
        contactInfo.setSundayClosed(request.isSundayClosed());

        return mapToResponse(contactInfoRepository.save(contactInfo));
    }

    private ContactInfo getOrCreateContactInfo() {
        return contactInfoRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> contactInfoRepository.save(ContactInfo.builder().build()));
    }

    private ContactInfoResponse mapToResponse(ContactInfo contactInfo) {
        return ContactInfoResponse.builder()
                .id(contactInfo.getId())
                .phone1(contactInfo.getPhone1())
                .phone2(contactInfo.getPhone2())
                .email(contactInfo.getEmail())
                .addressKa(contactInfo.getAddressKa())
                .addressEn(contactInfo.getAddressEn())
                .footerDescriptionKa(contactInfo.getFooterDescriptionKa())
                .footerDescriptionEn(contactInfo.getFooterDescriptionEn())
                .facebookUrl(contactInfo.getFacebookUrl())
                .instagramUrl(contactInfo.getInstagramUrl())
                .linkedinUrl(contactInfo.getLinkedinUrl())
                .youtubeUrl(contactInfo.getYoutubeUrl())
                .googleMapsUrl(contactInfo.getGoogleMapsUrl())
                .mondaySaturdayOpen(contactInfo.getMondaySaturdayOpen())
                .mondaySaturdayClose(contactInfo.getMondaySaturdayClose())
                .sundayOpen(contactInfo.getSundayOpen())
                .sundayClose(contactInfo.getSundayClose())
                .sundayClosed(contactInfo.getSundayClosed() == null || contactInfo.getSundayClosed())
                .build();
    }
}
