package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/createNote")
    public String postNote(Note note, Model model) {
        this.noteService.addNote(note);
        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable(value = "noteId") Integer noteId, @ModelAttribute Note note, Model model) {
        this.noteService.deleteNote(noteId);
        model.addAttribute("notes", this.noteService.getNotes());
        return "home";
    }

}
