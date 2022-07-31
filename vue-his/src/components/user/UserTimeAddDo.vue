<template>
  <div>
    <el-dialog
      title="排班"
      :visible.sync="editUserDialogFormVisible"
      @close="cancelAdd"
    ></el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      editUserDialogFormVisible: true,
    };
  },
  created() {},
  methods: {
    cancelAdd() {
      this.editUserDialogFormVisible = false;
      this.$emit("func");
    },
  },
};
</script>

<style></style>
