package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    CredentialMapper credentialMapper;

    public void addCredential(Integer credentialId, String url, String username, String password, Integer userId) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        Credentials credential = new Credentials(
                credentialId,
                url,
                username,
                encryptedPassword,
                encodedKey,
                userId
        );
        if (credentialId != null) {
            credentialMapper.update(credential);
        } else {
            credentialMapper.insert(credential);
        }
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteById(credentialId);
    }

    public List<Credentials> getCredentials(Integer userId) {
        return credentialMapper.getCredentials(userId);
    }

}
