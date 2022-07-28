<template>
    <div>
        <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><router-link to="/techer">护理纪录</router-link></el-breadcrumb-item>
      <el-breadcrumb-item>护理纪录列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row style="margin-top: 10px">
      <el-col :span="8"
        ><el-input v-model="patientid" placeholder="请输入病人住院号码">
          <!-- 输入框内的放大镜按钮 -->
          <el-button slot="append" icon="el-icon-search" @click="getRegister">
          </el-button></el-input></el-col>
      <el-col :span="8" style="margin-left: 10px">
      <el-button type="success" @click="comname='AddNureser'">添加护理记录</el-button>
        </el-col>
      <el-col :span="8"></el-col>
    </el-row>

    <!-- 患者列表的表格 -->
    <el-table :data="nurData" style="width: 100%">

    <el-table-column
        prop="id"
        label="序号"
        width="80">
      </el-table-column>

       <el-table-column
        prop="nursename"
        label="护理类型"
        width="140">
      </el-table-column>

        <el-table-column
        prop="registerid"
        label="住院编号"
        width="120">
      </el-table-column>

      <el-table-column
        prop="name"
        label="记录护士"
        width="120">
      </el-table-column>


      <el-table-column
        prop="nursetext"
        label="护理描述"
        width="140">
      </el-table-column>

       <el-table-column
        prop="nursetime"
        label="护理时间"
        width="160">
      </el-table-column>

       <!-- <el-table-column  label="是否缴费" width="120" align="center"> -->
         <!-- <template slot-scope="scope"> -->
         <!-- 判断是否添加颜色背景 -->
         <!-- <el-tag :type="scope.row.status=='0' ? 'success' : 'danger' "> -->
         <!-- 三目表达式赋值 -->
             <!-- {{scope.row.status=='0' ? '未缴费' : '已缴费' }} -->
         <!-- </el-tag> -->
        <!-- </template> -->
      <!-- </el-table-column> -->

      <el-table-column label="操作">
        
        <template slot-scope="scope">
          <el-button v-if="scope.row.status=='1'" size="mini" 
          type="primary"
          @click="">安排床位</el-button>
          <el-button
            size="mini"
            type="danger"
            @click=""
            >详情</el-button
          >
        </template>

      </el-table-column>

    </el-table>

          <el-pagination
           @current-change="changepagebynum"
            @size-change="sizepageSize"
             :background="true" 
             layout="prev, pager, next,sizes,jumper"
             prev-text="上一页"
             next-text="下一页"
             :page-sizes="[5,10,15,20,25,30]"
             :page-size="pageSize"
             :current-page="pageNum"
             :total="total">
            </el-pagination>
        <!-- 指定显示名称的组件 -->
          <component :is="comname"  @func="turnSon" :bedpatient="patient"></component>
  </div>
    </div>
</template>

<script>
// 导入子组件
import AddNureser from "../nurse/AddNurser.vue"
export default {
    components:{
        AddNureser
    },
   data() {
      return {
            nurData: [],
            patient:'',//应该传值用的
            pageNum:1,
            pageSize:5,
            pageInfo:null,
            total:null,
            comname:'',
            patient:null,
            patientid:''
      }
   },
   created(){
        this.gotoNursers();
   },
   methods:{
     turnSon(){//子调父方法
    this.comname='';
    },
    getRegister(){
        this.$axios
        .get("/api/register/gotoRegisters", {
            params:{
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            patientid: this.patientid
            }
        })
        .then((res) => {
          this.registerData=res.data.list;
        })
        .catch((e) => {console.log(e);});
    },
    gotoNursers(){//查看所有护理记录
        this.$axios
        .get("/api/nurserecord/gotoNursers", {
            params:{
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            }
        })
        .then((res) => {
          this.nurData=res.data.list;
          console.log(this.registerData);
           this.pageInfo=res.data;
           this.pageNum=res.data.pageNum;
          this.pageSize=res.data.pageSize;
          this.total=res.data.total;
        })
        .catch((e) => {console.log(e);});
    },
    changepagebynum(pageNum){
          this.pageNum=pageNum
          this.gotoTeacherList();
    },
    sizepageSize(pageSize){
          this.pageSize=pageSizee;
          this.gotoTeacherList();
    },
   }
}
</script>

<style>

</style>
