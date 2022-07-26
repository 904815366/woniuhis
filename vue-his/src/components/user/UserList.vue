<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/user/homeContent' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>
        <router-link to="/user">人员管理</router-link>
      </el-breadcrumb-item>
      <el-breadcrumb-item>人员列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索栏 -->
    <el-row style="margin-top: 10px">
      <el-col :span="8">
        <el-input v-model="searchName" placeholder="请输入内容">
          <!-- 放大镜按钮 -->
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="finduserList(1)"
          ></el-button>
        </el-input>
      </el-col>
      <el-col :span="8">
        <el-select
          @change="searchStatusChange"
          v-model="searchStatus"
          placeholder="请选择状态"
        >
          <el-option label="不限" value=""></el-option>
          <el-option label="在职" value="0"></el-option>
          <el-option label="离职" value="1"></el-option>
        </el-select>
      </el-col>
    </el-row>
    <!-- 讲师列表的数据表格
            :data动态绑定属性--数组
            el-table-column 表列
         -->
    <el-table :data="userData" style="width: 100%; margin-top: 10px">
      <el-table-column
        type="index"
        :index="indexMethod"
        label="序号"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="70"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="age"
        label="年龄"
        width="70"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="card"
        label="身份证号"
        width="150"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="phone"
        label="联系电话"
        width="110"
        align="center"
      ></el-table-column>
      <el-table-column label="岗位" width="120" align="center">
        <template slot-scope="scope">
          <span v-for="role in roleData" :key="role.id">
            <span v-if="role.id === scope.row.roleid">{{ role.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="科室" width="120" align="center">
        <template slot-scope="scope">
          <span v-for="family in familyData" :key="family.id">
            <span v-if="family.id === scope.row.familyid">{{ family.familyname }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="level"
        label="级别"
        width="70"
        align="center"
      ></el-table-column>
      <el-table-column label="在职状态" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == '0' ? 'success' : 'danger'">
            <span>{{ scope.row.status == "0" ? "在职" : "离职" }}</span>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!--分页组件
background是否显示背景色layout表示显示分页的布局组件
prev 上—页next下一页pager导航页码sizes每页记录数:total设置总记录数
: page-size每页记录数: current-page当前页码-->
    <el-pagination
      :background="true"
      layout="prev,pager,next,sizes,jumper,->,total"
      prev-text="上一页"
      next-text="下一页"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="pageSize"
      :current-page="currentPage"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>

    <!-- 切换方式显示子组件 -->
    <component
      :is="comName"
      :roleData="roleData"
      :familyData="familyData"
      :objuser="user"
      @func="handleShow"
    ></component>
  </div>
</template>

<script>
//导入子组件
import UpdateUser from "./UpdateUser.vue";
export default {
  components: {
    UpdateUser,
  },
  data() {
    return {
      searchName: "",
      searchStatus: "",
      userData: [],
      roleData: [],
      familyData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      comName: "",
      user: {},
    };
  },
  methods: {
    //索引下标计算
    indexMethod(index) {
      return index + 1 + this.pageSize * (this.currentPage - 1);
    },
    //控制子组件
    handleShow() {
      this.comName = "";
      this.finduserList(this.currentPage);
    },
    //处理编辑
    handleEdit(index, row) {
      this.comName = "UpdateUser";
      console.log(row);
      this.user = row;
    },
    //处理删除
    handleDelete(index, row) {
      console.log(row);
      this.$axios
        .get("/api/user/delete", {
          params: { id: row.id },
          headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data.msg);
          if (res.data.status == 200) {
            this.$message({
              type: "success",
              message: "删除成功!",
              offset: 300,
              duration: 1000,
            });
            this.finduserList(this.currentPage);
          } else {
            this.$message({
              type: "error",
              message: "权限不足!",
              offset: 300,
              duration: 1000,
            });
          }
        })
        .catch(() => {});
      this.finduserList(this.currentPage);
    },
    //获取用户列表数据
    finduserList(pno) {
      this.$axios
        .get("/api/user/list?", {
          params: {
            searchName: this.searchName,
            searchStatus: this.searchStatus,
            pageNum: pno,
            pageSize: this.pageSize,
          },
          //  headers: { strToken: window.localStorage.getItem("strToken") },
        })
        .then((res) => {
          console.log(res.data);
          let pageInfo = res.data.data;
          this.userData = pageInfo.list;
          //使用pageInfo.属性
          this.currentPage = pageInfo.pageNum;
          this.total = pageInfo.total;
          this.pageSize = pageInfo.pageSize;
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
    //页尺寸改变
    handleSizeChange(pSize) {
      this.pageSize = pSize;
      this.currentPage = 1;
      this.finduserList(1);
    },
    //当前页码改变
    handleCurrentChange(cPage) {
      this.currentPage = cPage;
      this.finduserList(cPage);
    },
    //条件查询,在职状态改变
    searchStatusChange() {
      this.finduserList(1);
    },
  },
  created() {
    //发送axio请求，获取讲师数据
    this.finduserList(1);
    //获取角色数据
    this.findRoleList();
    //获取科室数据
    this.findFamilyList();
  },
};
</script>

<style lang="less" scoped>
.el-breadcrumb {
  background: #d4dae0;
  padding-left: 20px;
  height: 40px;
  line-height: 40px;
}
</style>
