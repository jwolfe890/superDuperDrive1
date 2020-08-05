package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @Autowired
    FileService fileService;

    @Autowired
    CredentialService credentialService;

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model) {

        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();

        model.addAttribute("notes", this.noteService.getNotes(userId));
        model.addAttribute("credentials", this.credentialService.getCredentials(userId));
        model.addAttribute("files", this.fileService.getFiles(userId));
        return "home";
    }


}