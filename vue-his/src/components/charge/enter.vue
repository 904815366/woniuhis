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
          <el-option value="住院中">住院中</el-option>
          <el-option value="申请出院">申请出院</el-option>
          <el-option value="审核通过">审核通过</el-option>
          <el-option value="已出院">已出院</el-option>
        </el-select>


        <!-- 添加入院登记信息 -->
        <el-button @click="drawerOpen()" type="primary" style="margin-left: 16px;" class="el-icon-circle-plus">
        </el-button>
      </el-col>

    </div>


    <!-- 添加入院登记信息框 -->
    <el-drawer title="病人入院信息登记" :visible.sync="drawer" size="60%" :before-close="handleClose">
      <div>

        <!-- 预约列表按钮 -->
        <el-button @click="innerDrawerOpen()" style="margin-left:100px;margin-bottom: 10px;">打开预约列表</el-button>

        <!-- 预约列表框 -->
        <el-drawer title="病人预约列表" :append-to-body="true" :before-close="handleClose" :visible.sync="innerDrawer">
          <el-table :data="appointmentList" :fit="false">
            <el-table-column prop="id" label="编号" width="80" align="center">
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

        <!-- 入院登记信息 -->
        <el-form ref="addregister" :model="addregister" label-width="100px" :rules="rules"
          :hide-required-asterisk="false">
          <el-form-item label="病人编号:">
            <el-input :disabled="true" v-model="addregister.id" autocomplete="off" style="width: 50px;">
            </el-input>

          </el-form-item>

          <el-form-item label="姓名:" style="display:inline-block" prop="name">
            <el-input v-model="addregister.name" autocomplete="off" style="width: 300px" placeholder="请输入姓名">
            </el-input>
          </el-form-item>

          <el-form-item label="性别:" style="display:inline-block">
            <el-radio v-model="addregister.sex" label="男" border size="medium">男</el-radio>
            <el-radio v-model="addregister.sex" label="女" border size="medium">女</el-radio>
          </el-form-item>

          <el-form-item label="联系方式:" style="display:inline-block" prop="phone">
            <el-input autocomplete="off" style="width: 300px;" v-model="addregister.phone" placeholder="请输入联系方式"
              maxlength="11">
            </el-input>
          </el-form-item>

          <el-form-item label="年龄:" style="display:inline-block" prop="age">
            <el-input autocomplete="off" style="width: 120px;" v-model.number="addregister.age" placeholder="请输入年龄"
              maxlength="3">
            </el-input>
          </el-form-item>



          <el-form-item label="身份证号码:" style="display:inline-block" prop="card">
            <el-input autocomplete="off" style="width: 300px;" v-model="addregister.card" placeholder="请输入身份证号码"
              maxlength="18">
            </el-input>
          </el-form-item>

          <el-form-item label="住址:" style="display:inline-block" prop="area">
            <el-input autocomplete="off" style="width: 300px; " v-model="addregister.area" placeholder="请输入住址">
            </el-input>
          </el-form-item>

          <el-form-item label="门诊诊断:" style="display:inline-block" prop="diagnose">
            <el-input autocomplete="off" style="width: 300px; " v-model="addregister.diagnose" placeholder="请输入门诊诊断">
            </el-input>
          </el-form-item>

          <el-form-item label="预缴金额:" style="display:inline-block" prop="money">
            <el-input autocomplete="off" style="width: 300px; " v-model="addregister.money" placeholder="请输入预缴金额">
            </el-input>
          </el-form-item>

          <el-form-item label="状态:" prop="status">
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



    <!-- 修改入院登记信息框 -->
    <el-drawer title="病人入院信息修改" :visible.sync="modifyDrawer" size="60%">
      <div>


        <!-- 修改入院登记信息 -->
        <el-form ref="modifyregister" :model="modifyregister" label-width="100px" :rules="rules">
          <el-form-item label="病人编号:">
            <el-input disabled v-model="modifyregister.id" autocomplete="off" style="width: 50px;">
            </el-input>

          </el-form-item>

          <el-form-item label="姓名:" style="display:inline-block" prop="name">
            <el-input v-model="modifyregister.name" autocomplete="off" style="width: 300px" placeholder="请输入姓名">
            </el-input>
          </el-form-item>

          <el-form-item label="性别:" style="display:inline-block">
            <el-radio v-model="modifyregister.sex" label="男" border size="medium">男</el-radio>
            <el-radio v-model="modifyregister.sex" label="女" border size="medium">女</el-radio>
          </el-form-item>

          <el-form-item label="联系方式:" style="display:inline-block" prop="phone">
            <el-input autocomplete="off" style="width: 300px;" v-model="modifyregister.phone" placeholder="请输入联系方式">
            </el-input>
          </el-form-item>

          <el-form-item label="年龄:" style="display:inline-block" prop="age">
            <el-input autocomplete="off" style="width: 120px;" v-model.number="modifyregister.age" placeholder="请输入年龄">
            </el-input>
          </el-form-item>



          <el-form-item label="身份证号码:" style="display:inline-block" prop="card">
            <el-input autocomplete="off" style="width: 300px;" v-model="modifyregister.card" placeholder="请输入身份证号码">
            </el-input>
          </el-form-item>

          <el-form-item label="住址:" style="display:inline-block" prop="area">
            <el-input autocomplete="off" style="width: 300px; " v-model="modifyregister.area" placeholder="请输入住址">
            </el-input>
          </el-form-item>

          <el-form-item label="门诊诊断:" style="display:inline-block" prop="diagnose">
            <el-input autocomplete="off" style="width: 300px; " v-model="modifyregister.diagnose" placeholder="请输入门诊诊断">
            </el-input>
          </el-form-item>

          <el-form-item label="预缴金额:" style="display:inline-block" prop="money">
            <el-input autocomplete="off" style="width: 300px; " v-model="modifyregister.money" placeholder="请输入预缴金额">
            </el-input>
          </el-form-item>

          <el-form-item label="状态:">
            <el-select style="width: 300px; " v-model="modifyregister.status" placeholder="请输入状态">
              <el-option value="未缴费">未缴费</el-option>
              <el-option value="已缴费">已缴费</el-option>
              <el-option value="住院中">住院中</el-option>
              <el-option value="申请出院">申请出院</el-option>
              <el-option value="审核通过">审核通过</el-option>
              <el-option value="已出院">已出院</el-option>

            </el-select>



          </el-form-item>
          <el-popover placement="top" width="160" v-model="modifyvisible">
            <p>确定要修改入院信息码？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="modifyvisible = false">取消</el-button>
              <el-button type="primary" size="mini" @click="modifyRegisterForm()">确定</el-button>
            </div>
            <el-button slot="reference" type="primary" style="margin-left:100px;">确 定
            </el-button>
          </el-popover>
        </el-form>
      </div>
    </el-drawer>



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
      <el-table-column prop="phone" label="联系方式" width="110" align="center">
      </el-table-column>
      <el-table-column prop="card" label="身份证" width="100" align="center" :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column prop="area" label="住址" width="200" align="center">
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
          <span v-if="scope.row.status == 0">
            <el-tag type="danger">未缴费</el-tag>
          </span>
          <span v-if="scope.row.status == 1">
            <el-tag type="success">已缴费</el-tag>
          </span>
          <span v-if="scope.row.status == 2">
            <el-tag type="info">住院中</el-tag>
          </span>
          <span v-if="scope.row.status == 3">
            <el-tag type="warning">申请出院</el-tag>
          </span>
          <span v-if="scope.row.status == 4">
            <el-tag>审核通过</el-tag>
          </span>
          <span v-if="scope.row.status == 5">
            <el-tag>已出院</el-tag>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
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
      //预约的列表
      appointmentList: [],
      //预约列表中点击选择后绑定的对象
      appointment: {},
      //确认添加框
      visible: false,


      //修改窗口的打开和关闭属性
      modifyDrawer: false,
      modifyregister: {},
      modifyvisible: false,

      //表单添加的信息
      addregister: {
        sex: '男',
      },

      //幂等Token
      idempotentToken: '',
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { pattern: /^[\u4e00-\u9fa5]{1,}$/, message: '姓名格式错误', trigger: 'blur' },

        ],
        phone: [
          { required: true, message: '请输入联系方式', trigger: 'blur' },
          { pattern: /^0?(13|14|15|17|18|19)[0-9]{9}$/, message: '联系方式格式错误', trigger: 'change' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
          { type: 'number', min: 1, max: 120, message: '年龄格式错误(<=120)', trigger: 'blur' }
        ],
        card: [
          { required: true, message: '请输入身份证号码', trigger: 'blur' },
          { pattern: /^\d{17}[\d|x]/, message: '身份证号码格式错误', trigger: 'blur' }
        ],
        area: [
          { required: true, message: '请输入住址', trigger: 'blur' },
        ],
        diagnose: [
          { required: true, message: '请输入门诊诊断', trigger: 'blur' },
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'blur' },
        ],
        money: [
          { required: false, message: '请输入缴费金额', trigger: 'blur' },
          { pattern: /^[1-9][\d]{1,}$/, message: '金额格式错误', trigger: 'blur' },
        ],
      }
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
      let searchStatus = this.statusStringFormNumber(this.searchStatus);

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

    //添加入院信息
    addRegisterForm() {

      if (this.addregister.id == "" || this.addregister.id == null) {
        this.$message.error('请先在预约列表中进行选择!');
        this.visible = false;
        return;
      };

      this.$refs["addregister"].validate((valid) => {
        if (!valid) {
          return;
        } else {
          this.addregister.status = this.statusStringFormNumber(this.addregister.status);
          this.addregister.chargeid = window.sessionStorage.getItem('currentUserId');
          this.$axios({
            url: '/api/register/publish',
            method: 'post',
            headers: { addRegister: this.idempotentToken },
            data: this.addregister
          }).then(res => {
            if (res.data.status === 200) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });

              this.drawer = false;
              this.queryPageInfo(this.pageNum);
            } else {
              console.log(res.data);
              this.$message({
                message: '当前病人已登记过...',
                type: 'warning'
              });
            }
          }).catch(e => {
            this.$message({
              message: e.message,
              type: 'error'
            });
          });
        }
      });
      this.addregister.status = this.statusNumberFormString(this.addregister.status);
      this.visible = false;
    },




    //关闭入院登记窗口
    handleClose(done) {
      this.$confirm('确定关闭吗？')
        .then(_ => {
          done();
          this.addregister = {
            sex: '男',
          };
        })
        .catch(_ => { });
    },


    //打开入院登记窗口
    drawerOpen() {
      this.drawer = true;
      this.$axios.get("/api/getIdempotentToken").then(res => {

        this.idempotentToken = res.data;
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
      // obj.patientid = obj.id;
      this.addregister = obj;
      this.innerDrawer = false;
    },


    //删除行
    handleDelete(id) {
      this.$confirm('此操作将永久删除该信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/api/register/remove/" + id).then(res => {
          if (res.data.data == true) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.queryPageInfo(this.pageNum);
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },

    //打开编辑框并回显数据
    handleEdit(obj) {

      this.modifyregister = {
        id: obj.id,
        patientid: obj.patientid,
        name: obj.name,
        sex: obj.sex,
        age: obj.age,
        phone: obj.phone,
        card: obj.card,
        area: obj.area,
        diagnose: obj.diagnose,
        money: obj.money,
        status: obj.status,
      }
      this.modifyregister.status = this.statusNumberFormString(this.modifyregister.status);

      this.$axios.get("/api/getIdempotentToken").then(res => {
        this.idempotentToken = res.data;
      });
      this.modifyDrawer = true;
    },

    //提交修改
    modifyRegisterForm() {

      this.$refs["modifyregister"].validate((valid) => {
        if (!valid) {
          return;
        } else {
          this.modifyregister.status = this.statusStringFormNumber(this.modifyregister.status);
          this.$axios({
            url: '/api/register/modify',
            method: 'post',
            headers: { modifyRegister: this.idempotentToken },
            data: this.modifyregister
          }).then(res => {
            if (res.data.status === 200) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.queryPageInfo(this.pageNum);
              this.modifyDrawer = false;
            } else {
              this.$message({
                message: '修改失败',
                type: 'error'
              });
            }
          }).catch(e => {
            this.$message({
              message: e.message,
              type: 'error'
            });
          });
        }
      });
      this.modifyvisible = false;
    },


    //类型转换方法
    statusStringFormNumber(obj) {
      let searchStatus = '';
      if (obj == '查询所有') {
        searchStatus = -1
      } else if (obj == '未缴费') {
        searchStatus = 0
      } else if (obj == '已缴费') {
        searchStatus = 1
      } else if (obj == '住院中') {
        searchStatus = 2
      } else if (obj == '申请出院') {
        searchStatus = 3
      } else if (obj == '审核通过') {
        searchStatus = 4
      } else if (obj == '已出院') {
        searchStatus = 5
      }
      return searchStatus;
    },

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

  },






}
</script>

<style>
</style>
