package com.api.jira.Controller;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Tickets;
import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.UtilisateurRepo;
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
    private UtilisateurRepo utilisateurRepo;

    public UtilisateurController(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepo.save(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> AllUtilisateur(){
        return utilisateurRepo.findAll();
    }

    @GetMapping("/{id}")
    public Utilisateur findUtilisateurById(@PathVariable Long id){
        return utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id " + id));
    }

    @GetMapping("/{id}/projets")
    public List<Projet> getProjet(@PathVariable Long id){
        Utilisateur user = utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve avec id" + id));
        return user.getProjetPossede();
    }

    @GetMapping("/{id}/tickets")
    public List<Tickets> getCreatorTicket(@PathVariable Long id){
        Utilisateur user = utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve avec id" + id));
        return user.getCreatorTickets();
    }

    @GetMapping("/{id}/assigneticket")
    public List<Tickets> getAssigneTicket(@PathVariable Long id){
        Utilisateur user = utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve avec id" + id));
        return user.getAssigneTicket();
    }

    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurModifie) {

        Utilisateur existing = findUtilisateurById(id);

        existing.setUsername(utilisateurModifie.getUsername());
        existing.setEmail(utilisateurModifie.getEmail());
        existing.setPwd(utilisateurModifie.getPwd());
        existing.setActif(utilisateurModifie.isActif());

        return utilisateurRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id){
        utilisateurRepo.deleteById(id);
    }

}