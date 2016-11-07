package org.niconiconi.blog.plugins.comment;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.EmailService;
import org.niconiconi.blog.services.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Created by Volio on 2016/11/4.
 */
@Aspect
@Component
public class Notification {

    private final EmailService emailService;
    private final EnvService envService;

    @Autowired
    public Notification(EmailService emailService, EnvService envService) {
        this.emailService = emailService;
        this.envService = envService;
    }

    @Pointcut(value = "execution(* org.niconiconi.blog.controllers.api.CommentApiController.addComment(..)) && args(comment,headers)", argNames = "comment,headers")
    public void commentAdded(Comment comment, HttpHeaders headers) {
    }

    @After(value = "commentAdded(comment,headers)", argNames = "comment,headers")
    public void notifyAuthor(Comment comment, HttpHeaders headers) {
        if (envService.getNotification()) {
            String title = "\"" + comment.getAuthor() + "\" 在您的博客发表了新的评论";
            String text = comment.getCoid() + ". \"" + comment.getAuthor() + "\" 发表了评论：" + comment.getText();
            emailService.sendText(envService.getMailTo(), title, text);
        }
    }
}
