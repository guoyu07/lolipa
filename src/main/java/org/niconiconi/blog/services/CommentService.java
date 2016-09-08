package org.niconiconi.blog.services;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<Comment> comments = commentRepository.findCommentsByCid(cid);
        return comments;
    }
}
