package com.example.demo.service;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.example.demo.config.KMSVaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class VaultService {

    private Vault vault;

    @Autowired
    KMSVaultConfig kmsVaultConfig;

    @PostConstruct
    public void init() throws VaultException{
        VaultConfig vaultConfig;
        try{
            vaultConfig = new VaultConfig().address("127.0.0.1:8200").engineVersion(1).openTimeout(5).readTimeout(30).sslConfig(null).build();
            vault = new Vault(vaultConfig.token("lmao").build());
        }catch(VaultException e){
            throw new VaultException("failed to initialize");
        }
    }

    public String fetchKey() throws VaultException{
     String key = vault.logical().read("/secret/users")
                    .getData()
                    .get("data");
     return key;
    }
}
