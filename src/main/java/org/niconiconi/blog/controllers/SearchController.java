package org.niconiconi.blog.controllers;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Volio on 2016/9/5.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPost(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Article> articles = articleService.searchArticles(keyword);
        model.addAttribute("title", "包含关键字 " + keyword + " 的文章");
        if (articles.isEmpty()) {
            return "errors/404";
        } else {
            model.addAttribute("posts", articles);
            return "home/search";
        }
    }
}
