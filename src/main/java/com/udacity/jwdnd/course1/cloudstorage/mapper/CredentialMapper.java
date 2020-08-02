package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password) VALUES " +
            "(#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(@Param("credential") Credentials credential);

//    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
//    List<Credentials> findCredentialsByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credentials> getCredentials();

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId=#{credentialId}")
    void deleteById(@Param("credentialId") Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url=#{credential.url}, username=#{credential.username}, key=#{credential.key},password=#{credential.password} " +
            "WHERE credentialId = #{credential.credentialId}")
    void update(@Param("credential") Credentials credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId=#{credentialId}")
    Credentials findByCredentialId(@Param("credentialId") Integer credentialId);


}
