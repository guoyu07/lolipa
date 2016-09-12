package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Post;
import org.niconiconi.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Volio on 2016/9/10.
 */
@Controller
@RequestMapping("/admin/article")
public class AdminPostController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPosts(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model, Principal principal) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        org.springframework.data.domain.Page<Post> posts = postService.findAllPostByPage(pageNum, 15);
        String username = principal.getName();
        model.addAttribute("posts", posts);
        model.addAttribute("username", username);
        model.addAttribute("allPageNum", posts.getTotalPages());
        model.addAttribute("pageNum", pageNum + 1);
        return "admin/post/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String getNewPost(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "admin/post/write";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam(value = "pid") Long pid, Model model, Principal principal) {
        Post post = postService.findPost(pid);
        String username = principal.getName();
        model.addAttribute("post", post);
        model.addAttribute("username", username);
        return "admin/post/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String addPost(@Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/admin/article/write";
        }
        postService.save(post);
        return "redirect:/admin/article";
    }
}
