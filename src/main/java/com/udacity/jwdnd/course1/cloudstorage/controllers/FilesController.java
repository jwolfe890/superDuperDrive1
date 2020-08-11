package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FilesController {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @PostMapping("/file-upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, Model model) throws IOException {

        String username = auth.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        String fileUploadError = null;

        if (this.fileService.isFileNameAvailable(fileUpload, userId)) {
            try {
                fileMapper.insert(new Files(null,
                        fileUpload.getOriginalFilename(),
                        fileUpload.getContentType(),
                        fileUpload.getSize(),
                        userId,
                        fileUpload.getBytes()
                ));
                model.addAttribute("fileUploadSuccess", "File successfully uploaded.");
                return "home";
            } catch (Exception e) {
                e.printStackTrace();
                fileUploadError = e.toString();
                model.addAttribute("fileError", fileUploadError);
                return "home";
            }
        } else {
            model.addAttribute("fileError", "File name already used");
            model.addAttribute("files", fileMapper.getFiles(userId));
            return "home";
        }
    }

    @GetMapping("/deleteFile/{fileId}")
    public String deleteNote(@PathVariable(value = "fileId") Integer fileId, @ModelAttribute Files file, Authentication authentication, Model model) {
        this.fileService.deleteFile(fileId);

        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        model.addAttribute("files", this.fileService.getFiles(userId));
        return "home";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") Integer fileId) {

        Files file = fileMapper.findById(fileId);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFileName());
        header.add("Cache-control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource((file.getFileData()));
        return ResponseEntity.ok()
                .headers(header)
                .body(resource);
    }


}

