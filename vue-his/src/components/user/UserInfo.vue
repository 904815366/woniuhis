<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/user/homeContent' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><router-link to="#">人员管理</router-link></el-breadcrumb-item>
      <el-breadcrumb-item>个人信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 数据表格 -->
    <el-row style="margin-top: 15px">
      <el-form :model="user" status-icon>
        <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
          <el-input
            readonly
            v-model="user.name"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex" :label-width="formLabelWidth">
          <el-input readonly v-model="user.sex" :style="inputWidth" autocomplete="off">
          </el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age" :label-width="formLabelWidth">
          <el-input
            readonly
            v-model="user.age"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="card" :label-width="formLabelWidth">
          <el-input
            readonly
            v-model="user.card"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          readonly
          label="联系电话"
          prop="phone"
          :label-width="formLabelWidth"
        >
          <el-input
            readonly
            v-model="user.phone"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="岗位" prop="roleid" :label-width="formLabelWidth">
          <span v-for="role in roleData">
            <el-input
              readonly
              v-if="role.id === user.roleid"
              v-model="role.name"
              :style="inputWidth"
              autocomplete="off"
              :key="role.id"
            ></el-input>
          </span>
        </el-form-item>
        <el-form-item label="科室" prop="familyid" :label-width="formLabelWidth">
          <span v-for="family in familyData">
            <el-input
              readonly
              v-if="family.id === user.familyid"
              v-model="family.familyname"
              :style="inputWidth"
              autocomplete="off"
              :key="family.id"
            ></el-input>
          </span>
        </el-form-item>
        <el-form-item label="级别" prop="level" :label-width="formLabelWidth">
          <el-input
            readonly
            v-model="user.level"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row style="margin-top: 15px">
      <div style="color: dimgrey; margin-bottom: 5px">您可以修改您的密码</div>
      <el-form status-icon :model="updSelf" :rules="rules" ref="updForm">
        <el-form-item label="原密码" prop="opassword" :label-width="updFormWidth">
          <el-input
            type="password"
            v-model="opassword"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="npassword" :label-width="updFormWidth">
          <el-input
            type="password"
            v-model="updSelf.password"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="核对密码" prop="rpassword" :label-width="updFormWidth">
          <el-input
            type="password"
            v-model="rpassword"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="" prop="status" label-width="380px">
          <el-button type="primary" @click="confirmUpd">提交修改</el-button>
        </el-form-item>
      </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    //校验器--校验原密码
    let checkOpassword = (rule, value, callback) => {
      let oPsw = this.opassword;
      let password = this.user.password;
      if (this.opassword === "") {
        callback(new Error("请输入原密码"));
      } else if (this.opassword.length < 3) {
        callback(new Error("长度为3-10字符"));
      } else if (this.opassword.length > 10) {
        callback(new Error("长度为3-10字符"));
      } else {
        this.$axios
          .get("/api/user/psw?oPsw=" + oPsw + "&password=" + password)
          .then((res) => {
            if (res.data.status === 200) {
              callback();
            } else {
              callback(new Error(res.data.msg));
            }
          });
      }
    };
    //校验器--校验新密码
    let checkNpassword = (rule, value, callback) => {
      if (this.updSelf.password === "") {
        callback(new Error("请输入新密码(长度为3-10字符)"));
      } else if (this.updSelf.password.length < 3) {
        callback(new Error("长度为3-10字符"));
      } else if (this.updSelf.password.length > 10) {
        callback(new Error("长度为3-10字符"));
      } else {
        callback();
      }
    };
    //校验器--校验核对密码
    let checkRpassword = (rule, value, callback) => {
      if (this.rpassword === "") {
        callback(new Error("请再次输入新密码"));
      } else if (this.rpassword !== this.updSelf.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      rules: {
        opassword: [{ required: true, validator: checkOpassword, trigger: "blur" }],
        npassword: [{ required: true, validator: checkNpassword, trigger: "blur" }],
        rpassword: [{ required: true, validator: checkRpassword, trigger: "blur" }],
      },
      opassword: "",
      rpassword: "",
      updSelf: { id: window.sessionStorage.getItem("currentUserId"), password: "" },
      roleData: [],
      familyData: [],
      formLabelWidth: "350px",
      updFormWidth: "100px",
      inputWidth: "width: 400px",
      userid: window.sessionStorage.getItem("currentUserId"),
      user: {},
    };
  },
  created() {
    this.findRoleList();
    this.findFamilyList();

    //根据id查用户信息
    let id = this.userid;
    this.$axios
      .get("/api/user/userinfo?id=" + id)
      .then((res) => {
        console.log(res.data.data);
        this.user = res.data.data;
      })
      .catch(() => {});
  },
  methods: {
    //提交修改
    confirmUpd() {
      let id = this.userid;
      let npw = this.rpassword;
      this.$refs["updForm"].validate((valid) => {
        if (valid) {
          this.$axios
            .post("/api/user/updSelf?id=" + id + "&password=" + npw)
            .then((res) => {
              console.log(res.data);
              if (res.data.status === 200) {
                this.$message({
                  showClose: true,
                  message: "修改成功",
                  type: "success",
                  center: true,
                });
                this.updSelf.password = "";
                this.rpassword = "";
                this.opassword = "";
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
          return false;
        }
      });
    },
    findRoleList() {
      this.$axios
        .get("/api/role/list", {
          //  headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data);
          this.roleData = res.data.data;
        })
        .catch((e) => {
          this.$message({
            showClose: true,
            message: "服务器跑不见了!",
            type: "error",
            offset: 550,
            duration: 1000, //显示的时间,ms
          });
        });
    },
    //查询科室列表
    findFamilyList() {
      this.$axios
        .get("/api/family/list", {
          //  headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data);
          this.familyData = res.data.data;
        })
        .catch((e) => {
          this.$message({
            showClose: true,
            message: "服务器跑不见了!",
            type: "error",
            offset: 550,
            duration: 1000, //显示的时间,ms
          });
        });
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
