package com.api.jira.Controller;

import com.api.jira.Entities.Projet;
import com.api.jira.Entities.Status;
import com.api.jira.Entities.Tickets;
import com.api.jira.Repository.ProjetRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/projet")
public class ProjetController {

    private final ProjetRepo projetRepo;

    public ProjetController(ProjetRepo projetRepo) {
        this.projetRepo = projetRepo;
    }

    @PostMapping
    public Projet createProjet(@RequestBody Projet projet) {
        if (projet.getCreationDate() == null) {
            projet.setCreationDate(LocalDateTime.now());
        }
        return projetRepo.save(projet);
    }

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetRepo.findAll();
    }

    @GetMapping("/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec id " + id));
    }

    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id,
                               @RequestBody Projet projetModifie) {

        Projet projetExistant = getProjetById(id);

        projetExistant.setProjetName(projetModifie.getProjetName());
        projetExistant.setProjetDescription(projetModifie.getProjetDescription());
        projetExistant.setProjetStatus(projetModifie.getProjetStatus());
        projetExistant.setOwnerId(projetModifie.getOwnerId());

        return projetRepo.save(projetExistant);
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetRepo.deleteById(id);
    }


    @PatchMapping("/{id}/status")
    public Projet updateProjetStatus(@PathVariable Long id,
                                     @RequestParam Status status) {

        Projet projet = getProjetById(id);
        projet.setProjetStatus(status);
        return projetRepo.save(projet);
    }

    @GetMapping("/{id}/tickets")
    public List<Tickets> getTicketsByProjet(@PathVariable Long id) {
        Projet projet = getProjetById(id);
        return projet.getTickets();
    }
}