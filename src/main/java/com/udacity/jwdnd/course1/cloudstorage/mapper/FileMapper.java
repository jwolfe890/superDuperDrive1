package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userId, filedata) VALUES " +
            "(#{file.fileName}, #{file.contentType}, #{file.fileSize}, #{file.userId}, #{file.fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(@Param("file") Files file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> getFiles(@Param("userId") Integer userId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    void deleteById(@Param("fileid") Integer fileid);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files findById(@Param("fileId") Integer fileId);

}