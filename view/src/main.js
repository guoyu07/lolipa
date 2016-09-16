import Vue from 'vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import Articles from './components/Articles'
import Article from './components/Article'
import Single from './components/Single'
import Search from './components/Search'

Vue.use(VueResource)
Vue.use(VueRouter)

var router = new VueRouter()
var App = Vue.extend({
  data() {
    return {
      keyword: ''
    }
  },
  methods: {
    search() {
      router.go({ name: 'search', params: { keyword: this.keyword }})
    }
  }
})

router.map({
  '/home': {
    component: Articles
  },
  '/page/:pageNum': {
    component: Articles
  },
  '/article/:articleId': {
    component: Article
  },
  '/search/:keyword': {
    name: 'search',
    component: Search
  },
  '/links': {
    name: 'links',
    component: Single
  },
  '/about': {
    name: 'about',
    component: Single
  }
})

router.redirect({
  '*': "/home"
});

router.start(App, 'body')
