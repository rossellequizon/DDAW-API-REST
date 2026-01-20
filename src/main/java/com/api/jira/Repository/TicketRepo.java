package com.api.jira.Repository;

import com.api.jira.Entities.Tickets;
import com.api.jira.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Tickets,Long> {
    List<Tickets> findByAssigne(Utilisateur assigne);
    List<Tickets> findByCreator(Utilisateur creator);
}

