<template>
  <div>
    <el-dialog
      width="30%"
      title="排班"
      :visible.sync="editUserDialogFormVisible"
      @close="cancelAdd"
      >{{ week }},{{ userid }}
      <el-checkbox-group v-model="checkList">
        <el-checkbox value="1" label="周一"></el-checkbox><br />
        <el-checkbox value="2" label="周二"></el-checkbox><br />
        <el-checkbox value="3" label="周三"></el-checkbox><br />
        <el-checkbox value="4" label="周四"></el-checkbox><br />
        <el-checkbox value="5" label="周五"></el-checkbox><br />
        <el-checkbox value="6" label="周六"></el-checkbox><br />
        <el-checkbox value="7" label="周日"></el-checkbox><br />
      </el-checkbox-group>
      <el-button size="mini" type="primary" @click="confirmCheck" icon="el-icon-edit"
        >提交</el-button
      >
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: ["week", "userid"],
  data() {
    return {
      editUserDialogFormVisible: true,
      checkList: [],
    };
  },
  created() {},
  methods: {
    confirmCheck() {
      console.log(this.checkList);
      //发ajax请求,包括dutyuserid和checkList数组
      //成功后,关闭窗口,打开新增排班窗口
      this.editUserDialogFormVisible = false;
      this.$emit("openCt");
    },
    cancelAdd() {
      this.editUserDialogFormVisible = false;
      this.$emit("openCt");
    },
  },
};
</script>

<style></style>
