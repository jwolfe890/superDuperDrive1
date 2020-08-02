package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    @Autowired
    HashService hashService;

    @Autowired
    CredentialMapper credentialMapper;

    public void addCredential(String url, String username, String password) {

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = hashService.getHashedValue(password, encodedKey);

        Credentials credential = new Credentials(
                null,
                url,
                username,
                encryptedPassword,
                encodedKey
        );

        try {
            credentialMapper.insert(credential);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public List<Credentials> getCredentials() {
        return credentialMapper.getCredentials();
    }

}
