<template>
  <div>
    <el-dialog
      title="编辑人员排班信息"
      :visible.sync="editUserDialogFormVisible"
      @close="cancelEdit"
    >
      {{ objuserid }},{{ week }},{{ arrangeData }}
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
    };
  },
  props: ["arrangeData", "objuserid", "week"],
  created() {},
  methods: {
    cancelEdit() {
      this.editUserDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmEdit() {
      let week = this.week;
      this.editUserDialogFormVisible = false;
      if (week == "thisWeek") {
        console.log(week);
        //本周排班操作
      } else if ((week = "nextWeek")) {
        console.log(week);
        //下周排班操作
      }
    },
  },
};
</script>

<style></style>
