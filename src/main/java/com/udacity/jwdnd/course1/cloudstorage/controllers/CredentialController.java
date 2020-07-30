package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PostMapping("/createCredential")
    public String addCredential(Credentials credential, Model model) {
        this.credentialService.addCredential(credential);
        model.addAttribute("credentials", this.credentialService.getCredentials());
        return "home";
    }

    @GetMapping(value = "/test-map")
    @ResponseBody
    public String decodePassword(){
        Map<String, String> response = new HashMap<>();
        response.put("decryptedPassword", "testPassword");
        response.put("decryptedPassword2", "testPassword2");
        return response.get("decryptedPassword");
    }


}
