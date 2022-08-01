<template>
  <el-drawer
    :title="title"
    direction="ltr"
    :visible.sync="editAddUserTimeDialogFormVisible"
    :before-close="cancelAdd"
    size="60%"
  >
    {{ week }},{{ arrangeData }}
    <el-row style="margin-top: 10px">
      <el-col :span="6">
        <el-input v-model="searchName" placeholder="请输入员工姓名">
          <!-- 放大镜按钮 -->
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="findNullArrUserList"
          ></el-button>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-select @change="searchFamilyChange" v-model="searchFamilyid">
          <el-option label="科室不限" value=""></el-option>
          <el-option
            v-for="family in familyData"
            :key="family.id"
            :label="family.familyname"
            :value="family.id"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="4">
        <el-select @change="searchRoleChange" v-model="searchRoleid">
          <el-option label="岗位不限" value=""></el-option>
          <el-option
            v-for="role in roleData"
            :key="role.id"
            :label="role.name"
            :value="role.id"
          ></el-option>
        </el-select>
      </el-col>
    </el-row>
    <!-- 数据表格 -->
    <el-table :data="arrangeData" style="width: 100%; margin-top: 10px">
      <!-- row-key="arrangeData.nullArrUser.id" -->
      <el-table-column
        type="index"
        label="序号"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column label="岗位" width="150" align="center">
        <template slot-scope="scope">
          <span v-for="role in roleData" :key="role.id">
            <span v-if="role.id === scope.row.roleid">{{ role.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="科室" width="150" align="center">
        <template slot-scope="scope">
          <span v-for="family in familyData" :key="family.id">
            <span v-if="family.id === scope.row.familyid">{{ family.familyname }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="120">
        <template slot-scope="scope">
          <el-button type="primary" @click="confirmAdd(scope.$index, scope.row)"
            >排 班</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 切换方式显示子组件 -->
    <component
      :is="comName"
      :userid="user.id"
      :week="week"
      @func="handleShow"
      @openCt="open"
    ></component>
    <!-- </el-dialog> -->
  </el-drawer>
</template>

<script>
import UserTimeAddDo from "./UserTimeAddDo.vue";
export default {
  props: ["roleData", "familyData", "week"],
  components: {
    UserTimeAddDo,
  },
  data() {
    return {
      user: {},
      comName: "",
      arrangeData: [],
      searchName: "",
      searchRoleid: "",
      searchFamilyid: "",
      title: "",
      editAddUserTimeDialogFormVisible: true,
    };
  },
  created() {
    if (this.week == "thisWeek") {
      this.title = "本周未排班人员列表";
    } else if ((this.week = "nextWeek")) {
      this.title = "下周未排班人员列表";
    }
    this.findNullArrUserList();
  },
  methods: {
    //控制子组件
    handleShow() {
      this.comName = "";
      this.findNullArrUserList();
    },
    open() {
      this.editAddUserTimeDialogFormVisible = true;
      this.$emit("openCt");
    },
    //处理岗位变化
    searchRoleChange() {
      this.findNullArrUserList();
    },
    //处理科室变化
    searchFamilyChange() {
      this.findNullArrUserList();
    },
    cancelAdd() {
      this.editAddUserTimeDialogFormVisible = false;
      //调用父组件传来的方法
      this.$emit("func");
    },
    confirmAdd(index, row) {
      this.editAddUserTimeDialogFormVisible = false;
      //今天星期几
      console.log(new Date().getDay());
      console.log(row);
      console.log(row.id);
      this.user = row;
      this.$emit("add", "UserTimeAddDo", row.id);

      if (this.week == "thisWeek") {
        //本周新增操作
      } else if ((this.week = "nextWeek")) {
        //下周新增操作
      }
    },
    //查出未排班的员工信息
    findNullArrUserList() {
      let week = this.week;
      if (week == "thisWeek") {
        this.$axios
          .get("/api/user/nullArrUserThis", {
            params: {
              searchName: this.searchName,
              searchRoleid: this.searchRoleid,
              searchFamilyid: this.searchFamilyid,
            },
          })
          .then((res) => {
            console.log(res.data.data);
            this.arrangeData = res.data.data;
          })
          .catch(() => {});
      } else if ((week = "nextWeek")) {
        this.$axios
          .get("/api/user/nullArrUserNext", {
            params: {
              searchName: this.searchName,
              searchRoleid: this.searchRoleid,
              searchFamilyid: this.searchFamilyid,
            },
          })
          .then((res) => {
            console.log(res.data.data);
            this.arrangeData = res.data.data;
          })
          .catch(() => {});
      }
    },
  },
};
</script>

<style></style>
