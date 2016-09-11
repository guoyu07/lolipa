package org.niconiconi.blog.repositories;

import org.niconiconi.blog.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Volio on 2016/9/4.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostBySlug(String slug);

    Post findPostById(Long pid);

    List<Post> findPostsByContentContaining(String keyword);

    Page<Post> findAll(Pageable pageable);
}
