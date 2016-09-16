package org.niconiconi.blog.controllers.api;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Volio on 2016/9/16.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/search")
public class SearchApiController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getPost(@RequestParam(value = "keyword", required = false) String keyword) {
        return articleService.searchArticles(keyword);
    }
}
