package org.niconiconi.blog.repositories;

import org.niconiconi.blog.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Volio on 2016/9/6.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentsByCid(Long cid);

    List<Comment> findCommentsByCidAndStatus(Long cid,String status);

    Page<Comment> findAll(Pageable pageable);

    List<Comment> findAllByStatusOrderByCoidDesc(String status);

    int countCommentsByCid(Long cid);

    @Transactional
    Long deleteByCid(Long cid);

    @Query("SELECT COUNT(c.coid) FROM Comment c WHERE c.status = :status")
    Long countByStatus(@Param("status") String status);
}
