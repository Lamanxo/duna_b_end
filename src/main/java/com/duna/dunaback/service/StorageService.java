package com.duna.dunaback.service;

import com.duna.dunaback.entities.FileData;
import com.duna.dunaback.repositories.FileDataRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final FileDataRepo dataRepo;

    private final static String FOLDER_PATH = "c:/DunaFiles/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        if (file.isEmpty())
            throw new FileExistsException("File not found");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFilename = UUID.randomUUID() + "." + extension;
        while (dataRepo.findByName(newFilename).isPresent()) {
            newFilename = UUID.randomUUID() + "." + extension;
        }
        String filePath = FOLDER_PATH + newFilename;

        FileData fileData = dataRepo.save(new FileData(
                newFilename,
                file.getContentType(),
                filePath));

        file.transferTo(new File(filePath));

        return "file uploaded successfully : " + filePath;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileData fileData = dataRepo.findByName(fileName).orElseThrow(() -> new EntityNotFoundException("File not found"));
        String filePath=fileData.getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }


}
