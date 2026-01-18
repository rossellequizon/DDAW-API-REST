package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    private Boolean actif = true;


    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    @JsonIgnore
    private Profil profil;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Projet> projetPossedes= new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tickets> Creatorickets = new ArrayList<>();

    @OneToMany(mappedBy = "assigne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tickets> assigneTickets = new ArrayList<>();

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Commentaire> commentaire = new ArrayList<>();

    public Utilisateur() {}
    public Utilisateur(String username, String email, String pwd, Boolean actif) {
        this.username = username;
        this.email = email;
        this.pwd = pwd;
        this.actif = actif;
    }
}