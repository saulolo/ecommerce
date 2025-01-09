package edu.study.ecommerce.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFile {

    private final String FOLDER = "ïmages//";
    private final String IMG_DEFAULT = "default.jpg";

    /**
     * Upload file to folder by multipartFile
     * @param multipartFile file to upload
     * @return name file
     */
    public String upload(MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {
                byte [] bytes = multipartFile.getBytes();
                Path path = Paths.get(FOLDER + multipartFile.getOriginalFilename());
                Files.write(path, bytes);
                return multipartFile.getOriginalFilename();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return IMG_DEFAULT;
    }

    /**
     * Delete file from folder by name file
     * @param nameFile name file to delete file
     */
    public void delete(String nameFile) {
        File file = new File(FOLDER + nameFile);
        file.delete();

    }
}
