package com.dev.blog.controllers;

import com.dev.blog.domain.dtos.CreateTagsRequest;
import com.dev.blog.domain.dtos.TagResponse;
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
    public ResponseEntity<List<TagResponse>> listTags() {
        List<Tag> tags = tagService.listTags();
        List<TagResponse> tagResponses = tags.stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(
            @Valid @RequestBody CreateTagsRequest createTagsRequest
    ) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return new ResponseEntity<>(
                createdTagResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
