package com.api.jira.config;

import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.UtilisateurRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("Initialisation de la base de données");
        initDeletedUser();
    }

    private void initDeletedUser() {
        // Vérifier si deleted_user existe déjà
        if (!utilisateurRepo.existsByUsername("deleted_user")) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            Utilisateur deletedUser = new Utilisateur();
            deletedUser.setUsername("deleted_user");
            deletedUser.setEmail("deleted@system.local");
            deletedUser.setPwd(encoder.encode("SystemPassword123!"));
            deletedUser.setActif(false);

            utilisateurRepo.save(deletedUser);
            System.out.println("Utilisateur système 'deleted_user' créé");
        } else {
            System.out.println("Utilisateur 'deleted_user' existe déjà");
        }
    }
}