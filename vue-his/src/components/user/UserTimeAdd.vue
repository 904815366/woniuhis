<template>
  <el-drawer
    :title="title"
    direction="ltr"
    :visible.sync="editUserDialogFormVisible"
    :before-close="cancelEdit"
    size="80%"
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
      <el-table-column prop="monday" label="周一" width="70" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.monday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="tuesday" label="周二" width="70" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.tuesday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="wednesday" label="周三" width="70" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.wednesday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="thursday" label="周四" width="70" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.thursday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="friday" label="周五" width="70" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.friday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="saturday" label="周六" width="70" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.saturday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sunday" label="周日" width="60" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sunday !== null" circle>
            <span style="color: #409eff" class="el-icon-check"></span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="120">
        <template slot-scope="scope">
          <el-button type="primary" @click="confirmEdit">提 交</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-drawer>
</template>

<script>
export default {
  data() {
    return {
      searchName: "",
      searchRoleid: "",
      searchFamilyid: "",
      arrangeData: [],
      title: "",
      editUserDialogFormVisible: true,
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
    //处理岗位变化
    searchRoleChange() {
      this.findNullArrUserList();
    },
    //处理科室变化
    searchFamilyChange() {
      this.findNullArrUserList();
    },
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
        //本周新增操作
      } else if ((week = "nextWeek")) {
        console.log(week);
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
