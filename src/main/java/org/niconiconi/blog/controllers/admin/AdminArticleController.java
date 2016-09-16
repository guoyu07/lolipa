package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.services.ArticleService;
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
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPosts(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model, Principal principal) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        org.springframework.data.domain.Page<Article> articles = articleService.findAllByPage(pageNum, 15);
        String username = principal.getName();
        model.addAttribute("articles", articles);
        model.addAttribute("username", username);
        model.addAttribute("allPageNum", articles.getTotalPages());
        model.addAttribute("pageNum", pageNum + 1);
        return "admin/article/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String getNewPost(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "admin/article/write";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam(value = "pid") Long pid, Model model, Principal principal) {
        Article article = articleService.findArticle(pid);
        String username = principal.getName();
        model.addAttribute("article", article);
        model.addAttribute("username", username);
        return "admin/article/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String addPost(@Valid Article article, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/admin/article/write";
        }
        articleService.save(article);
        return "redirect:/admin/article";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String reEditPost(@Valid Article article, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/admin/article/edit?pid=" + article.getId();
        }
        articleService.update(article);
        return "redirect:/admin/article";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePost(@RequestParam(value = "pid") Long pid) {
        articleService.delete(pid);
        return "redirect:/admin/article";
    }
}
