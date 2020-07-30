package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class FilesController {

    @Autowired
    FileMapper fileMapper;


    @PostMapping("/file-upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        fileMapper.insert(new Files(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), fileUpload.getBytes()));
        model.addAttribute("files", fileMapper.getFiles());
        return "home";
    }

    @GetMapping("/download/{fileId}")
//    public String downloadFile() {
    public ResponseEntity<Resource> download(@PathVariable("fileId") Integer fileId) {
        List<Files> files = fileMapper.getFiles();
        Files file = files.get(0);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFileName());
        header.add("Cache-control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource((file.getFileData()));
        // TODO: add size and content type for property http response
        return ResponseEntity.ok()
                .headers(header)
                .body(resource);
    }


}

