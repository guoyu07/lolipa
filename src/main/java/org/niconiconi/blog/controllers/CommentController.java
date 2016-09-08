package org.niconiconi.blog.controllers;

import org.niconiconi.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Volio on 2016/9/8.
 */
@Controller
@RequestMapping("/article/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public String addComment() {
        return "redirect:/";
    }
}
