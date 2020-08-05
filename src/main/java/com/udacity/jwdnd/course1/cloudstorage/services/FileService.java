package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
