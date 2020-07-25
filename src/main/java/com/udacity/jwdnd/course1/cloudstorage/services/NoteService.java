package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    private List<Note> notes;

    public List<Note> getNotes() {
//        return notes;
        return noteMapper.getNotes();
    }

    public void addNote(Note note) {
        noteMapper.insert(new Note(note.getNoteTitle(), note.getNoteDescription()));
//        Note newNote = new Note();
//        newNote.setNoteTitle(note.getNoteTitle());
//        newNote.setNoteDescription(note.getNoteDescription());
//        this.notes.add(newNote);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        this.notes = new ArrayList<>();
    }

}
