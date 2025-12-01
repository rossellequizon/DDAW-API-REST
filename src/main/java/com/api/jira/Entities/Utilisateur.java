package com.api.jira.Entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME",  nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "EMAIL",  nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "PWD",  nullable = false, length = 100)
    private String pwd;

    @Column(name = "ACTIF", nullable = false)
    private boolean actif = true;


    @OneToOne(mappedBy = "utilisateurId", cascade = CascadeType.ALL)
    private Profil profil;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private List<Projet> projetPossedes;

    @OneToMany(mappedBy = "creatorId", cascade = CascadeType.ALL)
    private List<Tickets> Creatorickets;

    @OneToMany(mappedBy = "assigneId", cascade = CascadeType.ALL)
    private List<Tickets> assigneTickets;

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL)
    private List<Commentaire> commentaire;

    public Utilisateur() {}
    public Utilisateur(String username, String email, String pwd, boolean actif) {
        this.username = username;
        this.email = email;
        this.pwd = pwd;
        this.actif = actif;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public boolean isActif() {
        return actif;
    }
    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
    public List<Projet> getProjetPossede() {
        return projetPossedes;
    }
    public void setProjetPossedes(List<Projet> projetPossedes) {
        this.projetPossedes = projetPossedes;
    }
    public List<Tickets> getCreatorTickets() {
        return Creatorickets;
    }
    public void setCreatorTickets(List<Tickets> Creatortickets) {
        Creatortickets = Creatortickets;
    }
    public List<Tickets> getAssigneTicket() {
        return assigneTickets;
    }
    public void setAssigneTickets(List<Tickets> assigneTickets) {
        this.assigneTickets = assigneTickets;
    }
    public List<Commentaire> getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(List<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }
}