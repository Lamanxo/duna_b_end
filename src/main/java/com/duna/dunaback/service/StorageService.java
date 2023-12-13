package com.duna.dunaback.service;

import com.duna.dunaback.entities.FileData;
import com.duna.dunaback.repositories.FileDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final FileDataRepo dataRepo;

    private final String FOLDER_PATH = "c:/DunaFiles/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        FileData fileData=dataRepo.save(new FileData(
                file.getOriginalFilename(),
                file.getContentType(),
                filePath));

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileData fileData = dataRepo.findByName(fileName).orElseThrow(() -> new EntityNotFoundException("File not found"));
        String filePath=fileData.getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


}
