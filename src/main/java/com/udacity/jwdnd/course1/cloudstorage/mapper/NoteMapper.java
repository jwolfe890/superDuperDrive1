package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
//    @Select("SELECT * FROM USERS WHERE username = #{username}")
//    Note getNote(String note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    Note deleteNote(int noteId);

    @Select("SELECT * FROM NOTES")
    List<Note> getNotes();

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription) VALUES(#{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true)
    int insert(Note note);
}
