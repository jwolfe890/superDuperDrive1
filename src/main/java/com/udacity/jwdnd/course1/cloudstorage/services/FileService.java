package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    public void deleteFile(Integer fileId) {
        fileMapper.deleteById(fileId);
    }

    public List<Files> getFiles(Integer userId) {
        return fileMapper.getFiles(userId);
    }

    public boolean isFileNameAvailable(MultipartFile multipartFile, Integer userId) {
        Boolean isFileNameAvailable = true;
        List <Files> files = fileMapper.getFiles(userId);
        for (int i = 0; i < files.size(); i++){
            Files currFile = files.get(i);
            if (currFile.getFileName().equals(multipartFile.getOriginalFilename())) {
                isFileNameAvailable = false;
                break;
            }
        }
        return isFileNameAvailable;
    }

}
