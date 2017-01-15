
package org.niconiconi.blog.services;

import org.niconiconi.blog.errors.PageNotFoundException;
import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.models.Page;
import org.niconiconi.blog.repositories.PageRepository;
import org.niconiconi.blog.repositories.ArticleRepository;
import org.niconiconi.blog.utils.Markdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Volio on 2016/9/4.
 */
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final PageRepository pageRepository;
    private final CommentService commentService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CommentService commentService, PageRepository pageRepository) {
        this.articleRepository = articleRepository;
        this.commentService = commentService;
        this.pageRepository = pageRepository;
    }

    public org.springframework.data.domain.Page<Article> findAllByPage(int pageNum, int pageSize) {
        org.springframework.data.domain.Page<Article> articles = articleRepository.findAll(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"));
        for (Article article : articles) {
            article.setContent(Markdown.subMarkdownToHtml(article.getContent()));
        }
        return articles;
    }

    public Article findArticle(Long pid) {
        Article article = articleRepository.findArticleById(pid);
        if (article == null) {
            throw new PageNotFoundException();
        }
        return article;
    }

    public Article findHtmlArticle(Long pid) {
        Article article = findArticle(pid);
        article.setContent(Markdown.markdownToHtml(article.getContent()));
        return article;
    }

    public List<Article> searchArticles(String keyword) {
        List<Article> articles = articleRepository.findArticlesByContentContaining(keyword);
        for (Article article : articles) {
            article.setContent(Markdown.subMarkdownToHtml(article.getContent()));
        }
        return articles;
    }

    public Page findPage(String slug) {
        Page page = pageRepository.findBySlug(slug);
        if (page == null) {
            throw new PageNotFoundException();
        }
        page.setContent(Markdown.markdownToHtml(page.getContent()));
        return page;
    }

    public Long getArticleCount() {
        return articleRepository.count();
    }

    public Article save(Article article) {
        article.setCreatedAt(new Date());
        return articleRepository.save(article);
    }

    public Article update(Article article) {
        Article sArticle = articleRepository.findArticleById(article.getId());
        if(sArticle ==null){
            throw new PageNotFoundException();
        }
        sArticle.setTitle(article.getTitle());
        sArticle.setContent(article.getContent());
        return articleRepository.save(sArticle);
    }

    public void delete(Long id) {
        commentService.deleteCommentsByCid(id);
        articleRepository.delete(id);
    }
}
