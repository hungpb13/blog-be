package com.dev.blog.controllers;

import com.dev.blog.domain.dtos.CreateTagsRequest;
import com.dev.blog.domain.dtos.TagDto;
import com.dev.blog.domain.entities.Tag;
import com.dev.blog.mappers.TagMapper;
import com.dev.blog.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> listTags() {
        List<Tag> tags = tagService.listTags();
        List<TagDto> tagDtos = tags.stream()
                .map(tagMapper::toDto)
                .toList();
        return ResponseEntity.ok(tagDtos);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(
            @Valid @RequestBody CreateTagsRequest createTagsRequest
    ) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagDto> createdTagDtos = savedTags.stream()
                .map(tagMapper::toDto)
                .toList();
        return new ResponseEntity<>(
                createdTagDtos,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
