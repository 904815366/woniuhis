<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>
        <router-link to="/drug/drugreturn">退药列表</router-link>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-row style="margin-top: 20px">
      <el-col :span="5">
        <el-input placeholder="请输入住院编号" v-model="searchId"> </el-input>
      </el-col>
      <el-col :span="6">
        <el-input placeholder="请输入患者姓名" v-model="searchName">
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getDrugReturnList(1)"
          ></el-button>
        </el-input>
      </el-col>
      <el-button type="success" round @click="handleReturn" style="float: right"
        >通过</el-button
      >
      <el-button type="danger" round @click="handleRefuse" style="float: right"
        >退回</el-button
      >
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%; margin-top: 10px"
      max-height="100%"
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        :selectable="selectHandle"
        align="center"
      >
      </el-table-column>
      <el-table-column
        type="index"
        :index="indexMethod"
        label="序号"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column prop="registerid" label="住院编号" width="80" align="center">
      </el-table-column>
      <el-table-column prop="patientname" label="患者姓名" width="120" align="center">
      </el-table-column>
      <el-table-column label="科室" width="100" align="center">
        <template slot-scope="scope">
          <span v-for="family in familyList" :key="family.id">
            <span v-if="family.id == scope.row.familyid">{{ family.familyname }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="开立医生" width="100" align="center">
        <template slot-scope="scope">
          <span v-for="user in userList" :key="user.id">
            <span v-if="user.id == scope.row.doctorid">{{ user.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="执行护士" width="150" align="center">
        <template slot-scope="scope">
          <span v-for="user in userList" :key="user.id">
            <span v-if="user.id == scope.row.nurseid">{{ user.name }}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="applytime" label="申请时间" width="180" align="center">
      </el-table-column>
      <el-table-column label="药品名" width="123" align="center">
        <template slot-scope="scope">
          <span v-for="drug in drugList" :key="drug.id">
            <span v-if="drug.id == scope.row.drugid">
              {{ drug.name }}
            </span>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="num" label="数量" width="120" align="center">
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
      searchId: "",
      pageNum: 1,
      pageSize: 5,
      totalCount: 0,
      pageSizes: [5, 10, 15, 20],
      searchStatus: "",
      drug: {}, //用于存放要编辑的drug
      familyList: [], //用于存放科室列表
      userList: [], //用于存放用户列表
      drugList: [], //用于存放药品列表
      ids: [],
      userid: "",
      nameAndId: [],
    };
  },
  created() {
    this.getDrugReturnList(1);
    this.queryFamilyList();
    this.queryUserList();
    this.queryDrugList();
  },
  methods: {
    selectHandle(row) {
      return true;
    },
    handleSelectionChange(val) {
      this.ids = [];
      for (let i = 0; i < val.length; i++) {
        this.ids[i] = val[i].id;
      }
      console.log(this.ids);
    },
    //退药
    handleRefuse() {
      if (this.ids == []) {
        this.$message({
          showClose: true,
          message: "请先选择要提交的退药记录!",
          type: "error",
          duration: 1000, //显示的时间,ms
        });
      } else {
        let result = this.ids.join(",");
        this.$axios.get("/api/drugreturn/handlerefuse?ids=" + result).then((res) => {
          if (res.data.status == 200) {
            this.$message({
              showClose: true,
              message: "退回成功",
              type: "success",
              center: true,
            });
          } else {
            this.$message({
              showClose: true,
              message: "服务异常!",
              type: "error",
              duration: 1000, //显示的时间,ms
            });
          }
          this.getDrugReturnList(this.pageNum);
        });
      }
    },
    //退药
    handleReturn() {
      if (this.ids == []) {
        this.$message({
          showClose: true,
          message: "请先选择要提交的退药记录!",
          type: "error",
          duration: 1000, //显示的时间,ms
        });
      } else {
        let result = this.ids.join(",");
        this.userid = window.sessionStorage.getItem("currentUserId", this.nameAndId[1]);
        this.$axios
          .get("/api/drugreturn/handlereturn?ids=" + result + "&userid=" + this.userid)
          .then((res) => {
            if (res.data.status == 200) {
              this.$message({
                showClose: true,
                message: "提交成功",
                type: "success",
                center: true,
              });
            } else {
              this.$message({
                showClose: true,
                message: "服务异常!",
                type: "error",
                duration: 1000, //显示的时间,ms
              });
            }
            this.getDrugReturnList(this.pageNum);
          });
      }
    },
    //为type=index 属性指定生成规则
    indexMethod(index) {
      return index + 1 + this.pageSize * (this.pageInfo.pageNum - 1);
    },
    getDrugReturnList(pNum) {
      this.$axios
        .get("/api/drugreturn/returnlist", {
          params: {
            pageInfo: {},
            searchName: this.searchName,
            searchId: this.searchId,
            searchStatus: this.searchStatus,
            pageNum: pNum,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          console.log(res.data.data);
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
    //查询药品列表
    queryDrugList() {
      this.$axios
        .get("/api/drug/druglist")
        .then((res) => {
          this.drugList = res.data.data;
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
    //页码尺寸改变
    handleSizeChange(pSize) {
      this.pageSize = pSize;
      this.pageNum = 1; //默认为第一页
      this.getDrugReturnList(1); //初始化为第一页
    },
    // 当前页码改变
    handleCurrentChange(pNo) {
      this.pageNum = pNo;
      this.getDrugReturnList(pNo); //翻页
    },
  },
};
</script>

<style></style>
