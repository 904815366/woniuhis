<template>
  <el-dialog title="编辑药品" :visible.sync="addDrugDialogFormVisible" @close="cancelAdd">
    <el-form :model="drug" :rules="rules">
      <el-form-item label="药品id:" :label-width="formLabelWidth">
        <span>{{ drug.id }}</span>
      </el-form-item>
      <el-form-item label="药品名称:" :label-width="formLabelWidth">
        <span>{{ drug.name }}</span>
      </el-form-item>
      <el-form-item label="药品批次:" :label-width="formLabelWidth">
        <span>{{ drug.batch }}</span>
      </el-form-item>
      <el-form-item label="库存:" :label-width="formLabelWidth">
        <span>{{ drug.num }}</span>
      </el-form-item>
      <el-form-item label="报警库存:" :label-width="formLabelWidth" prop="alarmnum">
        <el-input v-model="drug.alarmnum" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancelAdd">取 消</el-button>
      <el-button type="primary" @click="confirmEdit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      addDrugDialogFormVisible: true,
      formLabelWidth: "120px",
      drug: {
        alarmnum: "",
      },
      rules: {
        alarmnum: [
          { required: true, message: "请输入报警库存", trigger: "blur" },
          { pattern: /^[1-9][\d]{1,}$/, message: "金额格式错误", trigger: "blur" },
        ],
      },
    };
  },
  props: ["objDrug"],
  methods: {
    cancelAdd() {
      this.addDrugDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmEdit() {
      // 发送 axios请求
      this.$axios
        .post("/api/drug/edit", this.drug)
        .then((res) => {
          console.log(res.data);
          if (res.data.status == 200) {
            this.$message({
              type: "success",
              message: "编辑药品成功!",
              offset: 300,
              duration: 1000, //显示的时间,ms
            });
          } else {
            this.$message({
              type: "danger",
              message: "编辑药品失败!",
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
    this.drug = {
      id: this.objDrug.id,
      name: this.objDrug.name,
      batch: this.objDrug.batch,
      num: this.objDrug.num,
      alarmnum: this.objDrug.alarmnum,
    };
  },
};
</script>

<style></style>
