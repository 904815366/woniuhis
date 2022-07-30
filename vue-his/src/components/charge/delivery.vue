<template>
  <div>
    <!-- 面包屑导航栏 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>收费管理</el-breadcrumb-item>
      <el-breadcrumb-item>预交缴费</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 通过住院号查询信息 -->
    <div class="demo-input-suffix">
      <!-- <el-col :span="6" style="margin-top:5px"> -->
      <el-input placeholder="请输住院号进行查询" v-model="searchpatientid" style="width:300px;margin-top:5px">
        <el-button slot="append" icon="el-icon-search" @click="queryregisterById()"></el-button>
        <el-button slot="append" icon="el-icon-delete" @click="cleanQuery()"></el-button>
      </el-input>

      <!-- 开启添加缴费记录框按钮 -->
      <el-button size="medium" @click="openAddFormVisible()" type="primary" style="margin-left: 16px;"
        class="el-icon-circle-plus">
      </el-button>


      <el-dialog title="添加缴费记录" :visible.sync="addFormVisible" width="30%">
        <el-form :model="moneyrecord">

          <el-form-item label="缴 费 人:" :label-width="formLabelWidth">
            <el-input :value="register.name" autocomplete="off" :disabled="true" style="width: 150px"></el-input>
          </el-form-item>

          <el-form-item label="缴费金额:" :label-width="formLabelWidth">
            <el-input v-model="moneyrecord.prepaymoney" autocomplete="off" style="width: 150px">
            </el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addMoneyrecord()">确 定</el-button>
        </div>
      </el-dialog>

    </div>




    <!-- 病人信息 -->
    <div style="margin-top:10px;padding-top:10px;background-color: white; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
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

      <el-divider content-position="left">病人缴费记录列表</el-divider>

      <!-- 预交缴费列表 -->
      <el-table :data="MoneyrecordList" height="280" style="width: 100%; height:450px; margin-left:30px; " :fit="false">

        <el-table-column prop="id" label="收费单编号" width="100" align="center">
        </el-table-column>
        <el-table-column prop="prepaymoney" label="缴费金额(元)" width="200" align="center">
        </el-table-column>
        <el-table-column label="缴费时间" width="200" align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.prepaytime | dateconverter }}</span>
          </template>
        </el-table-column>

        <el-table-column label="收费人员" width="150" align="center">
          <template slot-scope="scope">
            <ul v-for="user of userList" :key="user.id">
              <span v-if="scope.row.userid == user.id">{{ user.name }}</span>
            </ul>
          </template>
        </el-table-column>

        <!--    
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column> -->
      </el-table>

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
      userList: [],
      //添加窗口
      addFormVisible: false,
      moneyrecord: {},
      formLabelWidth: '120px',
      idempotentToken: '',
    }
  },
  created() {
    this.queryUserListByRoleId();
  },
  methods: {
    //根据ID查询病人入院登记信息
    queryregisterById() {
      this.axios.get("/api/register/queryById/" + this.searchpatientid + "/1").then(res => {
        if (res.data.status === 2000) {
          this.register = res.data.data;
          this.register.status = this.statusNumberFormString(this.register.status);
          this.queryMoneyrecordList();
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
    //  查询收费人员列表
    queryUserListByRoleId() {
      this.$axios.get("/api/register/queryUserListByRoleId").then(res => {
        this.userList = res.data.data;
        console.log(this.userList);
      })
    },

    //清除查询
    cleanQuery() {
      this.register = {};
      this.searchpatientid = '';
      this.MoneyrecordList = [];
    },


    //打开添加框
    openAddFormVisible() {
      if (this.register.id == null) {
        this.$message({
          message: '请先查询住院号才能添加!',
          type: 'warning'
        });
        return;
      }
      this.addFormVisible = true;
      this.$axios.get("/api/getIdempotentToken").then(res => {
        this.idempotentToken = res.data;
      });
    },


    //  添加缴费信息
    addMoneyrecord() {
      this.moneyrecord.registerid = this.register.id;
      this.moneyrecord.prepaytime = new Date();
      this.moneyrecord.userid = window.sessionStorage.getItem("currentUserId");
      this.$axios({
        url: "/api/moneyrecord/insertOne",
        method: 'post',
        headers: { addMoneyrecord: this.idempotentToken },
        data: this.moneyrecord,
      }).then(res => {
        if (res.data.status === 2000) {
          this.$message({
            message: '添加成功',
            type: 'succeed'
          });
          this.addFormVisible = false;
          this.queryregisterByIdAndqueryMoneyrecordList();
          this.moneyrecord = {};
        }
      }).catch(e => {
        console.log(res);
        this.$message({
          message: '服务器跑不见了.....',
          type: 'error'
        });
      })
    }

  }
}
</script>

<style>
</style>
