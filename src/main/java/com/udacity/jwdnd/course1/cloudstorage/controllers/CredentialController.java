package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.security.core.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CredentialController {

    @Autowired
    CredentialMapper credentialMapper;

    @Autowired
    UserService userService;

    @Autowired
    CredentialService credentialService;

    @Autowired
    EncryptionService encryptionService;

    @PostMapping("/create-credential")
    public String addCredential(
    @RequestParam("credentialId") Integer credentialId,
            @RequestParam("url") String credentialUrl,
            @RequestParam("username") String credentialUsername,
            @RequestParam("password") String credentialPassword,
            Model model,
            Authentication authentication
    ) throws IOException {

        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        this.credentialService.addCredential(credentialId, credentialUrl, credentialUsername, credentialPassword, userId);
        model.addAttribute("credentials", this.credentialService.getCredentials(userId));
        return "home";
    }

    @GetMapping("/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable(value = "credentialId") Integer credentialId,
                                   Model model, Authentication authentication) {

        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        credentialService.deleteCredential(credentialId);
        model.addAttribute("credentials", this.credentialService.getCredentials(userId));
        return "home";
    }

    @GetMapping("/decodeCredentialPassword/{credentialId}")
    @ResponseBody
    public Map decodeCredentialPassword(@PathVariable(value = "credentialId") Integer credentialId) {
        Credentials credential = credentialMapper.findByCredentialId(credentialId);
        String encryptedPassword = credential.getPassword();
        String encodedKey = credential.getKey();

        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
        Map<String, String> response = new HashMap<>();
        response.put("decryptedPassword", decryptedPassword);
        return response;
    }


}
