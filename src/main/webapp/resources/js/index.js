/**
 * Created by Volio on 2016/9/19.
 */
function deleteArticle(articleId) {
    if (confirm('确认删除?')) {
        $.ajax({
            url: '/admin/articles/' + articleId,
            type: 'DELETE',
            success: function () {
                location.reload()
            }
        })
    }
}

function deleteComment(commentId) {
    if (confirm('确认删除?')) {
        $.ajax({
            url: '/admin/comments/' + commentId,
            type: 'DELETE',
            success: function () {
                location.reload()
            }
        })
    }
}

function approveComment(commentId) {
    $.ajax({
        url: '/admin/comments/' + commentId,
        type: 'PUT',
        success: function () {
            location.reload()
        }
    })
}

function editArticle(articleId) {
    var data = {}
    $("#article-form").serializeArray().map(function (kv) {
        data[kv.name] = kv.value;
    })
    var json = JSON.stringify(data)
    $.ajax({
        url: '/admin/articles/' + articleId,
        type: 'PUT',
        data: json,
        contentType: "application/json",
        success: function () {
            alert('提交成功')
            location = '/admin/articles'
        },
        error: function () {
            alert('提交失败，请检查参数')
        }
    })
}

function addArticle() {
    var data = {}
    $("#article-form").serializeArray().map(function (kv) {
        data[kv.name] = kv.value;
    })
    var json = JSON.stringify(data)
    $.ajax({
        url: '/admin/articles',
        type: 'POST',
        data: json,
        contentType: "application/json",
        success: function () {
            alert('提交成功')
            location = '/admin/articles'
        },
        error: function () {
            alert('提交失败，请检查参数')
        }
    })
}