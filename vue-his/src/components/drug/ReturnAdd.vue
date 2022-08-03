<template>
  <el-dialog title="编辑退药" :visible.sync="addDrugDialogFormVisible" @close="cancelAdd">
    <el-form :model="warn">
      <el-form-item label="住院编号:" :label-width="formLabelWidth">
        <span>{{ warn.id }}</span>
      </el-form-item>
      <el-form-item label="药品名称:" :label-width="formLabelWidth">
        <span v-for="detail in detailList" :key="detail.id">
          <span v-if="detail.warnid == warn.id">
            <span v-for="drug in drugList" :key="drug.id">
              <span v-if="drug.id == detail.drugid">
                {{ drug.name }}
              </span>
            </span>
          </span>
        </span>
        </span>
      </el-form-item>
      <el-form-item label="开立时间:" :label-width="formLabelWidth">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{ warn.warntime }}</span>
      </el-form-item>
      <el-form-item label="开药数量:" :label-width="formLabelWidth">
        <span v-for="detail in detailList" :key="detail.id">
          <span v-if="detail.warnid == warn.id">
            {{ detail.num }}
          </span>
        </span>
      </el-form-item>
      <el-form-item label="退药数量:" :label-width="formLabelWidth">
        <el-input v-model="returnnum" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item :label-width="formLabelWidth">

        <span v-for="detail in detailList" :key="detail.id">
          <span v-if="detail.warnid == warn.id">
            <el-button @click="cancelAdd">取 消</el-button>
            <el-button type="primary" @click="confirmEdit(detail.num)">确 定</el-button>
          </span>
        </span>

      </el-form-item>

    </el-form>

  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      addDrugDialogFormVisible: true,
      formLabelWidth: "120px",
      warn: {},
      returnnum: "",
      warndetail: {},
    };
  },
  props: ["objWarn", "registerList", "familyList", "userList", "drugList", "detailList"],
  methods: {
    cancelAdd() {
      this.addDrugDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmEdit(num) {
      console.log(num + "-" + this.returnnum);
      if (num < this.returnnum) {
        this.$message({
          type: "danger",
          message: "申请数量应小于开药数量!",
          offset: 300,
          duration: 1000, //显示的时间,ms
        });
        return;
      }
      //发送 axios请求
      this.$axios
        .get("/api/drugreturn/edit", {
          params: {
            id: this.warn.id,
            returnnum: this.returnnum,
          }
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.status == 200) {
            this.$message({
              type: "success",
              message: "申请退药成功!",
              offset: 300,
              duration: 1000, //显示的时间,ms  
            });
          } else {
            this.$message({
              type: "danger",
              message: "申请退药失败!",
              offset: 300,
              duration: 1000, //显示的时间,ms
            });
          }
        })
        .catch(() => {
          alert("error");
        });
      this.addDrugdialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
      this.$emit("reload");
    },
  },
  created() {
    //浅拷贝
    //this.teacher=this.objTeacher;
    this.warn = {
      id: this.objWarn.id,
      patientname: this.objWarn.patientname,
      registerid: this.objWarn.registerid,
      warndetailsid: this.objWarn.warndetailsid,
      warntime: this.objWarn.warntime,
    };
  },
};
</script>

<style>
</style>
