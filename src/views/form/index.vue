<template>
  <div class="coder-container">
    <div ref="left" id="left">
      <el-container class="editor-container">
        <el-header>
          
        </el-header>
        <el-main class="editor-main-container left">
          
        </el-main>
        <el-footer>
          
        </el-footer>
      </el-container>
    </div>
    <div ref="middle" id="middle" v-on:mousedown="mousedown"></div>
    <div ref="right" id="right">
      <el-container class="editor-container">
        <el-header>
          <div class="operation-container">
            <el-row>
              <el-switch
                v-model="theme"
                class="theme-switch"
                active-color="#000"
                active-text="黑夜"
              />
            </el-row>
          </div>
        </el-header>
        <el-main class="editor-main-container right">
          <el-container>
            <el-main>
              <textarea ref="codeEditor" v-model="code" class="code-editor" />
            </el-main>
            <el-footer class="console-container">
              
            </el-footer>
          </el-container>
        </el-main>
        <el-footer class="editor-footer-container">
          <el-button>默认按钮</el-button>
          <el-button>默认按钮</el-button>
        </el-footer>
      </el-container>
    </div>
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
    // this.editor.setSize('', '500px')
    this.editor.on('onchange', function() {
      this.editor.showHint()
    })

    
  },
  methods: {
    run() {
      console.log(this.editor.getValue())
    },
    mousedown(e) {
      const left = this.$refs.left
      const middle = this.$refs.middle
      const right = this.$refs.right
      var middleWidth=9;
      var disX = (e || event).clientX
      middle.left = middle.offsetLeft
      document.onmousemove = function(e) {
        var iT = middle.left + ((e || event).clientX - disX)
        var e = e || window.event,tarnameb = e.target || e.srcElement
        var maxT = document.body.clientWidth
        iT < 0 && (iT = 0)
        iT > maxT && (iT = maxT)
        if(iT > 1000) {
          return false
        }
        middle.style.left = left.style.width = iT + 'px'
        right.style.left = iT + middleWidth + 'px'
        return false
			}
      document.onmouseup = function() {
        document.onmousemove = null
        document.onmouseup = null
        middle.releaseCapture && middle.releaseCapture()
      }
      middle.setCapture && middle.setCapture()
      return false
    }
  }
}
</script>

<style>
.operation-container{
  margin-bottom: 10px;
}
.line{
  text-align: center;
}
.CodeMirror{
  border: 1px solid #DCDFE6;
  position: relative;
  height: 100%;
}
.CodeMirror-code {
  font-family: Menlo, Monaco, Consolas, "Courier New", monospace;
  font-size: 19px;
}
.CodeMirror-gutters{
  background-color: #fff;
  border-right: 0;
}
.theme-switch{
  float: right;
  top: 7px;
}
.info-container{
  width: 100px;
  height: 200px;
  border: 1px;
  background-color: blueviolet;
}
#left,#right,#middle{
  height:99%;
  position:absolute;
}
#left{
  width:400px;
  background:rgb(252, 252, 252);
}
#middle{
  width:9px;
  background:rgb(252, 252, 252);
  left:400px;
}
#middle:hover{
  cursor:w-resize;
  background-color: rgb(231, 231, 231);
}
#right{
  right:0;
  background:rgb(252, 252, 252);
  left:409px; 
  width:auto;
}
.el-header, .el-main, .el-footer{
  padding: 0;
}
.el-container{
  height: 100%;
}
.editor-main-container.right{
  padding-left: 0;
  padding-right: 20px;
  padding-top: 0;
  padding-bottom: 0;
}
.editor-main-container.left{
  padding-left: 20px;
  padding-right: 0;
  padding-top: 0;
  padding-bottom: 0;
  background-color: #fff;
  border: 1px solid #DCDFE6;
}
.editor-footer-container{
  padding-top: 10px;
  text-align: right;
}
.console-container{
  background-color: #fff;
  border: 1px solid #DCDFE6;
  min-height: 150px;
}
</style>
