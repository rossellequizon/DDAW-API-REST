package com.api.jira.Repository;

import com.api.jira.Entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepo extends JpaRepository<Commentaire,Long> {
}
