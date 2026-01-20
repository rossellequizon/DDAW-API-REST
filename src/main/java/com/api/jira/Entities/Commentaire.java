package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "commentaire")
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENU", nullable = false, length = 500)
    private String contenu;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate =  LocalDateTime.now();

    @JsonProperty("auteur")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTEUR_ID", nullable = false)
    private Utilisateur auteur;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Tickets commentaireTicket;

    public Commentaire() {}
    public Commentaire(Long id, String contenu, Utilisateur auteur, Tickets commentaireTicket) {
        this.contenu = contenu;
        this.auteur = auteur;
        this.commentaireTicket = commentaireTicket;
    }
}