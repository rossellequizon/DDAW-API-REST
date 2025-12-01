package com.api.jira.Controller;

import com.api.jira.Entities.Profil;
import com.api.jira.Repository.ProfilRepo;
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
    private final ProfilRepo profilRepo;

    public ProfilController(ProfilRepo profilRepo) {
        this.profilRepo = profilRepo;
    }

    @PostMapping
    public Profil createProfil(@RequestBody Profil profil) {
        return profilRepo.save(profil);
    }

    @GetMapping("/{id}")
    public Profil getProfilById(@PathVariable Long id) {
        return profilRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Profil non trouve avec id" + id));
    }

    @GetMapping
    public List<Profil> getProfils() {
        return profilRepo.findAll();
    }

    @PutMapping("/{id}")
    public Profil updateProfil(@RequestBody Profil profilModifie, @PathVariable Long id) {
        Profil profilExistant = getProfilById(id);

        profilExistant.setFullname(profilModifie.getFullname());
        profilExistant.setUtilisateur(profilModifie.getUtilisateur());
        profilExistant.setMetier(profilModifie.getMetier());
        return profilRepo.save(profilExistant);
    }

    @DeleteMapping("/{id}")
    public void deleteProfil(@PathVariable Long id) {
        profilRepo.deleteById(id);
    }
}