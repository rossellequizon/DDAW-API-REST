package com.api.jira.Entities;
import jakarta.persistence.*;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROJET_NAME", nullable = false, unique = true, length = 100)
    private String ProjetName;

    @Column(name = "PROJET_DESCRIPTION", nullable = false, length = 200)
    private String ProjetDescription;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)  // stocke TODO / EN_COURS / TERMINE en base
    @Column(name = "PROJET_STATUS", nullable = false)
    private Status projetStatus = Status.TODO;

    @ManyToOne(optional = false)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Utilisateur ownerId;

    @OneToMany(mappedBy = "projetId", cascade = CascadeType.ALL)
    private List<Tickets> tickets;


    public Projet() {}
    public Projet(Long id, String ProjetName, String ProjetDescription, LocalDateTime creationDate, Status projetStatus) {
        this.id = id;
        this.ProjetName = ProjetName;
        this.ProjetDescription = ProjetDescription;
        this.creationDate = creationDate;
        this.projetStatus = projetStatus;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProjetName() {
        return ProjetName;
    }
    public void setProjetName(String ProjetName) {
        this.ProjetName = ProjetName;
    }
    public String getProjetDescription() {
        return ProjetDescription;
    }
    public void setProjetDescription(String ProjetDescription) {
        this.ProjetDescription = ProjetDescription;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public Status getProjetStatus() {
        return projetStatus;
    }
    public void setProjetStatus(Status projetStatus) {
        this.projetStatus = projetStatus;
    }
    public Utilisateur getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Utilisateur ownerId) {
        this.ownerId = ownerId;
    }
    public List<Tickets> getTickets() {
        return tickets;
    }
    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }
}