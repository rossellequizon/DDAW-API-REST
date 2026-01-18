package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Tickets> tickets = new HashSet<>();

    public Tag() {
    }
    public Tag(String tagName) {
        this.tagName = tagName;
    }
}