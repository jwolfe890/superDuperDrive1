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
        return noteMapper.getNotes();
    }

    public void addNote(Note note) {
        if (note.getNoteId() != null) {
            noteMapper.update(new Note(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription()));
        } else {
//            should try to change this functionality by passing the params to the mapper directly
//                    and declaring a param inside the mapper
            noteMapper.insert(new Note(null, note.getNoteTitle(), note.getNoteDescription()));
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
