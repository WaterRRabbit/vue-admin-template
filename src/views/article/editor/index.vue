<template>
  <div class="article-editor-container">
    <div ref="editor" class="editor-section" />
  </div>
</template>

<script>

import Editor from 'tui-editor'
import 'tui-editor/dist/tui-editor-extScrollSync.js'

import 'codemirror/lib/codemirror.css'
import 'tui-editor/dist/tui-editor.css'
import 'tui-editor/dist/tui-editor-contents.css'
import 'highlight.js/styles/github.css'

export default {
  name: 'ArticleEditor',
  data() {
    return {
      editor: undefined
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

  }
}
</script>

<style lang="scss">
  .article-editor-container{
    padding: 30px;
  }
</style>
