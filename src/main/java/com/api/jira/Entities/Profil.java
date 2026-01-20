package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profil")
public class Profil {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULLNAME", nullable = false, length = 100)
    private String fullname;

    @Column(name = "METIER", nullable = false, length = 100)
    private String metier;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID")
    private Utilisateur utilisateur;

    public Profil() {
    }

    public Profil(String fullname, String metier, Utilisateur utilisateur) {
        this.fullname = fullname;
        this.metier = metier;
        this.utilisateur = utilisateur;
    }
}