package org.niconiconi.blog.support;

import org.niconiconi.blog.adapter.NoticeAdapter;
import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * Created by Volio on 2016/11/5.
 */
@Service
@EnableAsync
public class EmailService implements NoticeAdapter {

    private final JavaMailSender mailSender;
    private final EnvService envService;

    @Autowired
    public EmailService(JavaMailSender mailSender, EnvService envService) {
        this.mailSender = mailSender;
        this.envService = envService;
    }

    //异步发送评论通知
    @Override
    @Async
    public void sendCommentNotice(Comment comment, Article article) {
        if (envService.getNotification()) {
            String title = "\"" + comment.getAuthor() + "\" 发表了新的评论";
            String text = comment.getCoid() + ". \"" + comment.getAuthor() + "\" 在文章 \""
                    + article.getTitle() + "\"" + " 中发表了评论：\n\n" + comment.getText();
            sendText(envService.getMailTo(), title, text);
        }
    }

    private void sendText(String to, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(envService.getMailFrom());
        message.setTo(to);
        message.setSubject(title);
        message.setText(text);
        mailSender.send(message);
    }
}
