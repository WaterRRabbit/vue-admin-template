<template>
  <el-card>
    <h3>{{ item.title }}</h3>
  </el-card>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    }
  }
}
</script>
