

package com.api.jira.Service;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Tickets;
import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.ProjetRepo;
import com.api.jira.Repository.TicketRepo;
import com.api.jira.Repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class UtilisateurService {
    private final UtilisateurRepo utilisateurRepo;
    private final TicketRepo ticketRepo;
    private final ProjetRepo projetRepo;

    public UtilisateurService(UtilisateurRepo utilisateurRepo, TicketRepo ticketRepo, ProjetRepo projetRepo) {
        this.utilisateurRepo = utilisateurRepo;
        this.ticketRepo = ticketRepo;
        this.projetRepo = projetRepo;

    }

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

        if(!utilisateurModifie.getEmail().contains("@")){
            throw new IllegalArgumentException("Email invalide");
        }
        if (utilisateurRepo.existsByUsername(utilisateurModifie.getUsername())) {
            throw new IllegalArgumentException("Le nom d'utilisateur est existe déjà");
        }
        if (utilisateurRepo.existsByEmail(utilisateurModifie.getEmail())) {
            throw new IllegalArgumentException("L'email est existe déjà");
        }
        existing.setUsername(utilisateurModifie.getUsername());
        existing.setEmail(utilisateurModifie.getEmail());
        existing.setPwd(utilisateurModifie.getPwd());
        existing.setActif(utilisateurModifie.getActif());

        return utilisateurRepo.save(existing);
    }

    public void deleteUtilisateur(Long id) {

        Utilisateur user = utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id " + id));

        // utilisateur doit exister en base
        Utilisateur deletedUser = utilisateurRepo.findByUsername("deleted_user")
                .orElseThrow(() -> new RuntimeException(
                        "Utilisateur système 'deleted_user' manquant en base"
                ));

        //assigne = null
        List<Tickets> ticketsAssignes = ticketRepo.findByAssigne(user);
        for (Tickets ticket : ticketsAssignes) {
            ticket.setAssigne(null);
            ticketRepo.save(ticket);
        }

        //creator = deleted_user
        List<Tickets> ticketsCrees = ticketRepo.findByCreator(user);
        for (Tickets ticket : ticketsCrees) {
            ticket.setCreator(deletedUser);
            ticketRepo.save(ticket);
        }
        // owner = deleted_user
        List<Projet> projets = projetRepo.findByOwner(user);
        for (Projet projet : projets) {
            projet.setOwner(deletedUser);
            projetRepo.save(projet);
        }

        // Suppression finale de l'utilisateur
        utilisateurRepo.delete(user);
    }
}
