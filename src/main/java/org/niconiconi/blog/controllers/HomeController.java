package org.niconiconi.blog.controllers;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.models.Post;
import org.niconiconi.blog.services.EnvService;
import org.niconiconi.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Volio on 2016/9/3.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private EnvService envService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return getPagination(1, model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchRedirect(@RequestParam(value = "s", required = false) String s) {
        if (s == "")
            return "redirect:/";
        else
            return "redirect:/search/" + s;
    }

    //分页查询
    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String getPagination(@PathVariable("pageNum") int pageNum, Model model) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        org.springframework.data.domain.Page<Post> posts = postService.findAllPostByPage(pageNum, envService.getPageSize());
        if (posts.getNumberOfElements() == 0) {
            throw new NotFoundException();
        }
        model.addAttribute("allPageNum",posts.getTotalPages());
        model.addAttribute("pageNum",pageNum+1);
        model.addAttribute("posts", posts);
        return "home/index";
    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public String getPage(@PathVariable("slug") String slug, Model model) {
        Page page = postService.findPage(slug);
        model.addAttribute("post", page);
        return "post/page";
    }
}
