<template>
  <div>
    <el-dialog
      title="用户排班"
      :visible.sync="editUserDialogFormVisible"
      @close="cancelEdit"
    >
      {{ objuserid }}
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
  props: ["arrangeData", "objuserid"],
  created() {},
  methods: {
    cancelEdit() {
      this.editUserDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmEdit() {
      this.editUserDialogFormVisible = false;
    },
  },
};
</script>

<style></style>
