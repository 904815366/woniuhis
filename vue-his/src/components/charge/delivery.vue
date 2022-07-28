<template>
  <div>
    <!-- 面包屑导航栏 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>收费管理</el-breadcrumb-item>
      <el-breadcrumb-item>预交缴费</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 条件查询 -->
    <div class="demo-input-suffix">
      <!-- <el-col :span="6" style="margin-top:5px"> -->
      <el-input placeholder="请输住院号进行查询" v-model="searchpatientid" style="width: 300px;margin-top:5px">
        <el-button slot="append" icon="el-icon-search" @click="queryregisterByIdAndqueryMoneyrecordList()"></el-button>
        <el-button slot="append" icon="el-icon-delete" @click="searchpatientid = '', register = ''"></el-button>
      </el-input>

    </div>


    <div style="margin-top:10px;padding-top:10px;background-color: white; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">

      <!-- 病人入院登记的inxi -->
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
          <el-input autocomplete="off" style="width: 100px; " v-model="register.money" :disabled="true">
          </el-input>
        </el-form-item>

        <el-form-item label="状态:" style="display:inline-block">
          <el-input autocomplete="off" style="width: 100px; " v-model="register.status" :disabled="true">
          </el-input>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      register: {},
      searchpatientid: '',
      MoneyrecordList: [],
    }
  },
  created() {

  },
  methods: {
    //根据ID查询病人入院登记信息
    queryregisterById() {
      this.axios.get("/api/register/queryById/" + this.searchpatientid).then(res => {
        if (res.data.status === 2000) {
          this.register = res.data.data;
          this.register.status = this.statusNumberFormString(this.register.status);
        } else {
          this.$message({
            message: '住院号不存在,未查询到信息!',
            type: 'error'
          });
        }
      });
    },
    //根据ID查询病人预缴列表
    queryMoneyrecordList() {
      this.axios.get("/api/moneyrecord/ListById/" + this.searchpatientid).then(res => {
        if (res.data.status === 2000) {
          this.MoneyrecordList = res.data.data;
          console.log(this.MoneyrecordList);
        }
      });
    },

    queryregisterByIdAndqueryMoneyrecordList() {
      this.queryregisterById();
      this.queryMoneyrecordList();

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
        searchStatus = '入院中'
      } else if (obj == 3) {
        searchStatus = '申请出院'
      } else if (obj == 4) {
        searchStatus = '审核通过'
      } else if (obj == 5) {
        searchStatus = '已出院'
      }
      return searchStatus;
    },
  }
}
</script>

<style>
</style>
