package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.CommentService;
import org.niconiconi.blog.utils.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Volio on 2016/9/11.
 */
@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getComments(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model, Principal principal) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        Page<Comment> comments = commentService.findAllCommentsByPage(pageNum, 20);
        Map<Long, String> avatarMap = getAvatars(comments);
        String username = principal.getName();
        model.addAttribute("comments", comments);
        model.addAttribute("avatarMap", avatarMap);
        model.addAttribute("username", username);
        return "admin/comment/list";
    }

    private Map<Long, String> getAvatars(Iterable<Comment> comments) {
        Map<Long, String> avatarMap = new HashMap<>();
        for (Comment comment : comments) {
            String mailMD5 = Encode.string2MD5(comment.getMail());
            String avatarUrl = "https://cdn.v2ex.com/gravatar/" + mailMD5 + "?s=32&r=G&d=mm";
            avatarMap.put(comment.getCoid(), avatarUrl);
        }
        return avatarMap;
    }
}
