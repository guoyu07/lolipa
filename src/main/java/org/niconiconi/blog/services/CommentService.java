package org.niconiconi.blog.services;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.repositories.CommentRepository;
import org.niconiconi.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Volio on 2016/9/6.
 */
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    public List<Comment> findCommentsByCid(Long cid) {
        return commentRepository.findCommentsByCid(cid);
    }

    public List<Comment> findApprovedCommentsByCid(Long cid) {
        return commentRepository.findCommentsByCidAndStatusOrderByCoidDesc(cid, "approved");
    }

    public List<Comment> findWaitingComments() {
        return commentRepository.findAllByStatusOrderByCoidDesc("waiting");
    }

    public Page<Comment> findAllCommentsByPage(int pageNum, int pageSize) {
        return commentRepository.findAll(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "coid"));
    }

    public Comment save(Comment comment) {
        comment.setCreatedAt(new Date());
        comment.setStatus("waiting");
        comment = commentRepository.save(comment);
        int count = commentRepository.countCommentsByCid(comment.getCid());
        articleRepository.changeCommentNum(comment.getCid(),count);
        return comment;
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findOne(id);
        commentRepository.delete(comment);
        int count = commentRepository.countCommentsByCid(comment.getCid());
        articleRepository.changeCommentNum(comment.getCid(),count);
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
