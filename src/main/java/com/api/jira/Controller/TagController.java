package com.api.jira.Controller;
import com.api.jira.Entities.Tag;
import com.api.jira.Repository.TagRepo;
import com.api.jira.Service.TagService;
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
    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @GetMapping
    public List<Tag> getAllTags()
    {
        return tagService.getAllTag();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTag(id);
    }

    // modifier un tag (par ex. renommer ou changer la couleur)
    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Long id,
                         @RequestBody Tag tagModifie) {
        return tagService.updateTag(id, tagModifie);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "Tag supprimé avec succès id : " + id;

    }
}