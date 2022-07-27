<template>
  <div>
    <el-dialog
      title="编辑用户"
      :visible.sync="editUserDialogFormVisible"
      @close="cancelEdit"
    >
      <el-form :model="user">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="user.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-input v-model="user.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input v-model="user.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" :label-width="formLabelWidth">
          <el-input v-model="user.card" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth">
          <el-input v-model="user.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="岗位" :label-width="formLabelWidth">
          <el-select v-model="user.roleid" placeholder="请选择岗位">
            <el-option
              v-for="role in roleData"
              :key="role.id"
              :label="role.name"
              :value="role.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科室" :label-width="formLabelWidth">
          <el-select v-model="user.familyid" placeholder="请选择科室">
            <el-option
              v-for="family in familyData"
              :key="family.id"
              :label="family.familyname"
              :value="family.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="级别" :label-width="formLabelWidth">
          <el-select v-model="user.level" placeholder="请选择级别">
            <el-option label="专家" value="专家"></el-option>
            <el-option label="主治医师" value="主治医师"></el-option>
            <el-option label="主任" value="主任"></el-option>
            <el-option label="副主任" value="副主任"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="在职状态" :label-width="formLabelWidth">
          <el-switch
            v-model="user.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
          ></el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelEdit">取 消</el-button>
        <el-button type="primary" @click="confirmEdit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      editUserDialogFormVisible: true,
      formLabelWidth: "120px",
      user: {},
    };
  },
  props: ["roleData", "objuser", "familyData"],
  created() {
    this.user = {
      id: this.objuser.id,
      familyid: this.objuser.familyid,
      name: this.objuser.name,
      sex: this.objuser.sex,
      age: this.objuser.age,
      card: this.objuser.card,
      phone: this.objuser.phone,
      roleid: this.objuser.roleid,
      level: this.objuser.level,
      status: this.objuser.status,
    };
    this.user.status = this.objuser.status == "0" ? true : false;
  },
  methods: {
    cancelEdit() {
      this.editUserDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmEdit() {
      this.user.status = this.user.status == true ? "0" : "1";
      //发送axios请求
      this.$axios
        .post("/api/user/edit", this.user, {
          headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.status == 200) {
            this.$message({
              type: "success",
              message: "修改成功!",
              offset: 300,
              duration: 1000,
            });
          } else {
            this.$message({
              type: "error",
              message: "修改失败!",
              offset: 300,
              duration: 1000,
            });
          }
        })
        .catch(() => {});
      this.$emit("func");
      console.log("func-ok");
      this.editUserDialogFormVisible = false;
    },
  },
};
</script>

<style></style>
