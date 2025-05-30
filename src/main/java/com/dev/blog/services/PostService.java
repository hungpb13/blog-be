package com.dev.blog.services;

import com.dev.blog.domain.CreatePostRequest;
import com.dev.blog.domain.UpdatePostRequest;
import com.dev.blog.domain.entities.Post;
import com.dev.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> listAllPosts(UUID categoryId, UUID tagId);

    List<Post> listDrafts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);

    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

    Post getPost(UUID id);

    void deletePost(UUID id);
}
