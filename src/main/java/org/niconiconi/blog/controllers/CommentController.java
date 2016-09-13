package org.niconiconi.blog.controllers;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Volio on 2016/9/8.
 */
@Controller
@RequestMapping("/article/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public String addComment(@Valid Comment comment, Errors errors, @RequestHeader HttpHeaders headers) {
        if (errors.hasErrors()) {
            return "redirect:/failed";
        }
        comment.setIp(headers.getFirst("X-Forwarded-For"));
        comment.setAgent(headers.getFirst("User-Agent"));
        commentService.save(comment);
        return "redirect:/success";
    }
}
