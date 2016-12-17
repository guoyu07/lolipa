package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Volio on 2016/9/10.
 */
@Controller
@RequestMapping("/admin/articles")
public class AdminArticleController {

    private final ArticleService articleService;

    @Autowired
    public AdminArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getArticles(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        org.springframework.data.domain.Page<Article> articles = articleService.findAllByPage(pageNum, 10);
        model.addAttribute("articles", articles);

        if (pageNum > 0) {
            model.addAttribute("prePage", true);
            model.addAttribute("prePageNum", pageNum);
        } else {
            model.addAttribute("prePage", false);
        }

        if ((pageNum + 1) < articles.getTotalPages()) {
            model.addAttribute("nextPage", true);
            model.addAttribute("nextPageNum", pageNum + 2);
        }else {
            model.addAttribute("nextPage", false);
        }

        model.addAttribute("title", "管理文章");
        return "admin/article/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String getNewArticle(Model model) {
        model.addAttribute("title", "撰写新文章");
        return "admin/article/edit";
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("pid") Long pid, Model model) {
        Article article = articleService.findArticle(pid);
        model.addAttribute("article", article);
        model.addAttribute("title", "编辑页面");
        return "admin/article/edit";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addArticle(@RequestBody @Valid Article article) {
        articleService.save(article);
    }

    @RequestMapping(value = "/{pid}", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public void updateArticle(@PathVariable("pid") Long pid, @RequestBody @Valid Article article) {
        article.setId(pid);
        articleService.update(article);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteArticle(@PathVariable("pid") Long pid) {
        articleService.delete(pid);
    }
}
