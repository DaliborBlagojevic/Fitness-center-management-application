package com.example.SitPass.dto;

import com.example.SitPass.model.Facility;
import com.example.SitPass.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ImageDto {

    private Long id;
    private String path;
    private Facility facility;

    public static ImageDto convertToDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .path(image.getPath())
                .facility(image.getFacility())
                .build();
    }

    public Image convertToModel() {
        return Image.builder()
                .id(getId())
                .path(getPath())
                .facility(getFacility())
                .build();
    }

}

