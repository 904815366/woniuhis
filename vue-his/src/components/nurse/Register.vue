<template>
  <div>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item></el-breadcrumb-item
        >
        <el-breadcrumb-item>预约病人列表</el-breadcrumb-item>
      </el-breadcrumb>

      <el-row style="margin-top: 10px">
        <el-col :span="8"
          ><el-input v-model="patientid" placeholder="请输入病人编号">
            <!-- 输入框内的放大镜按钮 -->
            <el-button slot="append" icon="el-icon-search" @click="getRegister">
            </el-button></el-input
        ></el-col>
        <el-col :span="8" style="margin-left: 10px"> </el-col>
        <el-col :span="8"></el-col>
      </el-row>

      <!-- 患者列表的表格 -->
      <el-table :data="registerData" style="width: 100%">
        <el-table-column prop="id" label="患者住院号" width="80"> </el-table-column>

        <el-table-column prop="patientid" label="患者编号" width="120"> </el-table-column>

        <el-table-column prop="name" label="姓名" width="120"> </el-table-column>

        <el-table-column prop="sex" label="性别" width="140"> </el-table-column>

        <el-table-column prop="diagnose" label="病理" width="140"> </el-table-column>

        <!-- <el-table-column  label="任课科目" width="180" align="center"> -->
        <!-- <template slot-scope="scope"> -->
        <!-- 查询到所有的课程遍历结果并且判断赋值 -->
        <!-- <span v-for="subject in subjectData" :key="subject.id"> -->
        <!-- <span v-if="subject.id===scope.row.subjectid">{{subject.name}}</span> -->
        <!-- </span> -->
        <!-- </template> -->
        <!-- </el-table-column> -->

        <el-table-column label="患者状态" width="120" align="center">
          <template slot-scope="scope">
            <el-tag type="info" v-if="scope.row.status == '0'">未缴费</el-tag>
            <el-tag v-if="scope.row.status == '1'">已缴费</el-tag>
            <el-tag type="success" v-if="scope.row.status == '2'">入院中</el-tag>
            <el-tag type="warning" v-if="scope.row.status == '3'">申请出院中</el-tag>
            <el-tag type="danger" v-if="scope.row.status == '4'">出院申请通过</el-tag>
            <el-tag type="danger" v-if="scope.row.status == '5'">已出院</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="业务">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.status == '1'"
              size="mini"
              type="primary"
              @click="gotoBad(scope.row)"
              >安排床位</el-button
            >

            <el-button
              v-if="scope.row.status == '2'"
              size="mini"
              type="danger"
              @click="gotoappliy(scope.row.id)"
              >申请出院</el-button
            >

            <el-button
              v-if="scope.row.status == '4'"
              size="mini"
              type="warning"
              @click="gotoOut(scope.row)"
              >办理出院</el-button
            >
          </template>
        </el-table-column>
        <el-table-column prop="diagnose" label="详情" width="140">
          <template slot-scope="scope">
            <el-button type="primary" @click="gotoMoney(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页组件 
     :background 表示背景颜色开启不开启 默认是false
     layout 表示分页组件的显示布局 
      prev上一页   pager导航页码   next下一页   sizes 每页记录数 
       total 设置总记录数字 jumper前往第几页
    -->
      <el-pagination
        @current-change="changepagebynum"
        @size-change="sizepageSize"
        :background="true"
        layout="prev, pager, next,sizes,jumper"
        prev-text="上一页"
        next-text="下一页"
        :page-sizes="[5]"
        :page-size="pageSize"
        :current-page="pageNum"
        :total="total"
      >
      </el-pagination>
      <!-- 指定显示名称的组件 -->
      <component :is="comname" @func="turnSon" :bedpatient="patient"></component>
    </div>
  </div>
</template>

<script>
// 导入子组件
import Bed from "../nurse/Bed.vue";
import Money from "../nurse/Money.vue";
export default {
  inject: ["reload"],
  components: {
    Bed,
    Money,
  },
  data() {
    return {
      patientid: "",
      registerData: null,
      pageNum: 1,
      pageSize: 5,
      pageInfo: null,
      total: null,
      comname: "",
      patient: null,
      wids: [],
      ids: [],
      result:'',
      
    };
  },
  created() {
    this.gotoRegisters();
  },
  methods: {
    gotoMoney(x) {
      this.patient = x;
      this.comname = "Money";
    },
    getWarnids(x) {
      //通过住院号查询所有的医嘱详情
      this.$axios
        .get("/api/register/getWarnids/" + x)
        .then((res) => {
          this.wids = res.data;
          for (let i = 0; i < this.wids.length; i++) {
            this.ids[i] = this.wids[i].id;
          }
           console.log("*******准备2**********");
           this.result = this.ids.join(",");
            console.log(this.result);
             // this.ids='';
      //  调接口改状态
      this.$axios
        .get("/api/warn/updateCpoeStatus?ids="+this.result+"&status=3")
        .then((res) => {

        })
        .catch((e) => {
          console.log(e);
        });
        })
        .catch((e) => {
          console.log(e);
        });
     
    },
    gotoOut(x) {
      //办理出院gotoOut
      x.status = "";
      this.$axios({
        //发送修改状态请求
        method: "post",
        url: "/api/register/gotoOut",
        data: x,
      }).then((res) => {
        if (res.data.status == 200) {
          this.$message({
            message: "办理成功",
            type: "success",
            duration: 1500,
            offset: 175,
          });
          this.getWarnids(x.id);
          this.gotoRegisters();
        } else {
          this.$message({
            message: "办理失败",
            type: "error",
            duration: 1500,
            offset: 175,
          });
        }
      });
    },
    gotowarnOut(x) {},
    gotoappliy(x) {
      //申请出院
      this.$axios
        .get("/api/register/upstatusc", {
          params: {
            id: x,
            status: "3",
          },
        })
        .then((res) => {
          if (res.data.status == 200) {
            this.$message({
              message: "申请成功",
              type: "success",
              duration: 1500,
              offset: 175,
            });
            this.gotoRegisters();
          } else {
            this.$message({
              message: "申请失败",
              type: "error",
              duration: 1500,
              offset: 175,
            });
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // 安排病房方法
    gotoBad(x) {
      this.patient = x;
      this.comname = "bed";
    },
    turnSon() {
      //子调父方法
      this.comname = "";
      // 再次调查询方法刷新页面
      this.gotoRegisters();
    },
    getRegister() {
      //查看单个患者
      this.$axios
        .get("/api/register/gotoRegisters", {
          params: {
            pageNum: 1,
            pageSize: this.pageSize,
            patientid: this.patientid,
          },
        })
        .then((res) => {
          this.registerData = res.data.list;
         this.pageInfo = res.data;
          this.pageNum = res.data.pageNum;
          // this.pageSize=res.data.pageSize;
          this.total = res.data.total;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    gotoRegisters() {
      //分页查看所有
      this.$axios
        .get("/api/register/gotoRegisters", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            patientid: this.patientid,
          },
        })
        .then((res) => {
          this.registerData = res.data.list;
          //   console.log(res.data.data);
          this.pageInfo = res.data;
          this.pageNum = res.data.pageNum;
          // this.pageSize=res.data.pageSize;
          this.total = res.data.total;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    changepagebynum(pageNum) {
      this.pageNum = pageNum;
      this.gotoRegisters();
    },
    sizepageSize(pageSize) {
      this.pageSize = pageSizee;
      this.gotoRegisters();
    },
  },
};
</script>

<style></style>
