package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private List<Note> notes;



    public List<Note> getNotes() {
        return notes;
    }

    public void addNote() {
        Note newNote1 = new Note();
        newNote1.setNoteTitle("title1");
        this.notes.add(newNote1);

        Note newNote2 = new Note();
        newNote2.setNoteTitle("title2");
        this.notes.add(newNote2);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        this.notes = new ArrayList<>();
    }

}
