package com.api.jira.Service;

import com.api.jira.Entities.*;
import com.api.jira.Repository.ProjetRepo;
import com.api.jira.Repository.UtilisateurRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjetService {
    private final ProjetRepo projetRepo;
    private final UtilisateurRepo utilisateurRepo;

    public ProjetService(ProjetRepo projetRepo, UtilisateurRepo utilisateurRepo) {
        this.projetRepo = projetRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    public Projet createProjet(Projet projet) {
        if(projet.getProjetName() ==  null || projet.getProjetName().isEmpty()){
            throw new IllegalArgumentException("Le nom du projet est requis");
        }
        if(projet.getProjetDescription() ==  null || projet.getProjetDescription().isEmpty()){
            throw new IllegalArgumentException("Le description du projet est requis");
        }
        if(projet.getOwner() ==  null || projet.getOwner().getId() ==  null){
            throw new IllegalArgumentException("Le propriétaire du projet est requis");
        }
        utilisateurRepo.findById(projet.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé avec l'id : " + projet.getOwner().getId()));

        // Vérifier unicité du nom
        if (projetRepo.existsByProjetName(projet.getProjetName())) {
            throw new IllegalArgumentException("Un projet avec ce nom existe déjà");
        }
        if (projet.getOwner() == null || projet.getOwner().getId() == null) {
            throw new IllegalArgumentException("Le propriétaire du projet est requis");
        }

        Long ownerId = projet.getOwner().getId();
        Utilisateur owner = utilisateurRepo.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Utilisateur owner introuvable avec id " + ownerId));

        projet.setOwner(owner);
        return projetRepo.save(projet);
    }


    public List<Projet> getAllProjets() {
        return projetRepo.findAll();
    }

    public  Projet getProjetById(Long id) {
        return projetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec id " + id));
    }

    public List<ProjetDTO> getAllProjetsMinimalDTO() {
        List<Projet> projets = projetRepo.findAll();  // Récupère tous les projets depuis la DB
        List<ProjetDTO> projetMinimalDTOs = new ArrayList<>();

        for (Projet projet : projets) {
            projetMinimalDTOs.add(new ProjetDTO(projet)); // Crée un DTO minimal pour chaque projet
        }
        return projetMinimalDTOs;
    }

    public ProjetDTO getProjetMinimalDTOById(Long id) {
        Projet projet = projetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec id " + id));
        return new ProjetDTO(projet);  // Retourne le DTO minimal du projet trouvé
    }

    public Projet updateProjet(Long id, Projet projetModifie) {
        Projet projetExistant = getProjetById(id);

        Utilisateur owner = utilisateurRepo.findById(projetModifie.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur owner introuvable avec id " + projetModifie.getOwner().getId()));

        projetExistant.setOwner(owner);
        projetExistant.setProjetDescription(projetModifie.getProjetDescription());
        projetExistant.setProjetName(projetModifie.getProjetName());
        projetExistant.setProjetDescription(projetModifie.getProjetDescription());
        projetExistant.setProjetStatus(projetModifie.getProjetStatus());

        return projetRepo.save(projetExistant);
    }

    public void deleteProjet(Long id) {
        if (!projetRepo.existsById(id)) {
            throw new RuntimeException("Projet non trouvé avec l'id : " + id);
        }
        projetRepo.deleteById(id);
    }

    public Status getProjetStatus(Long id) {
        Projet projet = projetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec id " + id));
         return projet.getProjetStatus();
    }

    public Projet updateStatus (Long id, Status status){
        Projet projet = getProjetById(id);
        projet.setProjetStatus(status);
        return projetRepo.save(projet);
    }

    public List<Tickets> getTicketsByProjet(Long id) {
        Projet projet = getProjetById(id);
        return projet.getTickets();
    }
}
