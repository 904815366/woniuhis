<template>
  <div>
    <el-button type="success" plain @click="thisWeek" :disabled="thisButtonStatus"
      >本周人员排信息</el-button
    >
    <el-button type="primary" plain @click="nextWeek" :disabled="nextButtonStatus"
      >下周人员排信息</el-button
    >
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {
    this.$router.push("/user/time/usertimethis");
    this.thisButtonStatus = true;
    this.nextButtonStatus = false;
  },
  methods: {
    nextButtonStatus() {},
    buttonStatus() {},
    //控制路由显示本周信息与下周信息
    thisWeek() {
      this.$router.push("/user/time/usertimethis");
      this.thisButtonStatus = true;
      this.nextButtonStatus = false;
    },
    nextWeek() {
      this.$router.push("/user/time/usertimenext");
      this.thisButtonStatus = false;
      this.nextButtonStatus = true;
    },
  },
};
</script>
