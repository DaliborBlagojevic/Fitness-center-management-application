package com.example.SitPass.service;

import com.example.SitPass.dto.ImageDto;
import com.example.SitPass.model.Image;

public interface ImageService {

    Image getModel(Long id);
    ImageDto create(ImageDto imageDto);
    ImageDto update(Long id, ImageDto imageDto);
    void delete(Long id);
}
