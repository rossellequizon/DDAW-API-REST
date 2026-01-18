package com.api.jira.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "DEADLINE")
    private LocalDate deadline;

    @ManyToOne(optional = false) //1,1
    @JsonIgnore
    @JoinColumn(name = "PROJET_ID", nullable = false)
    private Projet projet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CREATOR_ID", nullable = false)
    private Utilisateur creator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ASSIGNE_ID", nullable = false)
    private Utilisateur assigne;

    //relation 0,N avec ticket
    @OneToMany(mappedBy = "commentaireTicket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
    public Tickets(Long id, String title, String ticketDescription, Status ticketStatus, Priority ticketPriority, LocalDateTime creationDate, LocalDate deadline, Projet projet, Utilisateur creator,  Utilisateur assignee) {
        this.id = id;
        this.title = title;
        this.ticketDescription = ticketDescription;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.projet = projet;
        this.creator = creator;
        this.assigne = assignee;
    }
}
