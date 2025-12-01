package com.api.jira.Controller;

import com.api.jira.Entities.*;
import com.api.jira.Repository.CommentaireRepo;
import com.api.jira.Repository.TagRepo;
import com.api.jira.Repository.TicketRepo;
import com.api.jira.Repository.UtilisateurRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    /*
        creer un ticket
        afficher tous les ticket
        afficher un ticket
        modifier un ticket
        supprimer un ticket
        changer le status d'un ticket
        assigner un ticket a un utlisateur
        lister les commentaires d'un ticket
        ajouter un commentaire
        ajouter un tag au ticket
        retirer un tag du ticket
     */
    private final TicketRepo ticketRepo;
    private UtilisateurRepo utilisateurRepo;
    private CommentaireRepo commentaireRepo;
    private TagRepo tagRepo;

    public TicketController(TicketRepo ticketRepo, UtilisateurRepo utilisateurRepo, CommentaireRepo commentaireRepo, TagRepo tagRepo) {
        this.ticketRepo = ticketRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.commentaireRepo = commentaireRepo;
        this.tagRepo = tagRepo;
    }

    @PostMapping
    public Tickets createTicket(@RequestBody Tickets ticket) {
        return ticketRepo.save(ticket);
    }

    @GetMapping
    public List<Tickets> getAllTickets() {
        return ticketRepo.findAll();
    }

    @GetMapping("/{id}")
    public Tickets getTicketById(@PathVariable Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé avec id " + id));
    }

    @PutMapping("/{id}")
    public Tickets updateTicket(@RequestBody Tickets ticketModifie, @PathVariable Long id) {
        Tickets ticketExistant = getTicketById(id);

        ticketExistant.setTicketDescription(ticketModifie.getTicketDescription());
        ticketExistant.setTicketStatus(ticketModifie.getTicketStatus());
        ticketExistant.setTicketPriority(ticketModifie.getTicketPriority());
        ticketExistant.setTitle(ticketModifie.getTitle());
        ticketExistant.setProjetId(ticketModifie.getProjetId());
        ticketExistant.setDeadline(ticketModifie.getDeadline());
        ticketExistant.setCreatorId(ticketModifie.getCreatorId());
        ticketExistant.setAssigneeId(ticketModifie.getAssigneeId());
        ticketExistant.setTags(ticketModifie.getTags());

        return ticketRepo.save(ticketExistant);
    }

    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        ticketRepo.deleteById(id);
    }

    @PatchMapping("/{id}/status")
    public Tickets updateTicketStatus(@PathVariable Long id, @RequestParam Status status) {
        Tickets ticket = getTicketById(id);
        ticket.setTicketStatus(status);
        return ticketRepo.save(ticket);
    }

    @PatchMapping("/{id}/assign")
    public Tickets updateTicketAssign(@PathVariable Long id, @RequestParam Long userId) {
        Tickets ticket = getTicketById(id);
        Utilisateur utilisateur = utilisateurRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé avec id" + id));
        ticket.setAssigneeId(utilisateur);
        return ticketRepo.save(ticket);
    }

    @GetMapping("/{id}/commentaire")
    public List<Commentaire> getCommentaireByTicket(@PathVariable Long id) {
        Tickets ticket = getTicketById(id);
        return ticket.getCommentaire();
    }

    @PostMapping("/{id}/commentaire")
    public Commentaire createCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        Tickets ticket = getTicketById(id);
        commentaire.setCommentaireTicket(ticket);

        return commentaireRepo.save(commentaire);
    }

    @PostMapping("/{id}/tags/{tagId}")
    public Tickets addTagToTicket(@PathVariable Long id,
                                 @PathVariable Long tagId) {

        Tickets ticket = getTicketById(id);
        Tag tag = tagRepo.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + tagId));

        ticket.getTags().add(tag);
        return ticketRepo.save(ticket);
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public Tickets removeTagFromTicket(@PathVariable Long id,
                                      @PathVariable Long tagId) {

        Tickets ticket = getTicketById(id);
        Tag tag = tagRepo.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + tagId));

        ticket.getTags().remove(tag);
        return ticketRepo.save(ticket);
    }
}