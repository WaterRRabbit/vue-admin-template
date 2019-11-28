<template>
  <div class="dashboard-container">
    <panel-group />
  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
export default {
  name: 'Dashboard',
  components: { PanelGroup }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    padding: 30px;
    background-color: rgb(240, 242, 245);
    width: 100%;
    height: 100%;
  }
}
</style>
