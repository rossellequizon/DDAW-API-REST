package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROJET_NAME", nullable = false, unique = true, length = 100)
    private String projetName;

    @Column(name = "PROJET_DESCRIPTION", nullable = false, length = 200)
    private String projetDescription;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)  // stocke TODO / EN_COURS / TERMINE en base
    @Column(name = "PROJET_STATUS", nullable = false)
    private Status projetStatus = Status.TODO;

    @ManyToOne(optional = false)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    @JsonIgnore
    private Utilisateur owner;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Tickets> tickets = new ArrayList<>();


    public Projet() {}
    public Projet(Long id, String projetName, String projetDescription, Utilisateur owner) {
        this.id = id;
        this.projetName = projetName;
        this.projetDescription = projetDescription;
        this.owner = owner;
    }


}