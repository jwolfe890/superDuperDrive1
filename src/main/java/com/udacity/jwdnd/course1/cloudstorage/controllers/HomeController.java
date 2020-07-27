package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Controller
public class HomeController {

    NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
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
        InputStream fis = fileUpload.getInputStream();
        return "home";
    }



}