<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/user/homeContent' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><router-link to="#">人员管理</router-link></el-breadcrumb-item>
      <el-breadcrumb-item>人员录入</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row style="margin-top: 15px">
      <el-form :model="user" status-icon :rules="rules" ref="addForm">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input
            v-model="user.username"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
          <el-input
            type="password"
            v-model="user.password"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="核对密码" prop="repassword" :label-width="formLabelWidth">
          <el-input
            type="password"
            v-model="rpassword"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
          <el-input v-model="user.name" :style="inputWidth" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex" :label-width="formLabelWidth">
          <el-select v-model="user.sex" placeholder="请选择性别" :style="inputWidth">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age" :label-width="formLabelWidth">
          <el-input v-model="user.age" :style="inputWidth" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="card" :label-width="formLabelWidth">
          <el-input v-model="user.card" :style="inputWidth" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone" :label-width="formLabelWidth">
          <el-input
            v-model="user.phone"
            :style="inputWidth"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="岗位" prop="roleid" :label-width="formLabelWidth">
          <el-select v-model="user.roleid" placeholder="请选择岗位" :style="inputWidth">
            <el-option
              v-for="role in roleData"
              :key="role.id"
              :label="role.name"
              :value="role.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科室" prop="familyid" :label-width="formLabelWidth">
          <el-select v-model="user.familyid" placeholder="请选择科室" :style="inputWidth">
            <el-option
              v-for="family in familyData"
              :key="family.id"
              :label="family.familyname"
              :value="family.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="级别" prop="level" :label-width="formLabelWidth">
          <el-select v-model="user.level" placeholder="请选择级别" :style="inputWidth">
            <el-option label="专家" value="专家"></el-option>
            <el-option label="主治医师" value="主治医师"></el-option>
            <el-option label="主任" value="主任"></el-option>
            <el-option label="业务员" value="业务员"></el-option>
            <el-option label="副主任" value="副主任"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="在职状态" prop="status" :label-width="formLabelWidth">
          <el-select v-model="user.status" placeholder="请选择状态" :style="inputWidth">
            <el-option label="在职" value="0"></el-option>
            <el-option label="离职" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="" prop="status" label-width="450px">
          <el-button type="warning" @click="cancelAdd">重 置</el-button>
          <el-button type="primary" @click="confirmAdd">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    //校验器--校验录入人员的用户名唯一
    let checkUsernameOnly = (rule, value, callback) => {
      let checkUsername = this.user.username;
      this.$axios
        .get("/api/user/getByUsername?checkUsername=" + checkUsername)
        .then((res) => {
          if (res.data.status === 200) {
            console.log("用户名可用");
            callback();
          } else {
            callback(new Error("用户名已存在"));
          }
        })
        .catch((e) => {
          console.log(e);
        });
    };
    //校验器--校验核对密码
    let checkRepassword = (rule, value, callback) => {
      if (this.rpassword === "") {
        callback(new Error("请再次输入密码"));
      } else if (this.rpassword !== this.user.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      roleData: [],
      familyData: [],
      rpassword: "",
      user: {
        username: "",
        password: "",
        name: "",
        sex: "",
        age: "",
        card: "",
        phone: "",
        roleid: "",
        familyid: "",
        level: "",
        status: "",
      },
      formLabelWidth: "350px",
      inputWidth: "width: 400px",
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" },
          { validator: checkUsernameOnly, message: "", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" },
        ],
        repassword: [{ required: true, validator: checkRepassword, trigger: "blur" }],
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 2, max: 6, message: "长度在 2 到 6 个字符", trigger: "blur" },
        ],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        age: [
          { required: true, message: "请输入年龄", trigger: "blur" },
          { min: 2, max: 2, message: "年龄为2位数", trigger: "blur" },
        ],
        card: [
          { required: true, message: "请输入身份证号", trigger: "blur" },
          { min: 18, max: 18, message: "请输入正确的18位身份证号", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { min: 11, max: 11, message: "请输入正确的11位联系电话", trigger: "blur" },
        ],
        roleid: [{ required: true, message: "请选择岗位", trigger: "change" }],
        familyid: [{ required: true, message: "请选择科室", trigger: "change" }],
        level: [{ required: true, message: "请选择级别", trigger: "change" }],
        status: [{ required: true, message: "请选择在职状态", trigger: "change" }],
      },
    };
  },
  created() {
    //获取角色数据
    this.findRoleList();
    //获取科室数据
    this.findFamilyList();
  },
  methods: {
    //提交录入
    confirmAdd() {
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          this.$axios.post("/api/user/addUser", this.user).then((res) => {
            console.log(res.data);
            if (res.data.status === 200) {
              this.$message({
                showClose: true,
                message: "录入成功",
                type: "success",
                center: true,
              });
              this.$refs["addForm"].resetFields();
              this.rpassword = "";
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
    //重置录入
    cancelAdd() {
      this.$refs["addForm"].resetFields();
      this.rpassword = "";
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
