package com.udacity.jwdnd.course1.cloudstorage.model;

//import javax.persistence.Entity;
//
//@Entity
public class Credentials {

    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private Integer userId;
    private String key;

    public Credentials(Integer credentialId, String url, String username, String password, String key, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }


}
