package com.example.SitPass.service.impl;

import com.example.SitPass.dto.ImageDto;
import com.example.SitPass.model.Image;
import com.example.SitPass.repository.ImageRepository;
import com.example.SitPass.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.SitPass.dto.ImageDto.convertToDto;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    public final ImageRepository imageRepository;

    @Override
    public Image getModel(Long id) {
        return imageRepository.findById(id).orElseThrow();
    }

    @Override
    public ImageDto create(ImageDto imageDto) {

        return convertToDto(imageRepository.save(imageDto.convertToModel()));
    }

    @Override
    public ImageDto update(Long id, ImageDto imageDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        imageRepository.delete(getModel(id));
    }
}
