package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CredentialService {

    @Autowired
    CredentialMapper credentialMapper;

    public void addCredential(Credentials credential) {
        if (credential.getCredentialId() != null) {
//            noteMapper.update(new Note(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription()));
        } else {
            credentialMapper.insert(credential);
        }
    }

    public List<Credentials> getCredentials() {
        return credentialMapper.getCredentials();
    }

}
