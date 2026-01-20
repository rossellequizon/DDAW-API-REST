package com.api.jira.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "title", "id", "projetName", "projetId", "creatorName", "assigneName", "ticketStatus", "ticketPriority"
})
public class TicketDTO {
    private String title;
    private Long id;
    private String projetName;
    private Long projetId;
    private String creatorName;
    private String assigneName;
    private String ticketStatus;
    private String ticketPriority;
    private List<String> tags;



    public TicketDTO(Tickets ticket) {
        this.title = ticket.getTitle();
        this.id = ticket.getId();
        this.projetName = ticket.getProjet().getProjetName();
        this.projetId = ticket.getProjet().getId();
        this.creatorName = ticket.getCreator().getUsername();
        this.assigneName = (ticket.getAssigne() != null) ? ticket.getAssigne().getUsername() : null;        this.ticketStatus = ticket.getTicketStatus().toString();   // ou direct si c'est String
        this.ticketPriority = ticket.getTicketPriority().toString();
        this.tags = new ArrayList<>();
        for (Tag tag : ticket.getTags()) {
            this.tags.add(tag.getTagName());
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public String getAssigneName() {
        return assigneName;
    }
    public void setAssigneName(String assigneName) {
        this.assigneName = assigneName;
    }
    public String getProjetName() {
        return projetName;
    }
    public void setProjetName(String projetName) {
        this.projetName = projetName;
    }
    public Long getProjetId() {
        return projetId;
    }
    public void setProjetId(Long projetId) {
        this.projetId = projetId;
    }
    public String getTicketStatus() {
        return ticketStatus;
    }
    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
    public String getTicketPriority() {
        return ticketPriority;
    }
    public void setTicketPriority(String ticketPriority) {
        this.ticketPriority = ticketPriority;
    }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
