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
        <el-button @click="drawerOpen()" type="primary" style="margin-left: 16px;" class="el-icon-circle-plus">
        </el-button>
      </el-col>


      <!-- 添加入院登记信息框 -->
      <el-drawer title="入院信息登记" :visible.sync="drawer" size="60%">
        <div>

          <!-- 预约列表按钮 -->
          <el-button @click="innerDrawerOpen()" style="margin-left:100px;margin-bottom: 10px;">打开预约列表</el-button>

          <!-- 预约列表框 -->
          <el-drawer title="病人预约列表" :append-to-body="true" :before-close="handleClose" :visible.sync="innerDrawer">
            <el-table :data="appointmentList" :fit="false">
              <el-table-column prop="patientid" label="编号" width="80" align="center">
              </el-table-column>

              <el-table-column prop="name" label="姓名" width="80" align="center">
              </el-table-column>

              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button size="mini" @click="optionAppointment(scope.row)">选择</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-drawer>

          <!-- 入院登记列表 -->
          <el-form ref="form" :model="addregister" label-width="100px">
            <el-form-item label="病人编号:">
              <el-input disabled v-model="addregister.patientid" autocomplete="off" style="width: 50px;">
              </el-input>

            </el-form-item>

            <el-form-item label="姓名:" style="display:inline-block">
              <el-input v-model="addregister.name" autocomplete="off" style="width: 300px" placeholder="请输入姓名">
              </el-input>
            </el-form-item>

            <el-form-item label="性别:" style="display:inline-block">
              <el-radio v-model="addregister.sex" label="男" border size="medium">男</el-radio>
              <el-radio v-model="addregister.sex" label="女" border size="medium">女</el-radio>
            </el-form-item>

            <el-form-item label="联系方式:" style="display:inline-block">
              <el-input autocomplete="off" style="width: 300px;" v-model="addregister.phone" placeholder="请输入联系方式">
              </el-input>
            </el-form-item>

            <el-form-item label="年龄:" style="display:inline-block">
              <el-input autocomplete="off" style="width: 120px;" v-model="addregister.age" placeholder="请输入年龄">
              </el-input>
            </el-form-item>



            <el-form-item label="身份证号码:" style="display:inline-block">
              <el-input autocomplete="off" style="width: 300px;" v-model="addregister.card" placeholder="请输入身份证号码">
              </el-input>
            </el-form-item>

            <el-form-item label="住址:" style="display:inline-block">
              <el-input autocomplete="off" style="width: 300px; " v-model="addregister.area" placeholder="请输入住址">
              </el-input>
            </el-form-item>

            <el-form-item label="门诊诊断:" style="display:inline-block">
              <el-input autocomplete="off" style="width: 300px; " v-model="addregister.diagnose" placeholder="请输入门诊诊断">
              </el-input>
            </el-form-item>

            <el-form-item label="预缴金额:" style="display:inline-block">
              <el-input autocomplete="off" style="width: 300px; " v-model="addregister.money" placeholder="请输入预缴金额">
              </el-input>
            </el-form-item>

            <el-form-item label="状态:">
              <el-select style="width: 300px; " v-model="addregister.status" placeholder="请输入状态">
                <el-option value="未缴费">未缴费</el-option>
                <el-option value="已缴费">已缴费</el-option>
                <!-- <el-option value="已入院">已入院</el-option> -->
              </el-select>



            </el-form-item>
            <el-popover placement="top" width="160" v-model="visible">
              <p>确定要添加登录信息码？</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="addRegisterForm">确定</el-button>
              </div>
              <el-button slot="reference" type="primary" style="margin-left:100px;">确 定
              </el-button>
            </el-popover>

          </el-form>


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
      // 分页属性
      pageNum: 1,
      pageSize: 5,
      searchName: '',
      searchStatus: "",
      tableData: {},
      userList: [],

      //添加窗口的打开和关闭属性
      drawer: false,
      innerDrawer: false,

      //表单添加的信息
      addregister: {
        sex: '男',
      },

      //幂等Token
      idempotentToken: '',

      //预约的列表
      appointmentList: [],

      //预约列表中点击选择后绑定的对象
      appointment: {},

      //确认添加框
      visible: false,

    }
  },
  created() {
    this.queryPageInfo(1);
    this.queryUserListByRoleId();
  },
  methods: {
    //分页列表
    queryPageInfo(pageNum) {
      this.pageNum = pageNum;
      let searchStatus = this.statusTransition(this.searchStatus);

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
    },

    addRegisterForm() {
      if (this.addregister.patientid == "" || this.addregister.patientid == null) {
        this.$message.error('请先在预约列表中进行选择!');
        return;
      }
      this.visible = false;
      this.addregister.status = this.statusTransition(this.addregister.status);
      this.addregister.chargeid = window.sessionStorage.getItem('currentUserId');
      this.$axios({
        url: '/api/register/publish',
        method: 'post',
        headers: { 'addRegister': this.idempotentToken },
        data: this.addregister
      }).then(res => {
        if (res.data.status === 200) {
          this.$message({
            message: '添加消息',
            type: 'success'
          });
        } else {
          console.log(res.data);
        }
      }).catch(e => {
        this.$message({
          message: e.message,
          type: 'error'
        });
      })
    },


    //类型转换方法
    statusTransition(obj) {
      let searchStatus = '';
      if (obj == '查询所有') {
        searchStatus = -1
      } else if (obj == '未缴费') {
        searchStatus = 0
      } else if (obj == '已缴费') {
        searchStatus = 1
      } else if (obj == '已入院') {
        searchStatus = 2
      }
      return searchStatus;
    },


    //关闭入院登记窗口
    handleClose(done) {
      this.$confirm('还有未保存的工作哦确定关闭吗？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    },


    //打开入院登记窗口
    drawerOpen() {
      this.drawer = true;
      this.$axios.get("/api/getIdempotentToken").then(res => {
        this.idempotentToken = res;
      });
    },


    //打开预约列表窗口
    innerDrawerOpen() {
      this.$axios.get("/api/appointment/list/status?status=0").then(res => {
        this.appointmentList = res.data.data;
        console.log(this.appointmentList);
      });
      this.innerDrawer = true
    },


    //预约列表点击选择
    optionAppointment(obj) {
      obj.status = '';
      this.addregister = obj;
      this.innerDrawer = false;
    }



  },






}
</script>

<style>
</style>
