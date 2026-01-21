package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
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

    @JsonProperty("username")
    @Column(name = "USERNAME",  nullable = false, unique = true, length = 50)
    private String username;

    @JsonProperty("email")
    @Column(name = "EMAIL",  nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "PWD",  nullable = false, length = 100)
    @JsonIgnore
    private String pwd;

    @Column(name = "ACTIF", nullable = false)
    private Boolean actif = true;


    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    @JsonIgnore
    private Profil profil;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Projet> projetPossedes= new ArrayList<>();

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tickets> Creatorickets = new ArrayList<>();

    @OneToMany(mappedBy = "assigne", fetch = FetchType.LAZY)
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