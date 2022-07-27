<template>
  <div>
    <!-- 面包屑导航栏 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>收费管理</el-breadcrumb-item>
      <el-breadcrumb-item>入院管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 条件查询 -->
    <div class="demo-input-suffix">
      <el-col :span="6" style="margin-top:5px">
        <el-input placeholder="请输入姓名关键字内容" v-model="searchName" :width="100">
          <el-button slot="append" icon="el-icon-search" @click="queryPageInfo(1)"></el-button>
          <el-button slot="append" icon="el-icon-delete" @click="searchName = ''"></el-button>
        </el-input>
      </el-col>

      <el-col :span="6" style="margin-top:5px;margin-left: 10px;">
        <el-select v-model="searchStatus" placeholder="选择状态进行查询" @change="queryPageInfo(1)">
          <el-option value="查询所有">查询所有</el-option>
          <el-option value="未缴费">未缴费</el-option>
          <el-option value="已缴费">已缴费</el-option>
          <el-option value="已入院">已入院</el-option>
        </el-select>

        <!-- 添加入院登记信息 -->
        <el-button @click="drawer = true" type="primary" style="margin-left: 16px;" class="el-icon-circle-plus">
        </el-button>
      </el-col>



      <el-drawer title="我是外面的 Drawer" :visible.sync="drawer" size="50%">
        <div>
          <el-button @click="innerDrawer = true">打开里面的!</el-button>
          <el-drawer title="我是里面的" :append-to-body="true" :before-close="handleClose" :visible.sync="innerDrawer">
            <p>_(:зゝ∠)_</p>
          </el-drawer>
        </div>
      </el-drawer>
    </div>

    <!-- 入院登记列表 -->
    <el-table :data="tableData.list" style="width: 100%; height:450px; margin-top:50px;" :fit="false">

      <el-table-column prop="id" label="住院号" width="70" align="center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80" align="center">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="50" align="center">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="50" align="center">
      </el-table-column>
      <el-table-column prop="phone" label="联系方式" width="100" align="center">
      </el-table-column>
      <el-table-column prop="card" label="身份证" width="180" align="center">
      </el-table-column>
      <el-table-column prop="area" label="住址" width="250" align="center">
      </el-table-column>
      <el-table-column prop="diagnose" label="病因" width="100" align="center">
      </el-table-column>

      <el-table-column prop="money" label="余额" width="100" align="center">
      </el-table-column>

      <el-table-column label="收费人员" width="150" align="center">
        <template slot-scope="scope">
          <ul v-for="user of userList" :key="user.id">
            <span v-if="scope.row.chargeid == user.id">{{ user.name }}</span>
          </ul>
        </template>
      </el-table-column>

      <el-table-column label="状态" width="100" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 0">未缴费</span>
          <span v-if="scope.row.status == 1">已缴费</span>
          <span v-if="scope.row.status == 2">已入院</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <el-pagination small layout="prev, pager, next , total " :total="tableData.total" next-text="下一页" prev-text="上一页"
      :page-size="pageSize" @current-change="queryPageInfo" :current-page="pageNum">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageNum: 1,
      pageSize: 5,
      searchName: '',
      searchStatus: "",
      tableData: {},
      userList: [],
      drawer: false,
      innerDrawer: false,
    }
  },
  created() {
    this.queryPageInfo(1);
    this.queryUserListByRoleId();
  },
  methods: {
    queryPageInfo(pageNum) {
      this.pageNum = pageNum;
      let searchStatus = '';
      if (this.searchStatus == '查询所有') {
        searchStatus = -1
      } else if (this.searchStatus == '未缴费') {
        searchStatus = 0
      } else if (this.searchStatus == '已缴费') {
        searchStatus = 1
      } else if (this.searchStatus == '已入院') {
        searchStatus = 2
      }
      this.$axios.get("/api/register/queryPageInfo", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.searchName,
          status: searchStatus,
        }
      }).then(res => {
        if (res.data.status === 2000) {
          this.tableData = res.data.data;
          this.pageNum = res.data.data.pageNum;
          // console.log(res.data.data);
        } else {
          console.log("列表加载失败");
        }
      })
    },

    //  查询收费人员列表
    queryUserListByRoleId() {
      this.$axios.get("/api/register/queryUserListByRoleId").then(res => {
        this.userList = res.data.data;
        console.log(this.userList);
      })
    }
  },

  handleClose(done) {
    this.$confirm('还有未保存的工作哦确定关闭吗？')
      .then(_ => {
        done();
      })
      .catch(_ => { });
  }

}
</script>

<style>
</style>
