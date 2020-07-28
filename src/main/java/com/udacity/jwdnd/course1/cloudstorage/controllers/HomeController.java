package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    NoteService noteService;

    FileMapper fileMapper;

    public HomeController(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

//    @GetMapping("/home")
//    public String getHomePage(@ModelAttribute Note note, Model model) {
//        model.addAttribute("notes", this.noteService.getNotes());
//        return "home";
//    }

    @GetMapping("/home")
    public String getHomePage() {
//        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }

//    @PostMapping("/file-upload")
//    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
////        InputStream fis = fileUpload.getInputStream();
//
////        System.out.println(fileUpload.getOriginalFilename());
//        return "home";
//    }

//    byte[] bytes = file.getBytes();
//    String completeData = new String(bytes);
//    String[] rows = completeData.split("#");
//    String[] columns = rows[0].split(",");

    @PostMapping("/file-upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        fileMapper.insert(new Files(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), fileUpload.getBytes()));
        model.addAttribute("files", fileMapper.getFiles());
        return "home";
    }

    @GetMapping("/download")
    public String downloadFile() {
        List<Files> files = fileMapper.getFiles();
        Files newFile = files.get(0);
        byte[] downloadedFile = newFile.getFileData();
        return "home";
    }
}