package org.niconiconi.blog.controllers.admin;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.services.CommentService;
import org.niconiconi.blog.utils.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Volio on 2016/9/11.
 */
@Controller
@RequestMapping("/admin/comments")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getComments(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        Page<Comment> comments = commentService.findAllCommentsByPage(pageNum, 10);
        Map<Long, String> avatarMap = getAvatars(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("avatarMap", avatarMap);
        model.addAttribute("allPageNum", comments.getTotalPages());
        model.addAttribute("pageNum", pageNum + 1);
        return "admin/comment/list";
    }

    @RequestMapping(value = "waiting", method = RequestMethod.GET)
    public String getComments(Model model) {
        List<Comment> comments = commentService.findWaitingComments();
        Map<Long, String> avatarMap = getAvatars(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("avatarMap", avatarMap);
        model.addAttribute("allPageNum", 1);
        model.addAttribute("pageNum", 1);
        return "admin/comment/list";
    }

    @RequestMapping(value = "/{coid}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteComment(@PathVariable("coid") Long coid) {
        commentService.delete(coid);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public String approveComment(@RequestParam(value = "coid") Long coid) {
        commentService.approveComment(coid);
        return "redirect:/admin/comment/waiting";
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
