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

    private final CommentService commentService;

    @Autowired
    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getComments(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        Page<Comment> comments = commentService.findAllCommentsByPage(pageNum, 10);
        Map<Long, String> avatarMap = getAvatars(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("avatarMap", avatarMap);

        if (pageNum > 0) {
            model.addAttribute("prePage", true);
            model.addAttribute("prePageNum", pageNum);
        } else {
            model.addAttribute("prePage", false);
        }

        if ((pageNum + 1) < comments.getTotalPages()) {
            model.addAttribute("nextPage", true);
            model.addAttribute("nextPageNum", pageNum + 2);
        }else {
            model.addAttribute("nextPage", false);
        }

        model.addAttribute("title", "管理评论");
        return "admin/comment/list";
    }

    @RequestMapping(value = "approve", method = RequestMethod.GET)
    public String getUnApproveComments(Model model) {
        List<Comment> comments = commentService.findWaitingComments();
        Map<Long, String> avatarMap = getAvatars(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("avatarMap", avatarMap);
        model.addAttribute("prePage", false);
        model.addAttribute("nextPage", false);
        model.addAttribute("title", "审核评论");
        return "admin/comment/list";
    }

    @RequestMapping(value = "/{coid}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteComment(@PathVariable("coid") Long coid) {
        commentService.delete(coid);
    }

    @RequestMapping(value = "/{coid}", method = RequestMethod.PUT)
    @ResponseBody
    public Comment approveComment(@PathVariable("coid") Long coid) {
        return commentService.approveComment(coid);
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
