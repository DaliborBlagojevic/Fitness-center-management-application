package com.example.SitPass.controller;

import com.example.SitPass.dto.ImageDto;
import com.example.SitPass.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {

    public final ImageService imageService;
    @PostMapping()
    public ResponseEntity<ImageDto> create(@RequestBody ImageDto imageDto) {
        return ResponseEntity.status(CREATED).body(imageService.create(imageDto));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        imageService.delete(id);
    }

}
