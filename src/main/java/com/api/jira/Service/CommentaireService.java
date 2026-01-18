package com.api.jira.Service;

import com.api.jira.Entities.Commentaire;
import com.api.jira.Repository.CommentaireRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentaireService {

    private final CommentaireRepo commentaireRepo;
    public CommentaireService(CommentaireRepo commentaireRepo) {
        this.commentaireRepo = commentaireRepo;
    }

    public Commentaire createCommentaire(Commentaire commentaire) {
        if (commentaire.getCreationDate() == null) {
            commentaire.setCreationDate(LocalDateTime.now());
        }
        return commentaireRepo.save(commentaire);
    }

    public List<Commentaire> findAllCommentaire() {
        return commentaireRepo.findAll();
    }

    public Commentaire findCommentaireById(Long id) {
        return commentaireRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec id " + id));
    }

    public Commentaire updateCommentaire(Long id, Commentaire commentaireModifie) {
        Commentaire commentaireExistant = findCommentaireById(id);

        commentaireExistant.setContenu(commentaireModifie.getContenu());

        return commentaireRepo.save(commentaireExistant);
    }

    public void deleteCommentaire(Long id) {
        commentaireRepo.deleteById(id);
    }
}
