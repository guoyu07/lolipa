package org.niconiconi.blog.services;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Volio on 2016/9/6.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findCommentsByCid(Long cid) {
        return commentRepository.findCommentsByCid(cid);
    }

    public Page<Comment> findAllCommentsByPage(int pageNum, int pageSize) {
        return commentRepository.findAll(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "coid"));
    }
}
