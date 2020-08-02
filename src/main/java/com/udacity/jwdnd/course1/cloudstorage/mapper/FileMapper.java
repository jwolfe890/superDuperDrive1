package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata) VALUES " +
            "(#{file.fileName}, #{file.contentType}, #{file.fileSize}, #{file.fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(@Param("file") Files file);

    @Select("SELECT * FROM FILES")
    List<Files> getFiles();

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    void deleteById(@Param("fileid") Integer fileid);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files findById(@Param("fileId") Integer fileId);

}