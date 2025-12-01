package com.api.jira.Repository;

import com.api.jira.Entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag,Long> {
}
