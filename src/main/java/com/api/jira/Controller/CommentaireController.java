package com.api.jira.Controller;

import com.api.jira.Entities.Commentaire;
import com.api.jira.Repository.CommentaireRepo;
import com.api.jira.Service.CommentaireService;
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
    private final CommentaireService commentaireService;
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        return  commentaireService.createCommentaire(commentaire);
    }

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.findAllCommentaire();
    }

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable Long id) {
        return  commentaireService.findCommentaireById(id);
    }

    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id,
                                         @RequestBody Commentaire commentaireModifie) {
        return commentaireService.updateCommentaire(id, commentaireModifie);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
    }
}