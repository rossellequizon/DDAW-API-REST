package com.api.jira.Repository;

import com.api.jira.Entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepo extends JpaRepository<Projet,Long> {
}
