package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoteController {

    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/home")
    public String postNote(Note note, Model model) {
        this.noteService.addNote(note);
        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }

//    @PostMapping("/home/deleteNote")
//    public String deleteNote(Note note, Model model) {
//        this.noteService.addNote(note);
//        model.addAttribute("notes", this.noteService.getNotes());
//        return "home";
//    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable String noteId) {
//        this.noteService.addNote(note);
//        model.addAttribute("notes", this.noteService.getNotes());

        System.out.println("INSIDE POST DELETE: " + noteId);

        return "home";
    }

}
