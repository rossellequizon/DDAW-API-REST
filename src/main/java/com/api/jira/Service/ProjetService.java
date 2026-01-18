package com.api.jira.Service;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Status;
import com.api.jira.Entities.Tickets;
import com.api.jira.Repository.ProjetRepo;
import com.api.jira.Repository.UtilisateurRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        return projetRepo.save(projet);
    }

    public List<Projet> getAllProjets() {
        return projetRepo.findAll();
    }

    public  Projet getProjetById(Long id) {
        return projetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec id " + id));
    }

    public Projet updateProjet(Long id, Projet projetModifie) {
        Projet projetExistant = getProjetById(id);

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

    public Projet updateStatus(Long id, Status status) {
        Projet projet = getProjetById(id);
        projet.setProjetStatus(status);
        return projetRepo.save(projet);
    }

    public List<Tickets> getTicketsByProjet(Long id) {
        Projet projet = getProjetById(id);
        return projet.getTickets();
    }
}
