<template>
  <div class="article-list-container">
    <!--  -->
    <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
      <li v-for="i in count" class="infinite-list-item">{{ i }}</li>
    </ul>
  </div>
</template>

<script>
import Vue from 'vue'
import ArticleCard from './components/ArticleCard'
import infiniteScroll from 'vue-infinite-scroll'
Vue.use(infiniteScroll)
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
    load() {
      console.log('loading')
      this.count += 2
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
