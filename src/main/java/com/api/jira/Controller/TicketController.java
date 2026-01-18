package com.api.jira.Controller;

import com.api.jira.Entities.*;
import com.api.jira.Service.TicketService;
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
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Tickets createTicket(@RequestBody Tickets ticket) {

        System.out.println("DEBUG ticket.projet=" + (ticket.getProjet() == null ? null : ticket.getProjet().getId()));
        return  ticketService.createTicket(ticket);
    }

    @GetMapping
    public List<Tickets> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Tickets getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    public Tickets updateTicket(@RequestBody Tickets ticketModifie, @PathVariable Long id) {
        return  ticketService.updateTicket(ticketModifie, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
    }

    @PatchMapping("/{id}/status")
    public Tickets updateTicketStatus(@PathVariable Long id, @RequestParam Status status) {
        return ticketService.updateStatus(id, status);
    }

    @PatchMapping("/{id}/assign")
    public Tickets updateTicketAssign(@PathVariable Long id, @RequestParam Long userId) {
        return ticketService.updateTicketAssignee(id, userId);
    }

    @GetMapping("/{id}/commentaire")
    public List<Commentaire> getCommentaireByTicket(@PathVariable Long id) {
        return  ticketService.getCommentaireByTicket(id);
    }

    @PostMapping("/{id}/commentaire")
    public Commentaire createCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        return ticketService.createCommentaire(id, commentaire);
    }

    @PostMapping("/{id}/tags/{tagId}")
    public Tickets addTagToTicket(@PathVariable Long id,
                                  @PathVariable Long tagId) {
        return ticketService.addTagToTicket(id, tagId);
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public Tickets removeTagFromTicket(@PathVariable Long id,
                                       @PathVariable Long tagId) {

        return ticketService.removeTagFromTicket(id, tagId);
    }
}