package org.niconiconi.blog.controllers.api;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.ArticleService;
import org.niconiconi.blog.services.CommentService;
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

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Article> getArticles(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        return articleService.findAllByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable("pid") Long pid) {
        return articleService.findArticle(pid);
    }

    @RequestMapping(value = "/{pid}/comments",method = RequestMethod.GET)
    public List<Comment> getArticleComment(@PathVariable("pid") Long pid) {
        List<Comment> comments = commentService.findApprovedCommentsByCid(pid);
        comments = filterUnUsefulCommentsInfo(comments);
        return comments;
    }

    private List<Comment> filterUnUsefulCommentsInfo(List<Comment> comments) {
        for (Comment comment : comments) {
            String mailMD5 = Encode.string2MD5(comment.getMail());
            String avatarUrl = "https://cdn.v2ex.com/gravatar/" + mailMD5 + "?s=32&r=G&d=mm";
            comment.setMail(avatarUrl);
            comment.setIp(null);
        }
        return comments;
    }
}
