<template>
  <div class="content">
    <div class="wrapp">
      <div class="post" v-for="article in articles">
        <div class="top">
          <div class="title">
            <a class="p-title" rel="bookmark" v-link="'/article/' + article.id">
              <i class="fa fa-file-text-o"></i><span>{{ article.title }}</span>
            </a>
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
        <div class="bottom">
          <div class="left">
            <a class="more ripple" v-link="'/article/' + article.id"><i
              class="fa fa-caret-square-o-right"></i>阅读全文</a>
          </div>
        </div>
      </div>
      <div class="content-loading" v-show="isLoading">
        <img src="../assets/loading.gif">
      </div>
      <div class="content-loading" v-show="isNotFound">
        <p>再怎么看也没有啦 ╮(╯▽╰)╭</p>
      </div>
      <div class="page-navi" v-if="!isLoading">
        <span class="line-left line"></span>
        <div class="white">
          <li>
            <ol class="page-navigator">
              <li class="prev" v-if="!first"><a v-link="'/page/'+(number)">&laquo;</a></li>
              <li v-for="pageNum in totalPages" v-bind:class="{'current':pageNum==number}"><a
                v-link="'/page/'+(pageNum+1)">{{ pageNum+1 }}</a></li>
              <li class="next" v-if="!last"><a v-link="'/page/'+(number+2)">&raquo;</a></li>
            </ol>
          </li>
        </div>
        <span class="line-right line"></span>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        articles: [],
        first: true,
        last: true,
        totalPages: 3,
        number: 0,
        isLoading: true,
        numberOfElements: 0
      }
    },
    computed: {
      isNotFound() {
        return (this.numberOfElements==0)&&(this.isLoading==false)
      }
    },
    methods: {
      showArticles(pageNum) {
        var page = pageNum ? pageNum : 1
        this.$http.get('/api/articles?page=' + page).then((response) => {
          var data = response.body
          this.isLoading = false
          this.first = data.first
          this.last = data.last
          this.totalPages = data.totalPages
          this.number = data.number
          this.articles = data.content
          this.numberOfElements = data.numberOfElements
        }, (response) => {
          console.log(response)
        })
      },
      showLoading() {
        this.isLoading = true
        this.articles = []
      }
    },
    route: {
      data ({to: {params: {pageNum}}}) {
        this.showLoading()
        this.showArticles(pageNum)
        document.title = 'Volio\'s Blog'
      }
    }
  }
</script>
