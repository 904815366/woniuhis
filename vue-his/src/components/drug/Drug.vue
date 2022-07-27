<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>
        <router-link to="/drug/list">药品列表</router-link>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-row style="margin-top: 10px">
      <el-col :span="8">
        <el-input placeholder="请输入查询药品的名字" v-model="searchName">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getDrugList(1)"
          ></el-button>
        </el-input>
      </el-col>
      <el-col :span="8"> </el-col>
    </el-row>
    <el-table :data="tableData" style="width: 100%; margin-top: 10px" max-height="100%">
      <el-table-column prop="id" label="药品id" width="120"> </el-table-column>
      <el-table-column prop="name" label="姓名" width="120"> </el-table-column>
      <el-table-column prop="batch" label="批次" width="120"> </el-table-column>
      <el-table-column prop="type" label="类型" width="120"> </el-table-column>
      <el-table-column prop="price" label="价格" width="120"> </el-table-column>
      <el-table-column prop="num" label="库存" width="120"> </el-table-column>
      <el-table-column prop="alarmnum" label="报警库存" width="120"> </el-table-column>
      <el-table-column prop="producer" label="厂商" width="120"> </el-table-column>
      <el-table-column prop="producing" label="产地" width="120"> </el-table-column>
      <el-table-column prop="mdfunction" label="功效" width="120"> </el-table-column>
      <el-table-column prop="date" label="生产时间" width="120">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.protime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="过期时间" width="120">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.validtime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="memo" label="备注" width="120"> </el-table-column>
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
      pageNum: 1,
      pageSize: 5,
      totalCount: 0,
      pageSizes: [5, 10, 15, 20],
    };
  },
  created() {
    this.getDrugList(1);
  },
  methods: {
    getDrugList(pNum) {
      console.log(this.searchName);
      this.$axios
        .get(
          "/api/drug/list?searchName=" +
            this.searchName +
            "&pageNum=" +
            pNum +
            "&pageSize=" +
            this.pageSize
        )
        .then((res) => {
          console.log(res.data);
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
      this.getDrugList(1); //初始化为第一页
    },
    // 当前页码改变
    handleCurrentChange(pNo) {
      this.pageNum = pNo;
      this.getDrugList(pNo); //翻页
    },
  },
};
</script>

<style></style>
