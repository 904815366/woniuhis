<template>
  <el-drawer
    :title="title"
    direction="ltr"
    :visible.sync="editAddUserTimeDialogFormVisible"
    :before-close="cancelAdd"
    size="80%"
  >
    <!-- <el-dialog
    title="排班"
    :visible.sync="editAddUserTimeDialogFormVisible"
    @close="cancelAdd"
  > -->
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
      <el-table-column type="index" label="排班信息" width="490" align="center">
        <div v-for="arrange in arrangeData" :key="arrange.id">
          <el-checkbox-group v-model="checkList">
            <el-checkbox
              v-for="day in weekData"
              :key="day.id"
              :label="day.d"
              v-model="day.id"
            ></el-checkbox>
          </el-checkbox-group>
        </div>
        <!-- <el-checkbox label="2">周二</el-checkbox>
        <el-checkbox label="3">周三</el-checkbox>
        <el-checkbox label="4">周四</el-checkbox><br />
        <el-checkbox label="5">周五</el-checkbox>
        <el-checkbox label="6">周六</el-checkbox>
        <el-checkbox label="7">周日</el-checkbox> -->
      </el-table-column>
      <el-table-column label="操作" align="center" width="120">
        <template slot-scope="scope">
          <el-button type="primary" @click="confirmEdit(scope.$index, scope.row)"
            >提 交</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 切换方式显示子组件 -->
    <component :is="comName" :objuser="user" @func="handleShow"></component>
    <!-- </el-dialog> -->
  </el-drawer>
</template>

<script>
import UserTimeAddDo from "./UserTimeAddDo.vue";
export default {
  components: {
    UserTimeAddDo,
  },
  data() {
    return {
      weekData: [
        { id: 1, d: "周一" },
        { id: 2, d: "周二" },
        { id: 3, d: "周三" },
        { id: 4, d: "周四" },
        { id: 5, d: "周五" },
        { id: 6, d: "周六" },
        { id: 7, d: "周日" },
      ],
      user: {},
      comName: "",
      checkList: [],
      arrangeData: [],
      searchName: "",
      searchRoleid: "",
      searchFamilyid: "",
      arrangeInfo: {
        monday: "",
        tuesday: "",
        wednesday: "",
        thursday: "",
        friday: "",
        saturday: "",
        sunday: "",
      },
      title: "",
      editAddUserTimeDialogFormVisible: true,
    };
  },
  props: ["roleData", "familyData", "week"],
  created() {
    let week = this.week;
    if (week == "thisWeek") {
      this.title = "本周未排班人员列表";
    } else if ((week = "nextWeek")) {
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
    confirmEdit(index, row) {
      this.editAddUserTimeDialogFormVisible = false;
      //今天星期几
      console.log(new Date().getDay());
      console.log(row);
      console.log(this.checkList);
      this.comName = UserTimeAddDo;
      this.$emit("add", this.comName);
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
