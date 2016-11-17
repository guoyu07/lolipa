<template>
  <div class="comments-wrapp">
    <div class="topbar">
      <div class="wrapp">
        <div class="left">
          <li class="num"><a>{{'评论 (' + commentNum + ' 条)' }}</a></li>
          <li class="write"><a class="reShow" onclick="showReplyForm()"><i
            class="fa fa-pencil-square-o"></i>发表评论</a></li>
        </div>
      </div>
    </div>

    <ol class="comment-main">
      <div class="none" v-if="commentNum==0">暂无评论，快来抢沙发吧！</div>
      <ol class="comment-list">
        <comment-node :comments="comments" :comment-depth="commentDepth" :reply="reply"></comment-node>
      </ol>
    </ol>
    <div class="respond-bk">
      <div class="respond-main">
        <span class="nws-line"></span>
        <div class="subscribe-bg"></div>
        <div class="smilelink">
          <div class="faceIt wrapp wrapp-active">
            <a onclick="javascript:grin('|∀ﾟ')">|∀ﾟ</a>
            <a onclick="javascript:grin('(´ﾟДﾟ`)')">(´ﾟДﾟ`)</a>
            <a onclick="javascript:grin('(;´Д`)')">(;´Д`)</a>
            <a onclick="javascript:grin('(｀･ω･)')">(｀･ω･)</a>
            <a onclick="javascript:grin('(=ﾟωﾟ)=')">(=ﾟωﾟ)=</a>
            <a onclick="javascript:grin('| ω・´)')">| ω・´)</a>
            <a onclick="javascript:grin('|-` )')">|-` )</a>
            <a onclick="javascript:grin('(´ﾟДﾟ`)')">(´ﾟДﾟ`)</a>
            <a onclick="javascript:grin('|д` )')">|д` )</a>
            <a onclick="javascript:grin('|ー` )')">|ー` )</a>
            <a onclick="javascript:grin('|∀` )')">|∀` )</a>
            <a onclick="javascript:grin('(つд⊂)')">(つд⊂)</a>
            <a onclick="javascript:grin('(ﾟДﾟ≡ﾟДﾟ)')">(ﾟДﾟ≡ﾟДﾟ)</a>
            <a onclick="javascript:grin('(＾o＾)ﾉ')">(＾o＾)ﾉ</a>
            <a onclick="javascript:grin('(|||ﾟДﾟ)')">(|||ﾟДﾟ)</a>
            <a onclick="javascript:grin('( ﾟ∀ﾟ)')">( ﾟ∀ﾟ)</a>
            <a onclick="javascript:grin('( ´∀`)')">( ´∀`)</a>
            <a onclick="javascript:grin('(*´∀`)')">(*´∀`)</a>
            <a onclick="javascript:grin('(*ﾟ∇ﾟ)')">(*ﾟ∇ﾟ)</a>
            <a onclick="javascript:grin('(*ﾟーﾟ)')">(*ﾟーﾟ)</a>
            <a onclick="javascript:grin('(　ﾟ 3ﾟ)')">(　ﾟ 3ﾟ)</a>
            <a onclick="javascript:grin('( ´ー`)')">( ´ー`)</a>
            <a onclick="javascript:grin('( ・_ゝ・)')">( ・_ゝ・)</a>
            <a onclick="javascript:grin('( ´_ゝ`)')">( ´_ゝ`)</a>
            <a onclick="javascript:grin('(*´д`)')">(*´д`)</a>
            <a onclick="javascript:grin('(・ー・)')">(・ー・)</a>
            <a onclick="javascript:grin('(・∀・)')">(・∀・)</a>
            <a onclick="javascript:grin('(ゝ∀･)')">(ゝ∀･)</a>
            <a onclick="javascript:grin('(〃∀〃)')">(〃∀〃)</a>
            <a onclick="javascript:grin('(*ﾟ∀ﾟ*)')">(*ﾟ∀ﾟ*)</a>
            <a onclick="javascript:grin('( ﾟ∀。)')">( ﾟ∀。)</a>
            <a onclick="javascript:grin('( `д´)')">( `д´)</a>
            <a onclick="javascript:grin('(`ε´ )')">(`ε´ )</a>
            <a onclick="javascript:grin('(`ヮ´ )')">(`ヮ´ )</a>
            <a onclick="javascript:grin('σ`∀´)')">σ`∀´)</a>
            <a onclick="javascript:grin('ﾟ∀ﾟ)σ')">ﾟ∀ﾟ)σ</a>
            <a onclick="javascript:grin('ﾟ ∀ﾟ)')">ﾟ ∀ﾟ)</a>
            <a onclick="javascript:grin('(╬ﾟдﾟ)')">(╬ﾟдﾟ)</a>
            <a onclick="javascript:grin('(|||ﾟдﾟ)')">(|||ﾟдﾟ)</a>
            <a onclick="javascript:grin('( ﾟдﾟ)')">( ﾟдﾟ)</a>
            <a onclick="javascript:grin('Σ( ﾟдﾟ)')">Σ( ﾟдﾟ)</a>
            <a onclick="javascript:grin('( ;ﾟдﾟ)')">( ;ﾟдﾟ)</a>
            <a onclick="javascript:grin('( ;´д`)')">( ;´д`)</a>
            <a onclick="javascript:grin('(　д ) ﾟ ﾟ')">(　д ) ﾟ ﾟ</a>
            <a onclick="javascript:grin('( ☉д⊙)')">( ☉д⊙)</a>
            <a onclick="javascript:grin('(((　ﾟдﾟ)))')">(((　ﾟдﾟ)))</a>
            <a onclick="javascript:grin('( ` ・´)')">( ` ・´)</a>
            <a onclick="javascript:grin('( ´д`)')">( ´д`)</a>
            <a onclick="javascript:grin('( -д-)')">( -д-)</a>
            <a onclick="javascript:grin('(>д<)')">(&gt;д&lt;)</a>
            <a onclick="javascript:grin('･ﾟ( ﾉд`ﾟ)')">･ﾟ( ﾉд`ﾟ)</a>
            <a onclick="javascript:grin('( TдT)')">( TдT)</a>
            <a onclick="javascript:grin('(￣∇￣)')">(￣∇￣)</a>
            <a onclick="javascript:grin('(￣3￣)')">(￣3￣)</a>
            <a onclick="javascript:grin('(￣ｰ￣)')">(￣ｰ￣)</a>
            <a onclick="javascript:grin('(￣ . ￣)')">(￣ . ￣)</a>
            <a onclick="javascript:grin('(￣皿￣)')">(￣皿￣)</a>
            <a onclick="javascript:grin('(￣艸￣)')">(￣艸￣)</a>
            <a onclick="javascript:grin('(￣︿￣)')">(￣︿￣)</a>
            <a onclick="javascript:grin('(￣︶￣)')">(￣︶￣)</a>
            <a onclick="javascript:grin('ヾ(´ωﾟ｀)')">ヾ(´ωﾟ｀)</a>
            <a onclick="javascript:grin('(*´ω`*)')">(*´ω`*)</a>
            <a onclick="javascript:grin('(・ω・)')">(・ω・)</a>
            <a onclick="javascript:grin('( ´・ω)')">( ´・ω)</a>
            <a onclick="javascript:grin('(｀・ω)')">(｀・ω)</a>
            <a onclick="javascript:grin('(´・ω・`)')">(´・ω・`)</a>
            <a onclick="javascript:grin('(`・ω・´)')">(`・ω・´)</a>
            <a onclick="javascript:grin('( `_っ´)')">( `_っ´)</a>
            <a onclick="javascript:grin('( `ー´)')">( `ー´)</a>
            <a onclick="javascript:grin('>( ´_っ`)')">&gt;( ´_っ`)</a>
            <a onclick="javascript:grin('( ´ρ`)')">( ´ρ`)</a>
            <a onclick="javascript:grin('( ﾟωﾟ)')">( ﾟωﾟ)</a>
            <a onclick="javascript:grin('(oﾟωﾟo)')">(oﾟωﾟo)</a>
            <a onclick="javascript:grin('(　^ω^)')">(　^ω^)</a>
            <a onclick="javascript:grin('(｡◕∀◕｡)')">(｡◕∀◕｡)</a>
            <a onclick="javascript:grin('ヾ(´ε`ヾ)')">ヾ(´ε`ヾ)</a>
            <a onclick="javascript:grin('(ノﾟ∀ﾟ)')">(ノﾟ∀ﾟ)</a>
            <a onclick="javascript:grin('(σﾟдﾟ)σ')">(σﾟдﾟ)σ</a>
          </div>
          <div class="sm-bottom">
            <a class="faceItemLnk current" onclick="faceItem()">表情</a>
            <a class="faceCloseLnk" onclick="closeFaceSelect()">关闭</a>
          </div>
        </div>
        <div id="response-post" class="comment-respond">
          <h3 id="reply-title" class="comment-reply-title">发表评论</h3>
          <form id="comment-form" class="comment-form">
            <p class="comment-form-field comment-form-author">
              <input name="cid" value="14" type="hidden">
              <input id="author" type="text" placeholder="昵称" value="" size="10" v-model="cAuthor">
            </p>
            <p class="comment-form-field comment-form-email">
              <input id="mail" type="email" placeholder="邮箱" value="" v-model="cMail">
            </p>
            <p class="comment-form-field comment-form-url">
              <input id="url" type="url" placeholder="网站" value="http://" v-model="cUrl">
            </p>
            <p class="comment-form-comment">
              <textarea id="textarea" aria-required="true"
                        placeholder="说点什么…" v-model="cText"></textarea>
            </p>
            <p class="form-toolbar">
              <a onclick="showFaceSelect()" class="addSmile" title="表情"><i
                class="fa fa-smile-o"></i></a>
            </p>
            <p class="form-btm-cancel">
              <input type="button" v-on:click="cancelReply" id="form-bottom-cancel"
                     class="cancel" value="取消评论" input="">
            </p>
            <p class="form-submit">
              <input name="submit" type="button" id="submit" value="发表评论" v-on:click="submitComment">
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import CommentNode from './CommentNode'

  export default {
    data() {
      return {
        cAuthor: '',
        cMail: '',
        cUrl: '',
        cText: '',
        cReplyTo: 0,
        commentDepth: 1
      }
    },
    props: ['commentNum', 'comments', 'articleId'],
    methods: {
      reply(coid) {
        $('.respond-bk').addClass('is-on')
        respondResize()
        this.cReplyTo = coid
      },
      cancelReply() {
        hiddenReplyForm()
        this.cReplyTo = 0
      },
      submitComment() {
        let commentData = {
          cid: this.articleId,
          author: this.cAuthor,
          mail: this.cMail,
          url: this.cUrl,
          text: this.cText,
          parentId: this.cReplyTo
        };
        document.getElementById('submit').value = "提交中..."
        this.$http.post('/api/comments', commentData).then((response) => {
          hiddenReplyForm()
          document.getElementById('submit').value = "发表评论"
          const data = response.body;
          data.author = data.author + ' (您的评论正在等待管理员审核)'
          let node;
          for (let i = 0; i < this.comments.length; i++) {
            node = this.getCommentNode(data, this.comments[i])
            if (node != null) {
              node.commentChildren.push(data)
              break;
            }
          }
        }, () => {
          document.getElementById('submit').value = "发表评论（您提交的参数有误）"
        });
      },
      getCommentNode(comment, tree) {
        if (tree.coid == comment.parentId) {
          return tree
        } else {
            let commentNode
          for (let i = 0; i < tree.commentChildren.length; i++) {
            commentNode = this.getCommentNode(comment, tree.commentChildren[i])
          }
          return commentNode
        }
      }
    },
    components: {CommentNode}
  }
</script>
