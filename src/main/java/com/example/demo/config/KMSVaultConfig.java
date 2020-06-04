package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KMSVaultConfig {
    @Value("${vault.certificate}")
    private String certificate;

    @Value("${vault.token}")
    private String token;

    public String getToken(){
        return token;
    }

    public String getCertificate(){
        return certificate;
    }
}
