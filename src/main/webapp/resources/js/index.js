/**
 * Created by Volio on 2016/9/19.
 */
function deleteArticle(articleId) {
    if(confirm('确认删除?'))
    {
        $.ajax({
            url: '/admin/articles/'+articleId,
            type: 'DELETE',
            success: function() {
                location.reload()
            },
        });
    }
}

function deleteComment(commentId) {
    if(confirm('确认删除?'))
    {
        $.ajax({
            url: '/admin/comments/'+commentId,
            type: 'DELETE',
            success: function() {
                location.reload()
            },
        });
    }
}