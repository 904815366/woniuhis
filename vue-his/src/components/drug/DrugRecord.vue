<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>
        <router-link to="/drugrecord/list">发放记录</router-link>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-row style="margin-top: 20px">
      <el-col :span="2">
        <el-select @change="searchTypeChange" v-model="searchType">
          <el-option label="不限" value=""></el-option>
          <el-option label="发药" value="0"></el-option>
          <el-option label="退药" value="2"></el-option>
        </el-select>
      </el-col>
      <el-col :span="4">
        <el-select @change="searchFamilyChange" v-model="searchFamilyid">
          <el-option label="科室不限" value=""></el-option>
          <el-option
            v-for="family in familyList"
            :key="family.id"
            :label="family.familyname"
            :value="family.id"
          ></el-option>
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-input placeholder="请输入查询药品的名字" v-model="searchName">
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getRecordList(1)"
          ></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%; margin-top: 10px"
      max-height="100%"
      stripe
    >
      <el-table-column
        type="index"
        :index="indexMethod"
        label="序号"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column prop="drugid" label="药品编号" width="80" align="center">
      </el-table-column>
      <el-table-column prop="name" label="药品名" width="120" align="center">
      </el-table-column>
      <el-table-column prop="num" label="数量" width="120" align="center">
      </el-table-column>
      <el-table-column prop="date" label="时间" width="180" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.time }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作人员" width="100" align="center">
        <template slot-scope="scope">
          <span v-for="user in userList" :key="user.id">
            <span v-if="user.id == scope.row.userid">{{ user.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="科室" width="100" align="center">
        <template slot-scope="scope">
          <span v-for="family in familyList" :key="family.id">
            <span v-if="family.id == scope.row.familyid">{{ family.familyname }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="申请医生" width="100" align="center">
        <template slot-scope="scope">
          <span v-for="user in userList" :key="user.id">
            <span v-if="user.id == scope.row.doctorid">{{ user.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="申请护士" width="100" align="center">
        <template slot-scope="scope">
          <span v-for="user in userList" :key="user.id">
            <span v-if="user.id == scope.row.nurseid">{{ user.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="type" label="类型" width="120" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type == '2'"> 退药 </el-tag>
          <el-tag type="success" v-if="scope.row.type == '0'"> 发药 </el-tag>
        </template>
      </el-table-column>
    </el-table>
    <!--background是否显示背景色,layout显示分特的布局组件,prev上一下next下一页pager导航页码sizes每页记录数
            total设置总记录数,page-size梅特记录数,current-page当前页码
          -->
    <el-pagination
      :background="true"
      layout="prev, pager, next,sizes,jumper,->,total"
      prev-text="上一页"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      next-text="下一页"
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :current-page="pageNum"
      :total="totalCount"
      style="margin-top: 10px"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      searchName: "",
      searchBatch: "",
      pageNum: 1,
      pageSize: 5,
      totalCount: 0,
      pageSizes: [5, 10, 15, 20],
      comName: "",
      drug: {}, //用于存放要编辑的drug
      searchType: "",
      familyList: [], //用于存放科室列表
      searchFamilyid: "",
    };
  },
  created() {
    this.getRecordList(1);
    this.queryUserList();
    this.queryFamilyList();
  },
  methods: {
    queryFamilyList() {
      this.$axios
        .get("/api/family/list")
        .then((res) => {
          console.log(res.data.data);
          this.familyList = res.data.data;
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
    indexMethod(index) {
      return index + 1 + this.pageSize * (this.pageInfo.pageNum - 1);
    },
    //查询用户列表
    queryUserList() {
      this.$axios
        .get("/api/drugout/userlist")
        .then((res) => {
          this.userList = res.data.data;
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
    //条件查询
    searchTypeChange() {
      this.getRecordList(1);
    },
    searchFamilyChange() {
      this.getRecordList(1);
    },
    getRecordList(pNum) {
      this.$axios
        .get(
          "/api/drugrecord/list?searchName=" +
            this.searchName +
            "&pageNum=" +
            pNum +
            "&pageSize=" +
            this.pageSize +
            "&searchType=" +
            this.searchType +
            "&searchFamilyid=" +
            this.searchFamilyid
        )
        .then((res) => {
          console.log(res.data);
          this.pageInfo = res.data.data;
          this.tableData = res.data.data.list;
          this.totalCount = res.data.data.total;
          this.pageNum = res.data.data.pageNum;
          this.pageSize = res.data.data.pageSize;
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
    //页码尺寸改变
    handleSizeChange(pSize) {
      this.pageSize = pSize;
      this.pageNum = 1; //默认为第一页
      this.getRecordList(1); //初始化为第一页
    },
    // 当前页码改变
    handleCurrentChange(pNo) {
      this.pageNum = pNo;
      this.getRecordList(pNo); //翻页
    },
    handleEdit(row) {
      this.drug = row;
      this.comName = DrugEdit;
    },
  },
};
</script>

<style></style>
