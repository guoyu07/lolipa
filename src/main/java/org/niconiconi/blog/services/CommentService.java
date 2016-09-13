package org.niconiconi.blog.services;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public List<Comment> findApprovedCommentsByCid(Long cid) {
        return commentRepository.findCommentsByCidAndStatus(cid, "approved");
    }

    public Page<Comment> findAllCommentsByPage(int pageNum, int pageSize) {
        return commentRepository.findAll(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "coid"));
    }

    public Comment save(Comment comment) {
        comment.setCreatedAt(new Date(System.currentTimeMillis()));
        comment.setStatus("waiting");
        return commentRepository.save(comment);
    }

    public void delete(Long id) {
        commentRepository.delete(id);
    }

    public void deleteCommentsByCid(Long cid) {
        commentRepository.deleteByCid(cid);
    }

    public Comment approveComment(Long id) {
        Comment sComment = commentRepository.findOne(id);
        if(sComment==null){
            throw new NotFoundException();
        }
        sComment.setStatus("approved");
        return commentRepository.save(sComment);
    }
}
