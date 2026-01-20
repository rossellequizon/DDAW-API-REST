package com.api.jira.Service;

import com.api.jira.Entities.Commentaire;
import com.api.jira.Entities.Tickets;
import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.CommentaireRepo;
import com.api.jira.Repository.TicketRepo;
import com.api.jira.Repository.UtilisateurRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentaireService {

    private final CommentaireRepo commentaireRepo;
    private final TicketRepo ticketRepo;
    private final UtilisateurRepo utilisateurRepo;

    public CommentaireService(CommentaireRepo commentaireRepo, TicketRepo ticketRepo, UtilisateurRepo utilisateurRepo) {
        this.commentaireRepo = commentaireRepo;
        this.ticketRepo = ticketRepo;
        this.utilisateurRepo = utilisateurRepo;
    }


    public Commentaire createCommentaire(Long ticketId, Commentaire commentaire) {

        Tickets ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé avec id " + ticketId));

        // si tu veux sécuriser l'auteur (optionnel mais conseillé)
        if (commentaire.getAuteur() == null || commentaire.getAuteur().getId() == null) {
            throw new RuntimeException("Auteur requis (auteur.id)");
        }
        Utilisateur auteur = utilisateurRepo.findById(commentaire.getAuteur().getId())
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));

        commentaire.setAuteur(auteur);
        commentaire.setCommentaireTicket(ticket);
        return commentaireRepo.save(commentaire);
    }

    public List<Commentaire> findAllCommentaire() {
        return commentaireRepo.findAll();
    }

    public Commentaire findCommentaireById(Long id) {
        return commentaireRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec id " + id));
    }

    public Commentaire updateCommentaire(Long id, Commentaire commentaireModifie) {
        Commentaire commentaireExistant = findCommentaireById(id);

        commentaireExistant.setContenu(commentaireModifie.getContenu());

        return commentaireRepo.save(commentaireExistant);
    }

    public void deleteCommentaire(Long id) {
        commentaireRepo.deleteById(id);
    }
}
