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
        return  ticketService.createTicket(ticket);
    }

    @GetMapping("/DTO")
    public List<TicketDTO> getTickets() {
        return ticketService.getAllTicketsDTO();
    }

    @GetMapping("/DTO/{ticketsId}")
    public TicketDTO getTicketDTOById(@PathVariable Long ticketsId) {
        return ticketService.getTicketDTOById(ticketsId);
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
    public String deleteTicketById(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
        return "Ticket supprimé avec succès, id : " + id;
    }

    @GetMapping("/status/{id}")
    public Status getTicketStatusById(@PathVariable Long id) {
        return ticketService.getTicketStatus(id);
    }
    @PatchMapping("/{id}/status")
    public Tickets updateTicketStatus(@PathVariable Long id, @RequestParam Status status) {
        return ticketService.updateStatus(id, status);
    }
    @GetMapping("/priority/{id}")
    public Priority getTicketPriorityById(@PathVariable Long id) {
        return ticketService.getTicketPriority(id);
    }

    @PatchMapping("/{id}/priority")
    public Tickets updateTicketPriority(@PathVariable Long id, @RequestParam Priority priority) {
        return ticketService.updateTicketPriority(id, priority);
    }

    @PatchMapping("/{id}/userId")
    public Tickets updateTicketAssign(@PathVariable Long id, @RequestParam Long userId) {
        return ticketService.updateTicketAssignee(id, userId);
    }

    @PostMapping("commentaire/{id}")
    public Commentaire createCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        return ticketService.createCommentaire(id, commentaire);
    }

    @GetMapping("/commentaire/{id}")
    public List<Commentaire> getCommentaireByTicket(@PathVariable Long id) {
        return  ticketService.getCommentaireByTicket(id);
    }

    @PostMapping("/tags/{id}/{tagId}")
    public Tickets addTagToTicket(@PathVariable Long id,
                                  @PathVariable Long tagId) {
        return ticketService.addTagToTicket(id, tagId);
    }

    @DeleteMapping("/tags/{id}/{tagId}")
    public Tickets removeTagFromTicket(@PathVariable Long id,
                                       @PathVariable Long tagId) {
        return ticketService.removeTagFromTicket(id, tagId);
    }
}