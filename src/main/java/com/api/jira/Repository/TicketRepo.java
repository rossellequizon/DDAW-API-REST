package com.api.jira.Repository;

import com.api.jira.Entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Tickets,Long> {
}
