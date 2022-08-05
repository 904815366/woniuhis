<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>
        <router-link to="/drug/list">药品列表</router-link>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-row style="margin-top: 20px">
      <el-col :span="5">
        <el-input placeholder="请输入查询药品的批次" v-model="searchBatch"> </el-input>
      </el-col>
      <el-col :span="6">
        <el-input placeholder="请输入查询药品的名字" v-model="searchName">
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="getDrugList(1)"
          ></el-button>
        </el-input>
      </el-col>
      <el-button type="success" round @click="handleAdd" style="float: right"
        >添加</el-button
      >
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%; margin-top: 10px"
      max-height="100%"
      stripe
      @cell-dblclick="dbclick"
    >
      <el-table-column prop="id" label="药品编号" width="80" align="center">
      </el-table-column>
      <el-table-column prop="name" label="药品名" width="120" align="center">
      </el-table-column>
      <el-table-column prop="batch" label="批次" width="120" align="center">
      </el-table-column>
      <el-table-column prop="type" label="类型" width="80" align="center">
      </el-table-column>
      <el-table-column prop="price" label="价格" width="80" align="center">
      </el-table-column>
      <el-table-column
        prop="num"
        label="库存"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column prop="alarmnum" label="报警库存" width="120" align="center">
        <template slot-scope="scope"
          >{{ scope.row.alarmnum }}
          <el-button
            circle
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
          ></el-button>
        </template>
      </el-table-column>
      <el-table-column prop="producer" label="厂商" width="150" align="center">
      </el-table-column>
      <el-table-column prop="producing" label="产地" width="80" align="center">
      </el-table-column>
      <el-table-column prop="mdfunction" label="功效" width="120" align="center">
      </el-table-column>
      <el-table-column prop="date" label="生产时间" width="120" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.protime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="过期时间" width="120" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.validtime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="memo" label="备注" width="120" align="center">
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

    <!-- 显示子组件 -->
    <component
      :is="comName"
      :objDrug="drug"
      @func="handleShow"
      @funreload="handlereload"
      :pageNum="pageNum"
    ></component>
  </div>
</template>

<script>
//导入子组件
import DrugEdit from "./DrugEdit.vue";
import DrugAdd from "./DrugAdd.vue";
export default {
  inject: ["reload"],
  components: {
    //注册子组件
    DrugEdit,
    DrugAdd,
  },
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
    };
  },
  created() {
    this.getDrugList(1);
  },
  methods: {
    handlereload() {
      clearTimeout(this.timer);
      this.timer = setTimeout(() => {
        this.getDrugList(this.pageNum);
      }, 500);
    },
    handleShow() {
      console.log("执行handleShow");
      this.comName = "";
    },
    getDrugList(pNum) {
      console.log("执行getDrugList");
      console.log(this.searchName);
      this.$axios
        .get(
          "/api/drug/list?searchName=" +
            this.searchName +
            "&pageNum=" +
            pNum +
            "&pageSize=" +
            this.pageSize +
            "&searchBatch=" +
            this.searchBatch
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
    handleAdd() {
      this.drug = {};
      this.comName = DrugAdd;
    },
    handleEdit(row) {
      this.drug = row;
      this.comName = DrugEdit;
    },
    dbclick(row, column) {
      console.log("触发双击事件");
      this.handleEdit(row);
    },
  },
};
</script>

<style></style>
