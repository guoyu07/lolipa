<template>
  <div class="content">
    <div class="float-nav">
      <ul class="ex-menu">
        <li>
          <a onclick="goToTop();">回到顶部</a>
          <i class="fa fa-arrow-up"></i>
        </li>
      </ul>
      <a class="top" title="发表评论" onclick="showReplyForm();">
        <i class="fa fa-comments"></i>
      </a>
    </div>
    <div class="wrapp">
      <div class="page" v-show="!isLoading">
        <div class="top">
          <div class="title">
            <a class="p-title" rel="bookmark">
              <i class="fa fa-file-text-o"></i><span>{{ article.title }}</span></a>
          </div>
          <div class="info">
            <div class="white">
              <span class="date">{{ new Date(article.createdAt).toLocaleString() }}</span>
              <span class="dot"> - </span>
              <span class="comment">{{ article.commentNum + ' 条评论' }}</span>
            </div>
          </div>
        </div>
        <div class="entry">{{{ article.content }}}</div>
      </div>
      <div class="content-loading" v-show="isLoading">
        <img src="../assets/loading.gif">
      </div>
      <comment :comment-num="article.commentNum" :comments="comments" :article-id="articleId"></comment>
    </div>
  </div>
</template>

<script>
  import Comment from './Comment'

  export default {
    data() {
      return {
        article: {},
        comments: [],
        isLoading: true,
        articleId: null
      }
    },
    methods: {
      showArticles(articleId) {
        this.$http.get('/api/articles/' + articleId).then((response) => {
          var data = response.body
          this.isLoading = false
          this.article = data
          document.title = data.title
        }, (response) => {
          console.log(response)
        })
      },
      showComments(articleId) {
        this.$http.get('/api/articles/' + articleId + '/comments').then((response) => {
          this.comments = response.body
        }, (response) => {
          console.log(response)
        })
      },
      showLoading() {
        this.isLoading = true
        this.article = {}
        this.comments = []
      }
    },
    route: {
      data ({ to: { params: { articleId }}}) {
        this.showLoading()
        this.showArticles(articleId)
        this.showComments(articleId)
        this.articleId = articleId
      }
    },
    components: {Comment}
  }
</script>
