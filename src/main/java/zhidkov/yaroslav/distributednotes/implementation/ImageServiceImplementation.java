package zhidkov.yaroslav.distributednotes.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zhidkov.yaroslav.distributednotes.model.Image;
import zhidkov.yaroslav.distributednotes.repository.ImageRepository;
import zhidkov.yaroslav.distributednotes.service.ImageService;
import zhidkov.yaroslav.distributednotes.util.ImageUtil;

import java.io.IOException;


@Service
public class ImageServiceImplementation implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImplementation(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public void uploadImage(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();
        imageRepository.save(image);
        file.getOriginalFilename();
    }

    @Override
    @Transactional
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteImageById(Long id) {
        imageRepository.deleteImageById(id);
    }

}