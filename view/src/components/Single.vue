<template>
  <div class="content">
    <div class="float-nav">
      <ul class="ex-menu">
        <li>
          <a onclick="goToTop();">回到顶部</a>
          <i class="fa fa-arrow-up"></i>
        </li>
      </ul>
      <a class="top" title="发表评论">
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
            </div>
          </div>
        </div>
        <div class="entry">{{{ article.content }}}</div>
      </div>
      <div class="content-loading" v-show="isLoading">
        <img src="../assets/loading.gif">
      </div>
      <div class="comments-wrapp">
        <div class="topbar">
          <div class="wrapp">
            <div class="left">
              <li class="num"><a>评论已关闭</a></li>
            </div>
          </div>
        </div>

        <ol class="comment-main">
          <div class="none">暂无评论，快来抢沙发吧！</div>
        </ol>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        article: {},
        isLoading: true
      }
    },
    methods: {
      showArticle(slug) {
        this.$http.get('/api/pages?slug=' + slug).then((response) => {
          var data = response.body
          this.isLoading = false
          this.article = data
          document.title = data.title
        }, (response) => {
          console.log(response)
        })
      },
      showLoading() {
        this.isLoading = true
        this.article = {}
      }
    },
    route: {
      data () {
        var slug = this.$route.name
        this.showLoading()
        this.showArticle(slug)
      }
    }
  }
</script>
