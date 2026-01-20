package com.api.jira.Controller;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Tickets;
import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.UtilisateurRepo;
import com.api.jira.Service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    /* creer user
       afficher lite de tous les user
       afficher un seul user
       modifier les info d'un user
       delete un user
       afficher la liste de ses projets
       afficher la liste de ses tickets
     */
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService= utilisateurService;
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.create(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> AllUtilisateur(){
        return utilisateurService.getUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur findUtilisateurById(@PathVariable Long id){
        return utilisateurService.findById(id);
    }

    @GetMapping("/{id}/projets")
    public List<Projet> getProjet(@PathVariable Long id){
        return utilisateurService.getProjets(id);
    }

    @GetMapping("/{id}/tickets")
    public List<Tickets> getCreatorTicket(@PathVariable Long id){
        return  utilisateurService.getCreatorTickets(id);
    }

    @GetMapping("/{id}/assigneticket")
    public List<Tickets> getAssigneTicket(@PathVariable Long id){
        return utilisateurService.getAssigneTickets(id);
    }

    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurModifie) {
        return utilisateurService.update(id, utilisateurModifie);
    }

    @DeleteMapping("/{id}")
    public String deleteUtilisateur(@PathVariable Long id){
        utilisateurService.deleteUtilisateur(id);
        return "Utilisateur supprimé avec succès, id : " + id;
    }

}