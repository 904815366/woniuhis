<template>
  <el-dialog title="添加药品" :visible.sync="addDrugDialogFormVisible" @close="cancelAdd">
    <el-form :model="drug" :inline="true" ref="checkform" :rules="rules">
      <el-form-item label="药品名:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="类型:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.type" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="价格:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.price" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="数量:" :label-width="formLabelWidth" size="small" prop="num">
        <el-input v-model="drug.num" autocomplete="off"></el-input>
      </el-form-item>
      <font color="red" size="2">*</font>
      <el-form-item label="报警库存:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.alarmnum" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="批次:" :label-width="formLabelWidth" size="small" prop="batch">
        <el-input v-model="drug.batch" autocomplete="off"></el-input>
      </el-form-item>
      <font color="red" size="2">*</font>
      <el-form-item label="厂商:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.producer" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="产地:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.producing" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="功效:" :label-width="formLabelWidth" size="small">
        <el-input v-model="drug.mdfunction" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="有效期" :label-width="formLabelWidth" size="small">
        <el-col :span="7">
          <el-date-picker
            type="date"
            placeholder="生产日期"
            v-model="drug.protime"
            style="width: 100%"
          ></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">&nbsp;-</el-col>
        <el-col :span="7">
          <el-date-picker
            type="date"
            placeholder="有效期至"
            v-model="drug.validtime"
            style="width: 100%"
          ></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="备注:" :label-width="formLabelWidth" prop="memo" size="small">
        <el-input v-model="drug.memo" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancelAdd">取 消</el-button>
      <el-button type="primary" @click="confirmAdd">确 定</el-button>
    </div>
    <div align="center">
      <font color="red" size="2"
        >注:有同批次则只需添加库存,若无该批次则除备注外都为必填!</font
      >
    </div>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      addDrugDialogFormVisible: true,
      formLabelWidth: "120px",
      drug: {},
      rules: {
        num: [
          { required: true, message: "请输入数量", trigger: "blur" },
          { pattern: /^[1-9][\d]{0,}$/, message: "格式错误", trigger: "blur" },
        ],
        batch: [{ required: true, message: "请输入批次", trigger: "blur" }],
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
    confirmAdd() {
      this.$refs["checkform"].validate((valid) => {
        if (!valid) {
          return;
        } else {
          // 发送 axios请求
          console.log(this.drug.protime);
          this.$axios
            .post("/api/drug/add", this.drug)
            .then((res) => {
              console.log(res.data);
              if (res.data.status == 200) {
                this.$message({
                  type: "success",
                  message: "添加药品成功!",
                  offset: 300,
                  duration: 1000, //显示的时间,ms
                });
              } else {
                this.$message({
                  type: "danger",
                  message: "添加药品失败!",
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
          this.$emit("funreload");
        }
      });
    },
  },
  created() {},
};
</script>

<style></style>
