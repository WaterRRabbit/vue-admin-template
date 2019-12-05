<template>
  <div class="article-list-container">
    <el-button @click="go">Go</el-button>
  </div>
</template>

<script>
import ArticleCard from './components/ArticleCard'
export default {
  name: 'ArticleList',
  components: { ArticleCard },
  data() {
    return {
      articles: [
        {
          id: 1,
          title: '第一个Vue'
        },
        {
          id: 2,
          title: '第二个Vue'
        },
        {
          id: 3,
          title: '第三个Vue'
        },
        {
          id: 4,
          title: '第四个Vue'
        },
        {
          id: 5,
          title: '第五个Vue'
        },
        {
          id: 6,
          title: '第六个Vue'
        },
        {
          id: 7,
          title: '第七个Vue'
        }
      ],
      count: 0
    }
  },
  methods: {
    go() {
      this.$router.push({ name: 'CreateArticle' })
    }
  }
}
</script>

<style lang="scss" scoped>
  .article-list-container{
    padding: 30px;
    .article-card{
      margin-bottom: 20px;
    }
  }
</style>
