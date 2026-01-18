package com.api.jira.Service;

import com.api.jira.Entities.Tag;
import com.api.jira.Repository.TagRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TagService {

    private final TagRepo tagRepo;
    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Tag createTag(Tag tag) {
        return tagRepo.save(tag);
    }

    public Tag getTag(Long id) {
        return tagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + id));
    }

    public List<Tag> getAllTag(){
        return tagRepo.findAll();
    }

    public Tag updateTag(Long id, Tag tagModifie) {
        Tag tagExistant = getTag(id);

        tagExistant.setTagName(tagModifie.getTagName());
        return tagRepo.save(tagExistant);
    }

    public void deleteTag(Long id) {
        tagRepo.deleteById(id);
    }
}
