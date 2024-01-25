package com.duna.dunaback.service;

import com.duna.dunaback.entities.FileData;
import com.duna.dunaback.repositories.FileDataRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final FileDataRepo dataRepo;

    @Value("${app-settings.folder-name}")
    private String FOLDER_PATH;
    //test------------
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        FileData fileData = uploadFileToFS(file, null);

        return "file uploaded successfully : " + FOLDER_PATH + fileData.getName();
    }
    //test---------------

    public FileData uploadFileToFS(MultipartFile file, Long orderId) throws IOException {
        if (file.isEmpty())
            throw new FileNotFoundException("File not found");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFilename = UUID.randomUUID() + "." + extension;
        while (dataRepo.findByName(newFilename).isPresent()) {
            newFilename = UUID.randomUUID() + "." + extension;
        }
        String filePath = FOLDER_PATH + newFilename;

        FileData fileData = dataRepo.save(new FileData(
                orderId,
                newFilename,
                file.getContentType(),
                filePath));

        file.transferTo(new File(filePath));
        return fileData;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileData fileData = findByNameOrException(fileName);
        String filePath=fileData.getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    public FileData findByNameOrException(String fileName) {
        return dataRepo.findByName(fileName).orElseThrow(() -> new EntityNotFoundException("File not found"));
    }

    public List<String> findAllImageNamesByOrderId(Long orderId) {
        List<String> orderNames = dataRepo.findAllByOrderId(orderId).stream().map(fileData -> fileData.getName()).collect(Collectors.toList());
        return orderNames;
    }


}
