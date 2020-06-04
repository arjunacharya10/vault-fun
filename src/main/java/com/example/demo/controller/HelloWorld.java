package com.example.demo.controller;

import com.bettercloud.vault.VaultException;
import com.example.demo.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.security.validator.ValidatorException;

@RestController
@CrossOrigin("*")
public class HelloWorld {

    @Autowired
    VaultService vaultService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String funWithVault() throws VaultException {
        return vaultService.fetchKey();
    }
}
