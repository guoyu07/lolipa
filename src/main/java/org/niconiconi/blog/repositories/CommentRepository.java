package org.niconiconi.blog.repositories;

import org.niconiconi.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Volio on 2016/9/6.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentsByCid(Long cid);
}
