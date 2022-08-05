<template>
  <div>
    <!-- 搜索栏 -->
    <el-row style="margin-top: 10px">
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
      <el-col :span="6">
        <el-input v-model="searchName" placeholder="请输入员工姓名">
          <!-- 放大镜按钮 -->
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="findArrangeList"
          ></el-button>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="warning" plain @click="addUserTime">新增人员排班</el-button>
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
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)"
            icon="el-icon-edit"
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 切换方式显示子组件 -->
    <component
      :is="comName"
      :roleData="roleData"
      :familyData="familyData"
      :objuser="user"
      :objuserid="user.dutyuserid"
      :userid="adduserid"
      @func="handleShow"
      @add="addDo"
      @openCt="open"
      week="thisWeek"
    ></component>
  </div>
</template>

<script>
//导入子组件
import UserTimeEdit from "./UserTimeEdit.vue";
import UserTimeAdd from "./UserTimeAdd.vue";
import UserTimeAddDo from "./UserTimeAddDo.vue";
export default {
  components: {
    UserTimeEdit,
    UserTimeAdd,
    UserTimeAddDo,
  },
  data() {
    return {
      adduserid: "",
      searchName: "",
      searchRoleid: "",
      searchFamilyid: "",
      roleData: [],
      familyData: [],
      arrangeData: [],
      user: {},
      comName: "",
    };
  },
  created() {
    //获取科室数据
    this.findFamilyList();
    //获取岗位数据
    this.findRoleList();
    //获取排班数据
    this.findArrangeList();
  },
  methods: {
    addDo(comName, userid) {
      this.comName = comName;
      this.adduserid = userid;
    },
    //新增人员排班
    addUserTime() {
      this.comName = "UserTimeAdd";
    },
    //处理编辑
    handleEdit(index, row) {
      this.comName = "UserTimeEdit";
      this.user = row;
    },
    //控制子组件
    handleShow() {
      this.comName = "";
      this.findArrangeList;
    },
    open() {
      this.findArrangeList;
      this.comName = "UserTimeAdd";
    },
    //处理岗位变化
    searchRoleChange() {
      this.findArrangeList();
    },
    //处理科室变化
    searchFamilyChange() {
      this.findArrangeList();
    },
    //查询排班信息
    findArrangeList() {
      this.$axios
        .get("/api/arrange/list", {
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
        .catch((e) => {
          this.$message({
            showClose: true,
            message: "服务器跑不见了!",
            type: "error",
            offset: 550,
            duration: 1000, //显示的时间,ms
          });
        });
    },
    //查询角色列表
    findRoleList() {
      this.$axios
        .get("/api/role/list", {
          //  headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data);
          this.roleData = res.data.data;
        })
        .catch((e) => {
          this.$message({
            showClose: true,
            message: "服务器跑不见了!",
            type: "error",
            offset: 550,
            duration: 1000, //显示的时间,ms
          });
        });
    },
    //查询科室列表
    findFamilyList() {
      this.$axios
        .get("/api/family/list", {
          //  headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data);
          this.familyData = res.data.data;
        })
        .catch((e) => {
          this.$message({
            showClose: true,
            message: "服务器跑不见了!",
            type: "error",
            offset: 550,
            duration: 1000, //显示的时间,ms
          });
        });
    },
  },
};
</script>
<style lang="less" scoped>
.el-breadcrumb {
  background: #d4dae0;
  padding-left: 15px;
  height: 40px;
  line-height: 40px;
}
</style>
