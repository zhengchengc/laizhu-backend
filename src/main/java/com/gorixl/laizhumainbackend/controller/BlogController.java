package com.gorixl.laizhumainbackend.controller;

import com.gorixl.laizhumainbackend.exception.ResourceNotFoundException;
import com.gorixl.laizhumainbackend.model.Blog;
import com.gorixl.laizhumainbackend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * GET all blogs
     * @param pageable
     * @return
     */
    @GetMapping("/blogs")
    public Page<Blog> getBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * POST create a new blog
     * @param blog
     * @return
     */
    @PostMapping("/blogs")
    public Blog createBlog(@Valid @RequestBody Blog blog) {
        return blogRepository.save(blog);
    }

    @PutMapping("/blogs/{blogId}")
    public Blog updateBlog(
            @PathVariable Long blogId,
            @Valid @RequestBody Blog blogRequest) {
        return blogRepository.findById(blogId)
                .map(blog -> {
                    blog.setTitle(blogRequest.getTitle());
                    blog.setContext(blogRequest.getContext());
                    return blogRepository.save(blog);
                }).orElseThrow(() -> new ResourceNotFoundException("Blog not found with id" + blogId));
    }

    @DeleteMapping("/blogs/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long blogId) {
        return blogRepository.findById(blogId)
                .map(blog -> {
                    blogRepository.delete(blog);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Blog not found with id" + blogId));
    }
}
