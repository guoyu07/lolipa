package org.niconiconi.blog.controllers;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Post;
import org.niconiconi.blog.services.PostService;
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
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPost(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Post> posts = postService.searchPosts(keyword);
        model.addAttribute("title", "包含关键字 " + keyword + " 的文章");
        if (posts.isEmpty()) {
            return "errors/404";
        } else {
            model.addAttribute("posts", posts);
            return "home/search";
        }
    }
}
