package com.api.jira.Controller;

import com.api.jira.Entities.Commentaire;
import com.api.jira.Repository.CommentaireRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/commentaire")
public class CommentaireController {
    /*
        créer un commentaire
        lister tous les commentaires
        afficher un commentaire par id
        modifier un commentaire
        supprimer un commentaire
     */
    private final CommentaireRepo commentaireRepo;
    public CommentaireController(CommentaireRepo commentaireRepo) {
        this.commentaireRepo = commentaireRepo;
    }

    @PostMapping
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        if (commentaire.getCreationDate() == null) {
            commentaire.setCreationDate(LocalDateTime.now());
        }
        return commentaireRepo.save(commentaire);
    }

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepo.findAll();
    }

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable Long id) {
        return commentaireRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec id " + id));
    }

    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id,
                                         @RequestBody Commentaire commentaireModifie) {

        Commentaire commentaireExistant = getCommentaireById(id);

        commentaireExistant.setContenu(commentaireModifie.getContenu());
        commentaireExistant.setAuteur(commentaireModifie.getAuteur());
        commentaireExistant.setCommentaireTicket(commentaireModifie.getCommentaireTicket());

        return commentaireRepo.save(commentaireExistant);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentaire(@PathVariable Long id) {
        commentaireRepo.deleteById(id);
    }
}