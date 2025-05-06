package zhidkov.yaroslav.distributednotes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import zhidkov.yaroslav.distributednotes.model.Image;
import zhidkov.yaroslav.distributednotes.service.ImageService;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/image")
public class ImageController {


    private final ImageService imageService;


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable("id") Long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok().body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<Void> deleteImageById(@PathVariable Long id) {
        if (imageService.getImageById(id) != null) {
            imageService.deleteImageById(id);
            return  ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}