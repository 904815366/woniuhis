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
<style  lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

html,
body,
#app {
  height: 100%;
}

* {
  margin: 0;
  padding: 0;
}
</style>
