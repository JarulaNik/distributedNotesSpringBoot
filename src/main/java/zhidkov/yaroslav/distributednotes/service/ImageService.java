package zhidkov.yaroslav.distributednotes.service;

import org.springframework.web.multipart.MultipartFile;
import zhidkov.yaroslav.distributednotes.model.Image;

import java.io.IOException;

public interface ImageService {

    void uploadImage(MultipartFile file) throws IOException;

    Image getImageById(Long id);

    void deleteImageById(Long id);

}