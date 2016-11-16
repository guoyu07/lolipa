import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.niconiconi.blog.models.Comment;
import org.niconiconi.blog.structure.CommentNode;
import org.niconiconi.blog.utils.CommentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volio on 2016/11/15.
 */
public class CommentTreeTest {

    private List<Comment> comments;

    @Before
    public void setUp() throws Exception {
        this.comments = new ArrayList<>();
        Comment parentComment = new Comment();
        parentComment.setCoid((long) 1);
        parentComment.setParentId((long) 0);
        parentComment.setMail("demo@demo.com");
        comments.add(parentComment);
        Comment childComment1 = new Comment();
        childComment1.setCoid((long) 3);
        childComment1.setParentId((long) 2);
        childComment1.setMail("demo@demo.com");
        comments.add(childComment1);
        Comment childComment2 = new Comment();
        childComment2.setCoid((long) 2);
        childComment2.setParentId((long) 1);
        childComment2.setMail("demo@demo.com");
        comments.add(childComment2);
    }

    @Test
    public void buildTree() {
        List<CommentNode> commentNodeList = CommentUtil.buildCommentsTree(comments, false);
        Assert.assertEquals(1, commentNodeList.get(0).getCommentChildren().size());
        Assert.assertEquals(1, commentNodeList.get(0).getCommentChildren().get(0).getCommentChildren().size());
    }
}
