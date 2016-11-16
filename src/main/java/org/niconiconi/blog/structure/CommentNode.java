package org.niconiconi.blog.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Volio on 2016/11/15.
 */
public class CommentNode implements Comparable<CommentNode> {

    private Long coid;

    private Date createdAt;

    private String author;

    private String avatar;

    private String url;

    private String text;

    private Long parentId;

    private List<CommentNode> commentChildren;

    public CommentNode() {
        this.commentChildren = new ArrayList<>();
    }

    public Long getCoid() {
        return coid;
    }

    public void setCoid(Long coid) {
        this.coid = coid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<CommentNode> getCommentChildren() {
        return commentChildren;
    }

    public void setCommentChildren(List<CommentNode> commentChildren) {
        this.commentChildren = commentChildren;
    }

    //查找节点
    public CommentNode findCommentNodeById(Long coid) {
        if (Objects.equals(this.coid, coid))
            return this;
        if (commentChildren.isEmpty() || commentChildren == null) {
            return null;
        } else {
            for (CommentNode child : commentChildren) {
                CommentNode resultNode = child.findCommentNodeById(coid);
                if (resultNode != null) {
                    return resultNode;
                }
            }
            return null;
        }
    }

    @Override
    public int compareTo(CommentNode commentNode) {
        return this.getCoid().compareTo(commentNode.getCoid());
    }
}
