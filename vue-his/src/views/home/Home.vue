<template>
  <el-container>
    <el-header>
      <el-row>
        <el-col :span="8" class="col_l"><img style="width: 234px" src="@/assets/logo.png" /></el-col>
        <el-col :span="8">
          <h1>his管理系统</h1>
        </el-col>
        <el-col :span="8" class="col_r">
          <span style="color: blud">您好 , {{ username }} </span>&nbsp;&nbsp;
          <el-button type="danger" size="small" @click="logout()">退出</el-button>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-row class="tac">
          <el-col :span="12">
            <el-menu :default-active="$route.path" class="el-menu-vertical-demo" background-color="#545c64"
              text-color="#fff" active-text-color="#ffd04b" :router="true">
              <el-menu-item index="/home">
                <!-- <i class="el-icon-s-home"></i> -->
                <span slot="title">首页</span>
              </el-menu-item>

              <el-submenu index="1">
                <template slot="title">
                  <!-- <i class="el-icon-s-custom"></i> -->
                  <span>XXX</span>
                </template>

              </el-submenu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {};
  },
  methods: {
    logout() {
      this.$confirm("此操作将退出系统, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "退出成功!",
            center: true,
          });

          window.localStorage.clear();
          window.sessionStorage.clear();
          this.$router.push("/login");
        })
        .catch(() => { });
    },
  },
  created() { },
};
</script>

<style scoped lang="less">
.el-container {
  height: 100%;
}

.el-header {
  background: #fff;
  padding: 0;

  h1 {
    color: #515154;
    text-align: center;
    line-height: 60px;
    font-size: 26px;
    // font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    //   "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  .col_r {
    line-height: 60px;
    text-align: right;
    padding-right: 30px;
  }

  .col_l {
    text-align: left;
  }
}

.el-aside {
  background: #545c64;
}

.el-main {
  background: #eaeef1;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
