<template>
  <div>
    <el-dialog
      title="编辑人员排班信息"
      :visible.sync="editUserDialogFormVisible"
      @close="cancelEdit"
    >
      {{ objuserid }},{{ objuser }}
      <div style="margin-left: 130px" v-if="week == 'thisWeek'">
        <el-checkbox-group v-model="checkList">
          <el-checkbox
            value="1"
            label="1"
            :disabled="1 <= this.today ? true : false"
            border
            >周一</el-checkbox
          ><br />
          <el-checkbox
            value="2"
            label="2"
            :disabled="2 <= this.today ? true : false"
            border
            >周二</el-checkbox
          ><br />
          <el-checkbox
            value="3"
            label="3"
            :disabled="3 <= this.today ? true : false"
            border
            >周三</el-checkbox
          ><br />
          <el-checkbox
            value="4"
            label="4"
            :disabled="4 <= this.today ? true : false"
            border
            >周四</el-checkbox
          ><br />
          <el-checkbox
            value="5"
            label="5"
            :disabled="5 <= this.today ? true : false"
            border
            >周五</el-checkbox
          ><br />
          <el-checkbox
            value="6"
            label="6"
            :disabled="6 <= this.today ? true : false"
            border
            >周六</el-checkbox
          ><br />
          <el-checkbox
            value="7"
            label="7"
            :disabled="7 <= this.today ? true : false"
            border
            >周日</el-checkbox
          ><br />
        </el-checkbox-group>
      </div>
      <div style="margin-left: 130px" v-if="week == 'nextWeek'">
        <el-checkbox-group v-model="checkList">
          <el-checkbox value="1" label="1" border>周一</el-checkbox><br />
          <el-checkbox value="2" label="2" border>周二</el-checkbox><br />
          <el-checkbox value="3" label="3" border>周三</el-checkbox><br />
          <el-checkbox value="4" label="4" border>周四</el-checkbox><br />
          <el-checkbox value="5" label="5" border>周五</el-checkbox><br />
          <el-checkbox value="6" label="6" border>周六</el-checkbox><br />
          <el-checkbox value="7" label="7" border>周日</el-checkbox><br />
        </el-checkbox-group>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelEdit">取 消</el-button>
        <el-button type="primary" @click="confirmEdit">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      editUserDialogFormVisible: true,
      today: "",
      checkList: [],
      week1: this.week,
      userid1: this.objuserid,
    };
  },
  props: ["objuserid", "week", "objuser"],
  created() {
    this.today = new Date().getDay();
    this.checkList = [
      this.objuser.monday,
      this.objuser.tuesday,
      this.objuser.wednesday,
      this.objuser.thursday,
      this.objuser.friday,
      this.objuser.saturday,
      this.objuser.sunday,
    ];
  },
  methods: {
    cancelEdit() {
      this.editUserDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmEdit() {
      let week = this.week1;
      let checkListStr = this.checkList;
      let dutyuserid = this.userid1;
      let createuserid = window.sessionStorage.getItem("currentUserId");
      console.log(week);
      console.log(checkListStr);
      console.log(dutyuserid);
      console.log(createuserid);
      this.$axios
        .post(
          "/api/arrange/editArr?dutyuserid=" +
            dutyuserid +
            "&checkListStr=" +
            checkListStr +
            "&createuserid=" +
            createuserid +
            "&week=" +
            week
        )
        .then((res) => {
          if (res.data.status == 200) {
            this.$message({
              type: "success",
              message: "排班成功!",
              offset: 300,
              duration: 1000,
            });
            //成功后,关闭窗口
            let timer = setTimeout(() => {
              this.editUserDialogFormVisible = false;
              // this.$emit("openCt");
            }, 1000);
          } else {
            this.$message({
              type: "error",
              message: "排班失败!",
              offset: 300,
              duration: 1000,
            });
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style></style>
