<template>
  <li class="comment-body comment-parent comment-even" v-for="comment in comments">
    <div class="comment-author">
      <span><img class="avatar" v-bind:src="comment.avatar" width="32" height="32"></span>
      <cite class="fn" itemprop="name"><a href="{{ comment.url }}" rel="external nofollow">{{ comment.author
        }}</a></cite>
    </div>
    <div class="comment-meta">
      <a>
        <time>{{ new Date(comment.createdAt).toLocaleString() }}</time>
      </a>
    </div>
    <div class="comment-content">
      <p>{{ comment.text }}</p>
    </div>
    <div class="comment-reply">
      <a v-on:click="reply(comment.coid)" v-if="commentDepth<5">回复</a>
    </div>
    <div class="comment-children" v-if="comment.commentChildren">
      <ol class="comment-list">
        <comment-node :comments="comment.commentChildren" :comment-depth="commentDepth+1" :reply="reply"></comment-node>
      </ol>
    </div>
  </li>
</template>

<script>
  export default {
    name: 'comment-node',
    props: ['comments','commentDepth','reply']
  }
</script>
