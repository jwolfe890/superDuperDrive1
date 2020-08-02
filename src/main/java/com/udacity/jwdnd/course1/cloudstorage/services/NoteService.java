package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteMapper noteMapper;

    private List<Note> notes;

    public List<Note> getNotes() {
        return noteMapper.getNotes();
    }

    public void addNote(Integer noteId, String title, String description) {

        Note newNote = new Note(noteId, title, description);

        if (newNote.getNoteId() != null) {
            noteMapper.update(newNote);
        } else {
//           maybe should try to change this functionality by passing the params to the mapper directly and declaring a param inside the mapper
            noteMapper.insert(newNote);
        }
    }

    public void deleteNote(Integer noteId) {
        noteMapper.delete(noteId);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        this.notes = new ArrayList<>();
    }

}
