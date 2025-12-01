package com.api.jira.Repository;

import com.api.jira.Entities.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepo extends JpaRepository<Profil,Long> {
}
