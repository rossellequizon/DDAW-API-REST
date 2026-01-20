package com.api.jira.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id", "fullname", "username", "metier"})
public class ProfilDTO {

    private Long id;
    private String fullname;
    private String metier;
    private String username;

    // Constructeur qui prend un Profil en entrée
    public ProfilDTO(Profil profil) {
        this.id = profil.getId();
        this.fullname = profil.getFullname();
        this.metier = profil.getMetier();
        this.username = profil.getUtilisateur().getUsername(); // Assurez-vous que 'utilisateur' n'est pas null
    }

    // Getters et Setters
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
