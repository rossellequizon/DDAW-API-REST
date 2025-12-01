package com.api.jira.Entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentaire")
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENU", nullable = false, length = 500)
    private String contenu;

    @Column(name = "CREATION_DATE", nullable = false, insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "AUTEUR_ID", nullable = false)
    private Utilisateur auteur;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Tickets commentaireTicket;

    public Commentaire() {}
    public Commentaire(Long id, String contenu, LocalDateTime creationDate, Utilisateur auteur, Tickets commentaireTicket) {
        this.id = id;
        this.contenu = contenu;
        this.creationDate = creationDate;
        this.auteur = auteur;
        this.commentaireTicket = commentaireTicket;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public Utilisateur getAuteur() {
        return auteur;
    }
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }
    public Tickets getCommentaireTicket() {
        return commentaireTicket;
    }
    public void setCommentaireTicket(Tickets commentaireTicket) {
        this.commentaireTicket = commentaireTicket;
    }

}