<template>
  <div style="height: 100%">
    <el-row type="flex" justify="center" align="middle">
      <el-col :span="8">

        <h1>HIS管理系统</h1><br />
        <el-form :model="user" status-icon :rules="rules" ref="loginForm" label-width="100px" class="loginForm">

          <el-form-item label="用户名" prop="username">
            <el-input v-model="user.username" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="user.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :plain="true" @click="submitForm()">登录</el-button>
            <el-button @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nameAndId: [],
      user: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],
      },
    };
  },

  created() { },
  methods: {
    submitForm() {
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          let data = this.$qs.stringify(this.user);
          // console.log(data);
          this.$axios.post("/api/login", data).then((res) => {
            if (res.data.status === 200) {
              this.$message({
                showClose: true,
                message: "登陆成功",
                type: "success",
                center: true,
              });
              this.nameAndId = res.data.msg.split("-");
              // console.log(this.nameAndId[2]);
              window.localStorage.setItem("strToken", res.data.data);
              window.sessionStorage.setItem("username", this.user.username);
              window.sessionStorage.setItem("currentUser", this.nameAndId[0]);
              window.sessionStorage.setItem("currentUserId", this.nameAndId[1]);
              window.sessionStorage.setItem("currentUserFamilyId", this.nameAndId[2]);

              this.$router.push("/user/homeContent");
            } else {
              this.$message({
                showClose: true,
                message: res.data.msg,
                type: "error",
                center: true,
              });
            }
          });
        } else {
          // console.log("进来了");

          return false;
        }
      });
    },
    resetForm() {
      this.$refs["loginForm"].resetFields();
    },
  },
};
</script>

<style scoped>
.el-row {
  height: 100%;
  background: #ffffff;
  text-align: center;
}

.el-col {
  background: #ffffff;
  padding: 20px 30px;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 15px;
}
</style>
