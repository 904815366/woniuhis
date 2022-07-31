<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/user/homeContent' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><router-link to="#">人员管理</router-link></el-breadcrumb-item>
      <el-breadcrumb-item>人员排班</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row style="margin-top: 10px">
      <el-button type="success" plain @click="thisWeek" :disabled="thisButtonStatus"
        >本周人员排信息</el-button
      >
      <el-button type="primary" plain @click="nextWeek" :disabled="nextButtonStatus"
        >下周人员排信息</el-button
      >
    </el-row>
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

<style lang="less" scoped>
.el-breadcrumb {
  background: #d4dae0;
  padding-left: 20px;
  height: 40px;
  line-height: 40px;
}
</style>
