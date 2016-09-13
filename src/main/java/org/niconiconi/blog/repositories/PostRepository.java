package org.niconiconi.blog.repositories;

import org.niconiconi.blog.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.commentNum = :commentNum WHERE p.id = :cid")
    void changeCommentNum(@Param("cid") Long cid, @Param("commentNum") int commentNum);
}
