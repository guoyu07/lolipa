package org.niconiconi.blog.controllers.api;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.CommentService;
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

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST,consumes = "application/json")
    public Comment addComment(@RequestBody @Valid Comment comment, @RequestHeader HttpHeaders headers) {
        comment.setIp(headers.getFirst("X-Forwarded-For"));
        comment.setAgent(headers.getFirst("User-Agent"));
        comment = commentService.save(comment);
        String mailMD5 = Encode.string2MD5(comment.getMail());
        String avatarUrl = "https://cdn.v2ex.com/gravatar/" + mailMD5 + "?s=32&r=G&d=mm";
        comment.setMail(avatarUrl);
        return comment;
    }
}
