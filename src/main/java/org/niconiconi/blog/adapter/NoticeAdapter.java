package org.niconiconi.blog.adapter;

import org.niconiconi.blog.models.Article;
import org.niconiconi.blog.models.Comment;

/**
 * Created by Volio on 2017/1/16.
 */
public interface NoticeAdapter {

    void sendCommentNotice(Comment comment, Article article);
}
