package org.niconiconi.blog.controllers;

import org.niconiconi.blog.errors.NotFoundException;
import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.services.ArticleService;
import org.niconiconi.blog.services.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

/**
 * Created by Volio on 2016/9/3.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EnvService envService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return getPagination(1, model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchRedirect(@RequestParam(value = "s", required = false) String s, RedirectAttributes attributes) {
        if (Objects.equals(s, ""))
            return "redirect:/";
        else {
            attributes.addAttribute("keyword", s);
            return "redirect:/search";
        }
    }

    //分页查询
    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String getPagination(@PathVariable("pageNum") int pageNum, Model model) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        org.springframework.data.domain.Page<Article> articles = articleService.findAllByPage(pageNum, envService.getPageSize());
        if (articles.getNumberOfElements() == 0) {
            throw new NotFoundException();
        }
        model.addAttribute("allPageNum", articles.getTotalPages());
        model.addAttribute("pageNum", pageNum + 1);
        model.addAttribute("articles", articles);
        return "home/index";
    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public String getPage(@PathVariable("slug") String slug, Model model) {
        Page page = articleService.findPage(slug);
        model.addAttribute("article", page);
        return "article/page";
    }
}
