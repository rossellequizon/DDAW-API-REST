package com.api.jira.Repository;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepo extends JpaRepository<Projet,Long> {
    boolean existsByProjetName(String projetName);
    List<Projet> findByOwner(Utilisateur owner);

}
