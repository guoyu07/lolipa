package org.niconiconi.blog.controllers.api;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.CommentService;
import org.niconiconi.blog.structure.CommentNode;
import org.niconiconi.blog.utils.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Volio on 2016/9/16.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/comments")
public class CommentApiController {

    private final CommentService commentService;

    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public CommentNode addComment(@RequestBody @Valid Comment comment, @RequestHeader HttpHeaders headers) {
        comment.setIp(headers.getFirst("X-Forwarded-For"));
        comment.setAgent(headers.getFirst("User-Agent"));
        comment = commentService.save(comment);

        String mailMD5 = Encode.string2MD5(comment.getMail());
        String avatarUrl = "https://secure.gravatar.com/avatar/" + mailMD5 + "?s=32&r=G&d=mm";

        CommentNode commentNode = new CommentNode();
        commentNode.setCoid(comment.getCoid());
        commentNode.setCreatedAt(comment.getCreatedAt());
        commentNode.setParentId(comment.getParentId());
        commentNode.setAuthor(comment.getAuthor());
        commentNode.setAvatar(avatarUrl);
        commentNode.setUrl(comment.getUrl());
        commentNode.setText(comment.getText());
        return commentNode;
    }
}
