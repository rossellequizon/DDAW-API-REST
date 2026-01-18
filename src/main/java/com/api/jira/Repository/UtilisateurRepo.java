package com.api.jira.Repository;

import com.api.jira.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
