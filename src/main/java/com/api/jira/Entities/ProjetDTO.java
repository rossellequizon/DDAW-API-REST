package com.api.jira.Entities;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id", "projetName", "ownerUsername", "projetStatus"
})
public class ProjetDTO {
    private Long id;
    private String projetName;
    private String ownerUsername;
    private String projetStatus;

    public ProjetDTO(Projet projet) {
        this.id = projet.getId();
        this.projetName = projet.getProjetName();
        this.ownerUsername = projet.getOwner().getUsername();
        this.projetStatus = projet.getProjetStatus().toString();
    }

    // Getters et Setters (autogénérés si nécessaire)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getProjetName() {
        return projetName;
    }
    public void setProjetName(String projetName) {
        this.projetName = projetName;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }
    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getProjetStatus() {
        return projetStatus;
    }
    public void setProjetStatus(String projetStatus) {
        this.projetStatus = projetStatus;
    }

}
