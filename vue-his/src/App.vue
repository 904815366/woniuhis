<template>
  <div id="app">
    <router-view v-if="active" />
  </div>
</template>
<script>

export default {
  name: 'app',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      active: true
    }
  },
  methods: {
    reload() {
      this.active = false;
      this.$nextTick(function () {
        this.active = true;
      })
    }
  },
  components: {
    // header
  }
}
</script>
<style scoped lang="less">
</style>
