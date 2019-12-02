<template>
  <div class="article-editor-container">
    <el-form>
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          Publish
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          Draft
        </el-button>
      </sticky>
      <el-form-item style="margin-bottom: 40px;">
        <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
          Title
        </MDinput>
      </el-form-item>
      <div ref="editor" class="editor-section" />
    </el-form>
  </div>
</template>

<script>

import Editor from 'tui-editor'
import 'tui-editor/dist/tui-editor-extScrollSync.js'
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件

import { get, publish, draft } from '@/api/article'

import 'codemirror/lib/codemirror.css'
import 'tui-editor/dist/tui-editor.css'
import 'tui-editor/dist/tui-editor-contents.css'
import 'highlight.js/styles/github.css'

export default {
  name: 'ArticleEditor',
  components: { Sticky, MDinput },
  props: {
    isNew: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      postForm: {
        title: '',
        status: 'draft',
        html: '',
        markdown: ''
      },
      editor: undefined,
      loading: false
    }
  },
  created() {
    if (!this.isNew) {
      // 编辑文章
      const id = this.$route.params && this.$route.params.id
      console.log('id:' + id)
      this.fetchData(id)
    }
  },
  mounted() {
    this.editor = new Editor({
      el: this.$refs.editor,
      initialEditType: 'markdown',
      previewStyle: 'vertical',
      height: '600px',
      exts: ['scrollSync'],
      usageStatistics: false
    })
  },
  methods: {
    fetchData(id) {
      get(id).then(response => {
        let data = response.data
        console.log(data)
        this.postForm.title = data.title
        this.editor.setMarkdown(data.markdown)
      }).catch(err => {
        console.log(err)
      })
    },
    submitForm() {
      this.loading = true
      this.mergeData()
      console.log(this.postForm)
      publish(this.postForm).then(response => {
        console.log(response)
        this.loading = false
      }).catch(err => {
        console.log(err)
        this.loading = false
      })
    },
    draftForm() {
      this.loading = true
      this.mergeData()
      draft(this.postForm).then(response => {
        console.log(response)
        this.loading = false
      }).catch(err => {
        console.log(err)
        this.loading = false
      })
    },
    mergeData() {
      this.postForm.html = this.editor.getHtml()
      this.postForm.markdown = this.editor.getMarkdown()
    }
  }
}
</script>

<style lang="scss">
  .article-editor-container{
    padding: 30px;
  }
  .sub-navbar {
    height: 50px;
    line-height: 50px;
    position: relative;
    width: 100%;
    text-align: right;
    padding-right: 20px;
    transition: 600ms ease position;
    background: linear-gradient(90deg, rgba(32, 182, 249, 1) 0%, rgba(32, 182, 249, 1) 0%, rgba(33, 120, 241, 1) 100%, rgba(33, 120, 241, 1) 100%);

    .subtitle {
      font-size: 20px;
      color: #fff;
    }

    &.draft {
      background: #d0d0d0;
    }

    &.deleted {
      background: #d0d0d0;
    }
  }
</style>
