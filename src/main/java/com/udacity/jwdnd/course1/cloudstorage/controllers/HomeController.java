package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    NoteService noteService;

    @Autowired
    FileMapper fileMapper;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }


}