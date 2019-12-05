<template>
  <div class="coder-container">
    <div class="operation-container">
      <el-row>
        <el-button icon="el-icon-search" circle @click="run" />
        <el-switch
          v-model="theme"
          class="theme-switch"
          active-color="#000"
          active-text="黑夜"
        />
      </el-row>
    </div>
    <textarea ref="codeEditor" v-model="code" class="code-editor" />
  </div>
</template>

<script>
import 'codemirror/theme/monokai.css'
import 'codemirror/theme/eclipse.css'
import 'codemirror/lib/codemirror.css'
import 'codemirror/addon/hint/show-hint.css'

const CodeMirror = require('codemirror/lib/codemirror')
require('codemirror/addon/edit/matchbrackets')
require('codemirror/addon/selection/active-line')
require('codemirror/mode/clike/clike')
require('codemirror/addon/hint/show-hint')

export default {
  data() {
    return {
      options: [{
        value: 'monokai',
        label: '默认'
      }, {
        value: 'eclipse',
        label: '黑夜'
      }],
      code: '',
      editor: undefined,
      theme: false
    }
  },
  watch: {
    theme() {
      if (this.theme) {
        this.editor.setOption('theme', 'monokai')
      } else {
        this.editor.setOption('theme', 'eclipse')
      }
    }
  },
  mounted() {
    this.editor = CodeMirror.fromTextArea(this.$refs.codeEditor, {
      mode: 'text/x-java',
      indentWithTabs: true,
      smartIndent: true,
      lineNumbers: true,
      matchBrackets: true,
      theme: 'eclipse',
      tabSize: 4,
      indentUnit: 4,
      // autofocus: true,
      extraKeys: { 'Ctrl': 'autocomplete' }, // 自定义快捷键
      hintOptions: { // 自定义提示选项
        tables: {
          users: ['name', 'score', 'birthDate'],
          countries: ['name', 'population', 'size']
        }
      }
    })
    this.editor.setSize('', '500px')
    this.editor.on('onchange', function() {
      this.editor.showHint()
    })
  },
  methods: {
    run() {
      console.log(this.editor.getValue())
    }
  }
}
</script>

<style>
.coder-container{
  padding: 30px;
}
.operation-container{
  margin-bottom: 10px;
}
.line{
  text-align: center;
}
.CodeMirror{
  border: 1px solid #DCDFE6;
  border-radius: 5px;
}
.CodeMirror-code {
  font-family: Menlo, Monaco, Consolas, "Courier New", monospace;
  font-size: 19px;
}
.theme-switch{
  float: right;
  top: 20px;
}
</style>

