/**
 * Created by Volio on 2016/9/19.
 */
var article = {
    add: function () {
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
    },
    edit: function (articleId) {
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
    },
    delete: function (articleId) {
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
}

var comment = {
    approve: function (commentId) {
        $.ajax({
            url: '/admin/comments/' + commentId,
            type: 'PUT',
            success: function () {
                location.reload()
            }
        })
    },
    delete: function (commentId) {
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
}

function editPage(pageSlug) {
    var data = {}
    $("#page-form").serializeArray().map(function (kv) {
        data[kv.name] = kv.value;
    })
    var json = JSON.stringify(data)
    $.ajax({
        url: '/admin/pages/' + pageSlug,
        type: 'PUT',
        data: json,
        contentType: "application/json",
        success: function () {
            alert('提交成功')
            location = '/admin/settings'
        },
        error: function () {
            alert('提交失败，请检查参数')
        }
    })
}

function updateProfile() {
    var data = {}
    $("#setting-profile").serializeArray().map(function (kv) {
        data[kv.name] = kv.value;
    })
    var json = JSON.stringify(data)
    $.ajax({
        url: '/admin/settings/profile',
        type: 'PUT',
        data: json,
        contentType: "application/json",
        success: function () {
            alert('修改成功，下次登录后生效')
            location.reload()
        },
        error: function () {
            alert('提交失败，请检查参数')
        }
    })
}