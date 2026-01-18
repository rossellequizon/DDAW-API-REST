

package com.api.jira.Service;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Tickets;
import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class UtilisateurService {
    private final UtilisateurRepo utilisateurRepo;

    public UtilisateurService(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    // comment rendr le nom d'utilisateur unique
    public Utilisateur create(Utilisateur utilisateur) {
        if(utilisateur.getUsername() == null || utilisateur.getUsername().isEmpty()){
            throw new IllegalArgumentException("Le nom d'utilisateur est requis");
        }
        if(utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()){
            throw new IllegalArgumentException("L'eamil est requis");
        }
        if(utilisateur.getPwd() == null || utilisateur.getPwd().isEmpty()){
            throw new IllegalArgumentException("La password est requis");
        }
        if(!utilisateur.getEmail().contains("@")){
            throw new IllegalArgumentException("Email invalide");
        }
        if (utilisateurRepo.existsByUsername(utilisateur.getUsername())) {
            throw new IllegalArgumentException("Le nom d'utilisateur est existe déjà");
        }
        if (utilisateurRepo.existsByEmail(utilisateur.getEmail())) {
            throw new IllegalArgumentException("L'email est existe déjà");
        }
        return utilisateurRepo.save(utilisateur);
    }


    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepo.findAll();
    }

    public Utilisateur findById(Long id) {
        return utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id " + id));
    }

    public List<Projet> getProjets(Long id) {
        Utilisateur user = findById(id);
        return user.getProjetPossedes();
    }

    public List<Tickets> getCreatorTickets(Long id) {
        Utilisateur user = findById(id);
        return user.getCreatorickets();
    }

    public List<Tickets> getAssigneTickets(Long id) {
        Utilisateur user = findById(id);
        return user.getAssigneTickets();
    }

    public Utilisateur update(Long id, Utilisateur utilisateurModifie) {
        Utilisateur existing = findById(id);

        if (!existing.getUsername().equals(utilisateurModifie.getUsername())
                && utilisateurRepo.existsByUsername(utilisateurModifie.getUsername())) {
            throw new IllegalArgumentException("Username déjà utilisé");
        }

        if (!existing.getEmail().equals(utilisateurModifie.getEmail())
                && utilisateurRepo.existsByEmail(utilisateurModifie.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        existing.setUsername(utilisateurModifie.getUsername());
        existing.setEmail(utilisateurModifie.getEmail());
        existing.setPwd(utilisateurModifie.getPwd());
        existing.setActif(utilisateurModifie.getActif());

        return utilisateurRepo.save(existing);
    }

    public void delete(Long id) {
        if (!utilisateurRepo.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé avec l'id : " + id);
        }
        utilisateurRepo.deleteById(id);
    }

}
