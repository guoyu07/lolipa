package org.niconiconi.blog.controllers;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.models.Post;
import org.niconiconi.blog.services.CommentService;
import org.niconiconi.blog.services.PostService;
import org.niconiconi.blog.utils.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Volio on 2016/9/5.
 */
@Controller
@RequestMapping("/archives")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public String getPost(@PathVariable("slug") String slug, Model model) {
        Post post = postService.findPost(slug);
        List<Comment> comments = commentService.findApprovedCommentsByCid(post.getId());
        List<Comment> avatarComments = getAvatarComments(comments);
        model.addAttribute("post", post);
        model.addAttribute("comments", avatarComments);
        return "post/index";
    }

    private List<Comment> getAvatarComments(List<Comment> comments) {
        for (Comment comment : comments) {
            String mailMD5 = Encode.string2MD5(comment.getMail());
            String avatarUrl = "https://cdn.v2ex.com/gravatar/" + mailMD5 + "?s=32&r=G&d=mm";
            comment.setMail(avatarUrl);
        }
        return comments;
    }
}
