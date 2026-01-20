package com.api.jira.Controller;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.ProjetDTO;
import com.api.jira.Entities.Status;
import com.api.jira.Entities.Tickets;
import com.api.jira.Repository.ProjetRepo;
import com.api.jira.Service.ProjetService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/projet")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping
    public Projet createProjet(@RequestBody Projet projet) {
        System.out.println("DEBUG projetName=" + projet.getProjetName());
        System.out.println("DEBUG projetDescription=" + projet.getProjetDescription());
        return projetService.createProjet(projet);
    }

    @GetMapping("/DTO")
    public List<ProjetDTO> getProjetsMinimal() {
        return projetService.getAllProjetsMinimalDTO();
    }

    @GetMapping("/DTO/{id}")
    public ProjetDTO getProjetMinimalDTOById(@PathVariable Long id) {
        return projetService.getProjetMinimalDTOById(id);
    }

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id);
    }

    @GetMapping("/status/{id}")
    public Status getProjetStatusById(@PathVariable Long id) {
        return projetService.getProjetStatus(id);
    }
    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id,
                               @RequestBody Projet projetModifie) {

        return  projetService.updateProjet(id, projetModifie);
    }

    @DeleteMapping("/{id}")
    public String deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return "Projet supprimé" +id + "avec succès";
    }

    @PatchMapping("/{id}/status")
    public Projet updateProjetStatus(@PathVariable Long id,
                                     @RequestParam Status status) {
        return projetService.updateStatus(id, status);
    }

    @GetMapping("/{id}/tickets")
    public List<Tickets> getTicketsByProjet(@PathVariable Long id) {
        return projetService.getTicketsByProjet(id);
    }
}