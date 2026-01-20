package com.api.jira.Controller;

import com.api.jira.Entities.Profil;
import com.api.jira.Entities.ProfilDTO;
import com.api.jira.Repository.ProfilRepo;
import com.api.jira.Service.ProfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profil")
public class ProfilController {
    /* creer profil
       liste tous les profils
       liste un seul profil by id
       modifier un profil
       supprimer le profil
    */
    private final ProfilService profilService;

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping("/utilisateur/{userId}")
    public Profil createProfil(@PathVariable Long userId, @RequestBody Profil profil) {
        return profilService.createProfil(userId, profil);
    }

    @GetMapping("/DTO/{id}")
    public ProfilDTO getProfilDTO(@PathVariable Long id) {
        return profilService.getProfilDTO(id);
    }

    @GetMapping("/DTO")
    public List<ProfilDTO> getAllProfilDTO() {
        return profilService.getAllProfilDTO();
    }

    @GetMapping("/{id}")
    public Profil getProfilById(@PathVariable Long id) {
        return  profilService.getProfilById(id);
    }

    @GetMapping
    public List<Profil> getProfils() {
        return profilService.getProfils();
    }

    @PutMapping("/{id}")
    public Profil updateProfil(@RequestBody Profil profilModifie, @PathVariable Long id) {
        return profilService.update(profilModifie, id);
    }

    @DeleteMapping("/{id}")
    public String deleteProfil(@PathVariable Long id) {
        profilService.deleteProfilById(id);
        return "Profil supprimé id, avec succès : " + id;

    }
}