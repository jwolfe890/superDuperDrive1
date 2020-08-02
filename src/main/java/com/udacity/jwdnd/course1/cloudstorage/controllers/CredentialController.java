package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PostMapping("/create-credential")
    public String addCredential(
    @RequestParam("credentialId") Integer credentialId,
            @RequestParam("url") String credentialUrl,
            @RequestParam("username") String credentialUsername,
            @RequestParam("password") String credentialPassword,
            Model model) throws IOException {


        this.credentialService.addCredential(credentialUrl, credentialUsername, credentialPassword);
//        model.addAttribute("credentials", this.credentialService.getCredentials());
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
