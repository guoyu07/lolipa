package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Volio on 2016/9/10.
 */
@Controller
@RequestMapping("/admin/articles")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getArticles(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        org.springframework.data.domain.Page<Article> articles = articleService.findAllByPage(pageNum, 10);
        model.addAttribute("articles", articles);
        model.addAttribute("allPageNum", articles.getTotalPages());
        model.addAttribute("pageNum", pageNum + 1);
        return "admin/article/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String getNewArticle() {
        return "admin/article/write";
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("pid") Long pid, Model model) {
        Article article = articleService.findArticle(pid);
        model.addAttribute("article", article);
        return "admin/article/write";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addArticle(@RequestBody @Valid Article article) {
        articleService.save(article);
    }

    @RequestMapping(value = "/{pid}", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public void reEditArticle(@PathVariable("pid") Long pid,@RequestBody @Valid Article article) {
        article.setId(pid);
        articleService.update(article);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteArticle(@PathVariable("pid") Long pid) {
        articleService.delete(pid);
    }
}
