package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.services.CommentService;
import org.niconiconi.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Volio on 2016/9/8.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAdminPage(Model model, Principal principal) {
        Page<Article> articles = articleService.findAllByPage(0, 6);
        Page<Comment> comments = commentService.findAllCommentsByPage(0, 6);
        String username = principal.getName();
        model.addAttribute("articles", articles);
        model.addAttribute("comments", comments);
        model.addAttribute("username", username);
        return "admin/index";
    }
}
