package com.api.jira.Service;

import com.api.jira.Entities.*;
import com.api.jira.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class TicketService {

    private final TicketRepo ticketRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final CommentaireRepo commentaireRepo;
    private final TagRepo tagRepo;
    private final ProjetRepo projetRepo;

    public TicketService(TicketRepo ticketRepo, UtilisateurRepo utilisateurRepo, CommentaireRepo commentaireRepo, TagRepo tagRepo, ProjetRepo projetRepo) {
        this.ticketRepo = ticketRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.commentaireRepo = commentaireRepo;
        this.tagRepo = tagRepo;
        this.projetRepo = projetRepo;
    }

    public Tickets createTicket(Tickets ticket) {
        if(ticket.getTitle() == null || ticket.getTitle().isEmpty()){
            throw new IllegalArgumentException("Le titre est requis");
        }
        if(ticket.getProjet() == null || ticket.getProjet().getId() == null){
            throw new IllegalArgumentException("Le projet est requis");
        }
        if(ticket.getTicketDescription() == null || ticket.getCreator().getId() == null){
            throw new IllegalArgumentException("Le créateur est requis");
        }
        Projet projet = projetRepo.findById(ticket.getProjet().getId())
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'id : " + ticket.getProjet().getId()));
        // Vérifier que le créateur existe
        Utilisateur creator = utilisateurRepo.findById(ticket.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("Créateur non trouvé avec l'id : " + ticket.getCreator().getId()));

        ticket.setProjet(projet);
        ticket.setCreator(creator);

        // Vérifier l'assigné s'il existe
        if (ticket.getAssigne() != null && ticket.getAssigne().getId() != null) {
            Utilisateur assignee = utilisateurRepo.findById(ticket.getAssigne().getId())
                    .orElseThrow(() -> new RuntimeException("Assigné non trouvé avec l'id : " + ticket.getAssigne().getId()));
            ticket.setAssigne(assignee);
        }
        return ticketRepo.save(ticket);
    }

    public List<Tickets> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Tickets getTicketById(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé avec id " + id));

    }

    public Tickets updateTicket(Tickets ticketModifie, Long id) {
        Tickets ticketExistant = getTicketById(id);

        ticketExistant.setTitle(ticketModifie.getTitle());
        ticketExistant.setTicketDescription(ticketModifie.getTicketDescription());
        ticketExistant.setTicketPriority(ticketModifie.getTicketPriority());
        ticketExistant.setDeadline(ticketModifie.getDeadline());

        return ticketRepo.save(ticketExistant);
    }

    public void deleteTicketById(Long id) {
        if (!ticketRepo.existsById(id)) {
            throw new RuntimeException("Ticket non trouvé avec l'id : " + id);
        }
        ticketRepo.deleteById(id);
    }

    public Tickets updateStatus(Long id, Status status) {
        Tickets ticket = getTicketById(id);
        ticket.setTicketStatus(status);
        return ticketRepo.save(ticket);
    }

    public Tickets updateTicketAssignee(Long id, Long userId) {
        Tickets ticket = getTicketById(id);
        Utilisateur utilisateur = utilisateurRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé avec id" + userId));
        ticket.setAssigne(utilisateur);
        return ticketRepo.save(ticket);
    }

    public List<Commentaire> getCommentaireByTicket(Long ticketId) {
        Tickets ticket = getTicketById(ticketId);
        return ticket.getCommentaire();
    }

    public Commentaire createCommentaire(Long id, Commentaire commentaire) {
        Tickets ticket = getTicketById(id);
        if (commentaire.getContenu() == null || commentaire.getContenu().isEmpty()) {
            throw new IllegalArgumentException("Le contenu du commentaire est requis");
        }
        commentaire.setCommentaireTicket(ticket);
        if(commentaire.getAuteur() == null){
            commentaire.setAuteur(ticket.getCreator());
        }

        return commentaireRepo.save(commentaire);
    }

    public Tickets addTagToTicket(Long id, Long tagId) {
        Tickets ticket = getTicketById(id);
        Tag tag = tagRepo.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + tagId));

        ticket.getTags().add(tag);
        return ticketRepo.save(ticket);
    }

    public Tickets removeTagFromTicket(Long id, Long tagId) {
        Tickets ticket = getTicketById(id);
        Tag tag = tagRepo.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + tagId));

        ticket.getTags().remove(tag);
        return ticketRepo.save(ticket);
    }
}
