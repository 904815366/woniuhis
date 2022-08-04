<template>
  <div>
    <!-- 面包屑导航栏 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>收费管理</el-breadcrumb-item>
      <el-breadcrumb-item>收费日结</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 通过住院号查询信息 -->
    <div class="demo-input-suffix">


      <el-input placeholder="请输住院号进行查询" v-model="searchRegisterId" style="width:300px;margin-top:5px">
        <el-button slot="append" icon="el-icon-search" @click="queryregisterById(5)"></el-button>
        <el-button slot="append" icon="el-icon-delete" @click="cleanQuery()"></el-button>
      </el-input>


      <el-select style="margin-left: 10px;" v-model="searchStatus" placeholder="选择状态进行查询"
        @change="modifyStatusChange()">
        <el-option value="查询所有">查询所有</el-option>
        <el-option value="未结算">未结算</el-option>
        <el-option value="已结算">已结算</el-option>
      </el-select>


      <el-date-picker style="margin-left: 10px;" v-model="searchDate" type="date" placeholder="选择日期"
        @change="modifyStatusChange()" value-format="yyyy-MM-dd HH:mm:ss">
      </el-date-picker>


    </div>


    <!-- 病人信息 -->
    <div style="margin-top:10px;padding-top:5px;background-color: white; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">

      <el-divider content-position="left">病人登记的信息</el-divider>

      <!-- 病人入院登记的信息 -->

      <el-descriptions class="margin-top" :column="5" size="medium" style="padding-left:60px ;">

        <el-descriptions-item label="住院号">{{ register.id }}</el-descriptions-item>
        <el-descriptions-item label="姓名">{{ register.name }}</el-descriptions-item>
        <el-descriptions-item label="性别">{{ register.sex }}</el-descriptions-item>
        <el-descriptions-item label="联系方式">{{ register.phone }}</el-descriptions-item>
        <el-descriptions-item label="年龄">{{ register.age }}</el-descriptions-item>
        <el-descriptions-item label="身份证号码">{{ register.card }}</el-descriptions-item>
        <el-descriptions-item label="住址">{{ register.area }}</el-descriptions-item>
        <el-descriptions-item label="门诊诊断">{{ register.diagnose }}</el-descriptions-item>
        <el-descriptions-item label="病房编号">{{ register.wardid }}</el-descriptions-item>
        <el-descriptions-item label="床位编号">{{ register.bedid }}</el-descriptions-item>
        <el-descriptions-item label="余额">{{ register.money }}</el-descriptions-item>
        <el-descriptions-item label="已用">{{ register.used }}</el-descriptions-item>
        <el-descriptions-item label="退款">{{ register.refund }}</el-descriptions-item>
        <el-descriptions-item label="状态">{{ register.status }}</el-descriptions-item>

      </el-descriptions>


      <el-divider content-position="left">病人消费列表</el-divider>

      <!-- 消费列表 -->
      <el-table :data="MoneyList" height="280" style="width: 97%; height:450px; margin-left:30px; " :fit="false">

        <!-- <template slot-scope="scope" v-if="scope.row.status == 0">
          <el-table-column type="selection" width="55" :selectable="selectHandle" align="center">
          </el-table-column>
        </template> -->


        <el-table-column width="50">
          <template slot-scope="scope" v-if="scope.row.status == 0">
            <el-checkbox name="chk" :label="scope.row.id + '-' + scope.row.consummoney" width="35"
              @change="handleSelectionChange()" align="center">&nbsp;</el-checkbox>
          </template>
        </el-table-column>



        <el-table-column prop="id" label="消费编号" width="80" align="center">
        </el-table-column>
        <el-table-column prop="consummoney" label="消费金额(元)" width="200" align="center">
        </el-table-column>
        <el-table-column label="消费时间" width="200" align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.consumtime | dateconverter
            }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="consumpart" label="消费详情" width="200" align="center">
        </el-table-column>

        <!-- <el-table-column prop="type" label="付款方式" width="200" align="center">
        </el-table-column> -->

        <el-table-column label="状态" width="200" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status == 0" style="color:red">未结算</span>
            <span v-if="scope.row.status == 1" style="color:green">已结算</span>
            <span v-if="scope.row.status == 2" style="color:red">申请退费</span>
            <span v-if="scope.row.status == 3" style="color:green">已退费</span>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope" v-if="scope.row.status == 0">
            <el-button size="mini" @click="goSettlement(scope.row)">结算</el-button>
          </template>
        </el-table-column>

        <el-table-column align="right" width="120">
          <template slot="header" slot-scope="scope">
            <!-- <el-input size="mini" placeholder="输入关键字搜索" /> -->
            <el-button slot="append" @click="goSettlements()">批量结算</el-button>

          </template>
        </el-table-column>

      </el-table>


    </div>



  </div>
</template>

<script>
import { type } from 'os';

export default {
  data() {
    return {

      //时间查询
      searchDate: null,
      // 状态查询
      searchStatus: '查询所有',
      //查询的住院信息
      register: {},
      //查询条件
      searchRegisterId: '',
      //消费列表
      MoneyList: [],
      //结算窗口
      settlementFormVisible: false,
      //结算的数据
      settlementData: {
        registerId: '',
        notNum: 0,
        notMoney: 0,
        returnNum: 0,
        returnMoney: 0,
        notMoneyListId: '',
        returnMoneyListId: '',
      },
      //结算窗口大小
      formLabelWidth: '120px',
      //UUID
      idempotentToken: '',

      settlementInfo: '',

      ids: '',
      sumMoney: 0,
    }
  },
  created() {

  },
  methods: {
    //根据ID查询病人入院登记信息
    queryregisterById(status) {
      if (this.searchRegisterId == "") {
        this.$message({
          message: '请先输入住院号!',
          type: 'warning'
        });
        return;
      };
      console.log("进来了" + status);
      this.axios.get("/api/register/queryById/" + this.searchRegisterId + "/" + status).then(res => {
        if (res.data.status === 2000) {
          this.register = res.data.data;
          this.register.status = this.statusNumberFormString(this.register.status);
          this.QueryMoneyListByRegisterId(-1);
        } else {
          this.$message({
            message: '未查询到信息,请先申请出院或住院号不存在!',
            type: 'error'
          });
        }
      }).catch(e => {
        this.$message({
          message: '服务器跑步见了,请稍后再试...!',
          type: 'error'
        });
      });
    },


    //状态查询
    modifyStatusChange() {
      if (this.register.id == null || this.register.id == '') {
        this.$message({
          message: '请先查询住院号!',
          type: 'error'
        });
        return;
      };

      this.QueryMoneyListByRegisterId(this.searchStatus);
    },


    //根据住院号查询病人消费列表
    QueryMoneyListByRegisterId(status) {

      if (status == '查询所有') {
        status = -1;
      } else if (status == '未结算') {
        status = 0;
      } else if (status == '已结算') {
        status = 1;
      }

      // console.log(this.searchDate);
      this.axios.get("/api/moneylist/query/registerIdAndStatus", {
        params: {
          registerId: this.searchRegisterId,
          status: status,
          consumtime: this.searchDate,
        }
      }).then(res => {
        if (res.data.status === 2000) {
          this.MoneyList = res.data.data;
          // console.log(this.MoneyList);
        }
      });
    },

    //打开结算框
    openSettlementFormVisible() {
      if (this.register.id == null) {
        this.$message({
          message: '请先查询住院号才能结算!',
          type: 'warning'
        });
        return;
      }

      for (let i of this.MoneyList) {
        if (i.status == '0') {

          this.settlementData.notNum++;
          this.settlementData.notMoney += i.consummoney;
          this.settlementData.notMoneyListId += i.id + ",";
        }
        if (i.status == '2') {

          this.settlementData.returnNum++;
          this.settlementData.returnMoney += i.consummoney;
          this.settlementData.returnMoneyListId += i.id + ",";
        }
      }

      this.settlementFormVisible = true;
      this.$axios.get("/api/getIdempotentToken").then(res => {
        this.idempotentToken = res.data;
      });
    },


    //关闭结算窗口
    closeSettlementFormVisible() {
      this.settlementFormVisible = false;
      this.settlementData = {
        registerId: '',
        notNum: 0,
        notMoney: 0,
        notMoneyListId: '',

        returnNum: 0,
        returnMoney: 0,
        returnMoneyListId: '',
      };

    },


    //类型转换方法
    statusNumberFormString(obj) {
      let searchStatus = '';
      if (obj == '-1') {
        searchStatus = '查询所有'
      } else if (obj == 0) {
        searchStatus = '未缴费'
      } else if (obj == 1) {
        searchStatus = '申请住院'
      } else if (obj == 2) {
        searchStatus = '住院中'
      } else if (obj == 3) {
        searchStatus = '申请出院'
      } else if (obj == 4) {
        searchStatus = '审核通过'
      } else if (obj == 5) {
        searchStatus = '已出院'
      }
      return searchStatus;
    },


    //清除查询
    cleanQuery() {
      this.register = {};
      this.searchRegisterId = '';
      this.MoneyList = [];
    },

    //结算
    goSettlement(obj) {


      this.$confirm('确认结算码? 将从余额中扣除:' + obj.consummoney + '元, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        obj.status = 1;
        this.$axios.post("/api/moneylist/modifyStatusAndRegisterMoney", obj).then(res => {
          if (res.data.status === 2000) {
            this.$message({
              message: '结算完成',
              type: 'success',
            });
            this.queryregisterById(5);
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消结算'
        });
      });

    },


    //批量结算
    goSettlements() {
      let arr = document.getElementsByName("chk");
      let chknum = 0;
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].checked == true) {
          chknum++;
        }
      }
      if (chknum == 0) {
        this.$message({
          message: '请至少选择一个,才能批量结算!',
          type: 'warning',
        });
        return;
      }


      this.$confirm('确认批量结算码? 将从余额中扣除:' + this.sumMoney + '元, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          ids: this.ids,
          sumMoney: this.sumMoney,
          status: 1,
          registerid: this.register.id,
        }
        this.$axios.post("/api/moneylist/modifyStatusAndRegisterMoneys", data).then(res => {
          if (res.data.status === 2000) {
            this.$message({
              message: '批量结算完成',
              type: 'success',
            });
            this.queryregisterById(5);
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消结算'
        });
      });

    },


    selectHandle(row) {
      return true;
    },
    handleSelectionChange() {
      this.ids = '';
      this.sumMoney = 0;
      let arr = document.getElementsByName("chk");
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].checked == true) {
          let obj = arr[i].value.split("-");
          this.ids += obj[0] + "-";
          this.sumMoney += Number(obj[1]);
        }
      }
      this.ids = this.ids.substring(0, this.ids.length - 1);
      // this.sumMoney = this.sumMoney.substring(0, this.sumMoney.length - 1);
      // console.log(this.ids);
      // console.log(this.sumMoney);
    },

  }
}
</script>

<style>
</style>
