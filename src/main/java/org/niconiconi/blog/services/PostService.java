package org.niconiconi.blog.services;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.models.Post;
import org.niconiconi.blog.repositories.PageRepository;
import org.niconiconi.blog.repositories.PostRepository;
import org.niconiconi.blog.utils.Markdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Volio on 2016/9/4.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PageRepository pageRepository;

    public org.springframework.data.domain.Page<Post> findAllPostByPage(int pageNum, int pageSize) {
        org.springframework.data.domain.Page<Post> posts = postRepository.findAll(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"));
        for (Post post : posts) {
            post.setContent(Markdown.subMarkdownToHtml(post.getContent()));
        }
        return posts;
    }

    public Post findPost(String slug) {
        Post post = postRepository.findPostBySlug(slug);
        if (post == null) {
            throw new NotFoundException();
        }
        post.setContent(Markdown.markdownToHtml(post.getContent()));
        return post;
    }

    public Post findPost(Long pid) {
        Post post = postRepository.findPostById(pid);
        if (post == null) {
            throw new NotFoundException();
        }
        return post;
    }

    public List<Post> searchPosts(String keyword) {
        List<Post> posts = postRepository.findPostsByContentContaining(keyword);
        for (Post post : posts) {
            post.setContent(Markdown.subMarkdownToHtml(post.getContent()));
        }
        return posts;
    }

    public Page findPage(String slug) {
        Page page = pageRepository.findBySlug(slug);
        if (page == null) {
            throw new NotFoundException();
        }
        page.setContent(Markdown.markdownToHtml(page.getContent()));
        return page;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
