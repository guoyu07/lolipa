package org.niconiconi.blog.controllers.api;

import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Volio on 2016/9/16.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pages")
public class SinglePageApiController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Page getPage(@RequestParam(value = "slug") String slug) {
        return articleService.findPage(slug);
    }
}
