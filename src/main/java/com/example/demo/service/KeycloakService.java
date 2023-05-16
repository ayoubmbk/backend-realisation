package com.example.demo.service;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.info.ServerInfoRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakService {

    private final String keycloakBaseUrl = "http://localhost:8181/auth";
    private final String realmName = "ppl";
    private final String clientId = "ppl";


    public void getAllUsers() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakBaseUrl)
                .username("admin")
                .password("Password")
                .realm(realmName)
                .clientId(clientId)
                .clientSecret("88a4e1fb-f79b-4563-95ae-c6729913e9d9")
                .build();
        ServerInfoRepresentation serverInfo = keycloak.serverInfo().getInfo();

        // Print Keycloak server version
        System.out.println("Keycloak Server Version: " + serverInfo.getSystemInfo().getVersion());



    //    RealmResource realmResource = keycloak.realm(realmName);
      //  UsersResource usersResource = realmResource.users();

  //      return usersResource.list();
    }
}