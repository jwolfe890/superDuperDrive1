package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void delete(Integer noteId);

    @Update("UPDATE NOTES SET noteTitle = #{note.noteTitle}, noteDescription = #{note.noteDescription} WHERE noteId = #{note.noteId}")
    Integer update(@Param("note") Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getNotes(@Param("userId") Integer userId);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES (#{note.noteTitle}, #{note.noteDescription}, #{note.userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(@Param("note") Note note);

}
