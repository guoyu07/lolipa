package org.niconiconi.blog.controllers.api;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.ArticleService;
import org.niconiconi.blog.services.CommentService;
import org.niconiconi.blog.structure.CommentNode;
import org.niconiconi.blog.utils.CommentUtil;
import org.niconiconi.blog.utils.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Volio on 2016/9/15.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/articles")
public class ArticleApiController {

    private final ArticleService articleService;

    private final CommentService commentService;

    @Autowired
    public ArticleApiController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Article> getArticles(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        return articleService.findAllByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable("pid") Long pid) {
        return articleService.findHtmlArticle(pid);
    }

    @RequestMapping(value = "/{pid}/comments", method = RequestMethod.GET)
    public List<CommentNode> getArticleComment(@PathVariable("pid") Long pid) {
        List<Comment> comments = commentService.findApprovedCommentsByCid(pid);
        return CommentUtil.buildCommentsTree(comments, true);
    }
}
