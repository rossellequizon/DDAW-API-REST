package com.api.jira.Service;

import com.api.jira.Entities.Profil;
import com.api.jira.Entities.Utilisateur;
import com.api.jira.Repository.ProfilRepo;
import com.api.jira.Repository.UtilisateurRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProfilService {
    public final ProfilRepo profilRepo;
    public final UtilisateurRepo utilisateurRepo;

    public ProfilService(ProfilRepo profilRepo, UtilisateurRepo utilisateurRepo) {
        this.profilRepo = profilRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    public Profil createProfil(Profil profil) {
        Utilisateur u = utilisateurRepo.findById(profil.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        profil.setUtilisateur(u);
        return profilRepo.save(profil);
    }

    public List<Profil> getProfils() {
        return profilRepo.findAll();
    }
    public Profil getProfilById(Long id) {
        return profilRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Profil non trouve avec id" + id));
    }

    public Profil update(Profil profilModifie, Long id) {
        Profil profilExistant = getProfilById(id);

        profilExistant.setFullname(profilModifie.getFullname());
        profilExistant.setMetier(profilModifie.getMetier());
        return profilRepo.save(profilExistant);
    }

    public void deleteProfilById(Long id) {
        profilRepo.deleteById(id);
    }
}
