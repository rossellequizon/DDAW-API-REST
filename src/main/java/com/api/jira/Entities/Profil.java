package com.api.jira.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "profil")
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULLNAME", nullable = false, length = 100)
    private String fullname;

    @Column(name = "METIER", nullable = false, length = 100)
    private String metier;

    @OneToOne
    @JoinColumn(name = "UTILISATEUR_ID", nullable = false, unique = true)
    private Utilisateur utilisateurId;

    public Profil() {
    }

    public Profil(Long id, String fullname, String metier, Utilisateur utilisateurId) {
        this.id = id;
        this.fullname = fullname;
        this.metier = metier;
        this.utilisateurId = utilisateurId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMetier() {
        return metier;
    }
    public void setMetier(String metier) {
        this.metier = metier;
    }

    public Utilisateur getUtilisateur() {
        return utilisateurId;
    }

    public void setUtilisateur(Utilisateur utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}