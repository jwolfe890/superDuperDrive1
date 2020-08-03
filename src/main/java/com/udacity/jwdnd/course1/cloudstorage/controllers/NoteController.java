package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @PostMapping("/createNote")
    public String postNote(@RequestParam("noteId") Integer noteId, @RequestParam("title") String title,
                           @RequestParam("description") String description, Authentication authentication, Model model) {

        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        this.noteService.addNote(noteId, title, description, userId);
        model.addAttribute("notes", this.noteService.getNotes(userId));
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable(value = "noteId") Integer noteId, @ModelAttribute Note note, Authentication authentication, Model model) {
        this.noteService.deleteNote(noteId);

        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        model.addAttribute("notes", this.noteService.getNotes(userId));
        return "home";
    }

}
