<template>
  <div>
    <!-- 面包屑导航栏 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>收费管理</el-breadcrumb-item>
      <el-breadcrumb-item>出院结算</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 通过住院号查询信息 -->
    <div class="demo-input-suffix">
      <!-- <el-col :span="6" style="margin-top:5px"> -->
      <el-input placeholder="请输住院号进行查询" v-model="searchRegisterId" style="width:300px;margin-top:5px">
        <el-button slot="append" icon="el-icon-search" @click="queryregisterById()"></el-button>
        <el-button slot="append" icon="el-icon-delete" @click="cleanQuery()"></el-button>
      </el-input>

      <!-- 开启结算框按钮 -->
      <el-button size="medium" @click="openSettlementFormVisible()" type="primary" style="margin-left: 16px;">点击进行结算
      </el-button>

      <el-dialog title="结算窗口" :visible.sync="settlementFormVisible" width="30%">
        <el-form :model="settlementData">

          <el-form-item label="缴 费 人:" :label-width="formLabelWidth">
            <span style="font-weight:900;font-size: 20px;">{{ register.name }}</span>
          </el-form-item>

          <el-form-item label="未结算项:" :label-width="formLabelWidth">
            <span style="font-weight:900;font-size: 20px;">{{ settlementData.num }}</span>
          </el-form-item>

          <el-form-item label="未结算项总金额:" :label-width="formLabelWidth">
            <span style="font-weight:900; font-size: 20px; color:red">{{ settlementData.sumMoney }}元</span>
          </el-form-item>

          <el-form-item>
            <span style="color:red">{{ settlementInfo }}</span>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeSettlementFormVisible()">取 消</el-button>
          <el-button type="primary" @click="addSettlementData()">结算出院</el-button>
        </div>
      </el-dialog>

      <!-- 病人信息 -->
      <div
        style="margin-top:10px;padding-top:10px;background-color: white; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
        <el-divider content-position="left">病人登记的信息</el-divider>
        <!-- 病人入院登记的信息 -->
        <el-form ref="form" :model="register" label-width="100px">

          <el-form-item label="住院号:" style="display:inline-block">
            <el-input v-model="register.id" autocomplete="off" style="width: 50px;" :disabled="true">
            </el-input>

          </el-form-item>

          <el-form-item label="姓名:" style="display:inline-block">
            <el-input v-model="register.name" autocomplete="off" style="width: 150px" :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="性别:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 50px;" v-model="register.sex" :disabled="true">
            </el-input>

          </el-form-item>

          <el-form-item label="联系方式:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 130px;" v-model="register.phone" :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="年龄:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 50px;" v-model="register.age" :disabled="true">
            </el-input>
          </el-form-item>



          <el-form-item label="身份证号码:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 180px;" v-model="register.card" :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="住址:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 300px; " v-model="register.area" :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="门诊诊断:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 280px; " v-model="register.diagnose" :disabled="true">
            </el-input>
          </el-form-item>

          <el-form-item label="余额:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 100px; color:red; " v-model="register.money" :disabled="true">
            </el-input>

          </el-form-item>

          <el-form-item label="状态:" style="display:inline-block">
            <el-input autocomplete="off" style="width: 100px; " v-model="register.status" :disabled="true">
            </el-input>
          </el-form-item>
        </el-form>

        <el-divider content-position="left">病人消费列表</el-divider>

        <!-- 预交缴费列表 -->
        <el-table :data="MoneyList" height="280" style="width: 100%; height:450px; margin-left:30px; " :fit="false">

          <el-table-column prop="id" label="消费编号" width="100" align="center">
          </el-table-column>
          <el-table-column prop="consummoney" label="消费金额(元)" width="200" align="center">
          </el-table-column>
          <el-table-column label="消费时间" width="200" align="center">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.consumtime | dateconverter }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="consumpart" label="消费详情" width="200" align="center">
          </el-table-column>

          <el-table-column label="状态" width="200" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.status == 0" style="color:red">未结算</span>
              <span v-if="scope.row.status == 1" style="color:green">已结算</span>

            </template>
          </el-table-column>

        </el-table>


      </div>

    </div>

  </div>
</template>

<script>
import { type } from 'os';

export default {
  data() {
    return {
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
        num: 0,
        sumMoney: 0,
        moneyListId: '',
      },
      //结算窗口大小
      formLabelWidth: '120px',
      //UUID
      idempotentToken: '',

      settlementInfo: '',
    }
  },
  created() {

  },
  methods: {
    //根据ID查询病人入院登记信息
    queryregisterById() {
      this.axios.get("/api/register/queryById/" + this.searchRegisterId + "/3").then(res => {
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


    //根据住院号查询病人消费列表
    QueryMoneyListByRegisterId(status) {
      this.axios.get("/api/moneylist/query/registerIdAndStatus", {
        params: {
          registerId: this.searchRegisterId,
          status: status,
        }
      }).then(res => {
        if (res.data.status === 2000) {
          this.MoneyList = res.data.data;
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
          console.log(i);
          this.settlementData.num++;
          this.settlementData.sumMoney += i.consummoney;
          this.settlementData.moneyListId += i.id + ",";
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
        register: '',
        num: 0,
        sumMoney: 0,
        moneyListId: '',
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
        searchStatus = '已缴费'
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
    addSettlementData() {
      if (this.settlementData.sumMoney > this.register.money) {
        this.settlementInfo = '余额不足! 请先到缴费窗口缴费. 当前余额:' + this.register.money + '元 , 结算所需金额:' + this.settlementData.sumMoney + '元, 需缴费:' + (this.settlementData.sumMoney - this.register.money) + '元.';
        return;
      }
      this.settlementData.registerId = this.register.id;
      console.log(this.settlementData.moneyListId);
      this.$axios({
        url: "/api/register/settlement",
        method: "post",
        headers: { modifyRegisterAndMoneyList: this.idempotentToken },
        data: this.settlementData,
      }).then(res => {
        if (res.data.status === 2000) {
          this.$message({
            message: '结算完成,出院审核通过',
            type: 'success',
          });
          this.QueryMoneyListByRegisterId(-1);
        } else {
          this.$message({
            message: '结算失败',
            type: 'erorr',
          });
        }
      }).catch(e => {
        this.$message({
          message: '服务器跑不见了.....',
          type: 'erorr',
        })
      });
    }
  }
}
</script>

<style>
</style>
