package com.api.jira.Controller;
import com.api.jira.Entities.Tag;
import com.api.jira.Repository.TagRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    /*
     créer un tag
     lister tous les tags
     afficher un tag par id
     modifier un tag
     supprimer un tag
  */
    private final TagRepo tagRepo;
    public TagController(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagRepo.save(tag);
    }

    @GetMapping
    public List<Tag> getAllTags()
    {
        return tagRepo.findAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + id));
    }

    // modifier un tag (par ex. renommer ou changer la couleur)
    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Long id,
                         @RequestBody Tag tagModifie) {

        Tag tagExistant = getTagById(id);

        tagExistant.setTagName(tagModifie.getTagName());
        return tagRepo.save(tagExistant);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagRepo.deleteById(id);
    }
}