package org.niconiconi.blog.utils;

import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.structure.CommentNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Volio on 2016/11/15.
 */
public class CommentUtil {

    //构建评论多叉树列表结构
    public static List<CommentNode> buildCommentsTree(List<Comment> comments, boolean isDesc) {
        List<CommentNode> commentTreeList = new ArrayList<>();
        List<CommentNode> nodeList = commentsToCommentNodeList(comments);
        //排序
        sortComments(nodeList);
        for (CommentNode commentNode : nodeList) {
            if (commentNode.getParentId() == 0)
                commentTreeList.add(commentNode);
            else
                insertToCommentTree(commentTreeList, commentNode);
        }
        //是否降序
        if (isDesc)
            sortCommentsDesc(commentTreeList);

        return commentTreeList;
    }

    //向多叉树列表中插入节点
    private static void insertToCommentTree(List<CommentNode> commentTreeList, CommentNode children) {
        for (CommentNode commentNode : commentTreeList) {
            CommentNode parentNode = commentNode.findCommentNodeById(children.getParentId());
            if (parentNode != null) {
                parentNode.getCommentChildren().add(children);
                break;
            }
        }
    }

    //评论升序排序
    private static void sortComments(List<CommentNode> commentNodes) {
        Collections.sort(commentNodes);
    }

    //评论降序排序
    private static void sortCommentsDesc(List<CommentNode> commentNodes) {
        Collections.sort(commentNodes, (o1, o2) -> o2.compareTo(o1));
    }

    //评论List转换
    private static List<CommentNode> commentsToCommentNodeList(List<Comment> comments) {
        List<CommentNode> nodeList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentNode commentNode = new CommentNode();
            commentNode.setCoid(comment.getCoid());
            commentNode.setCreatedAt(comment.getCreatedAt());
            commentNode.setAuthor(comment.getAuthor());
            String mailMD5 = Encode.string2MD5(comment.getMail());
            String avatar = "https://cdn.v2ex.com/gravatar/" + mailMD5 + "?s=32&r=G&d=mm";
            commentNode.setAvatar(avatar);
            commentNode.setUrl(comment.getUrl());
            commentNode.setText(comment.getText());
            commentNode.setParentId(comment.getParentId());
            nodeList.add(commentNode);
        }

        return nodeList;
    }
}
