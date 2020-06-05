package com.example.demo.service;

import com.bettercloud.vault.SslConfig;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.example.demo.config.KMSVaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class VaultService {

    private Vault vault;

    @Autowired
    KMSVaultConfig kmsVaultConfig;

    @Value("${vault.token}")
    private String token;

    @PostConstruct
    public void init() throws VaultException{
        VaultConfig vaultConfig;
        try{
            vaultConfig = new VaultConfig()
                    .address("https://127.0.0.1:8200")
                    .token(token)
                    .sslConfig(new SslConfig().verify(false))
                    .engineVersion(1)
                    .build();
            vault = new Vault(vaultConfig);
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

    public String sign(String content, String key) throws VaultException {
        String signature = vault.logical()
                .write("ethereum/accounts/" + key + "/sign", new HashMap() {{ put("data", content);put("encoding","utf-8"); }})
                .getData()
                .get("signature");
        return signature;
    }
}
