package com.api.jira.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "TICKET_DESCRIPTION", nullable = false, length = 200)
    private String ticketDescription;

    @Enumerated(EnumType.STRING)  // stocke TODO / EN_COURS / TERMINE en base
    @Column(name = "TICKET_STATUS", nullable = false)
    private Status ticketStatus = Status.TODO;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_PRIORITY", nullable = false)
    private Priority ticketPriority = Priority.LOW;

    @Column(name = "CREATION_DATE", nullable = false, insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "DEADLINE")
    private LocalDate deadline;

    @ManyToOne(optional = false) //1,1
    @JoinColumn(name = "PROJET_ID", nullable = false)
    private Projet projetId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CREATOR_ID", nullable = false)
    private Utilisateur creatorId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ASSIGNE_ID", nullable = false)
    private Utilisateur assigneId;

    //relation 0,N avec ticket
    @OneToMany(mappedBy = "commentaireTicket", cascade = CascadeType.ALL)
    private List<Commentaire> commentaire = new ArrayList<>();

    //association table TICKET TAG
    @ManyToMany
    @JoinTable(
            name = "TICKET_TAG",
            joinColumns = @JoinColumn(name = "TICKET_ID"), // FK vers l'id de ticket
            inverseJoinColumns = @JoinColumn(name = "TAG_ID") // FK vers l'id de tag
    )
    private List<Tag> tags = new ArrayList<>();

    public Tickets() {}
    public Tickets(Long id, String title, String ticketDescription, Status ticketStatus, Priority ticketPriority, LocalDateTime creationDate, LocalDate deadline, Projet projetId, Utilisateur creatorId,  Utilisateur assigneeId) {
        this.id = id;
        this.title = title;
        this.ticketDescription = ticketDescription;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.projetId = projetId;
        this.creatorId = creatorId;
        this.assigneId = assigneeId;
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
    public String getTicketDescription() {
        return ticketDescription;
    }
    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }
    public Status getTicketStatus() {
        return ticketStatus;
    }
    public void setTicketStatus(Status ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
    public Priority getTicketPriority() {
        return ticketPriority;
    }
    public void setTicketPriority(Priority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public Projet getProjetId() {
        return projetId;
    }
    public void setProjetId(Projet projetId) {
        this.projetId = projetId;
    }
    public Utilisateur getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Utilisateur creatorId) {
        this.creatorId = creatorId;
    }
    public Utilisateur getAssigneeId() {
        return assigneId;
    }
    public void setAssigneeId(Utilisateur assigneeId) {
        this.assigneId = assigneeId;
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public  List<Commentaire> getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(List<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }
}
