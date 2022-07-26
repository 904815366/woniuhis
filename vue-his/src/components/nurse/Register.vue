<template>
    <div>
        <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><router-link to="/techer">入院登记</router-link></el-breadcrumb-item>
      <el-breadcrumb-item>预约病人列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row style="margin-top: 10px">
      <el-col :span="8"
        ><el-input v-model="patientid" placeholder="请输入病人编号">
          <!-- 输入框内的放大镜按钮 -->
          <el-button slot="append" icon="el-icon-search" @click="getRegister">
          </el-button></el-input
      ></el-col>
      <el-col :span="8" style="margin-left: 10px">
        </el-col
      >
      <el-col :span="8"></el-col>
    </el-row>

    <!-- 患者列表的表格 -->
    <el-table :data="registerData" style="width: 100%">

    <el-table-column
        prop="id"
        label="患者序号"
        width="80">
      </el-table-column>

        <el-table-column
        prop="patientid"
        label="患者编号"
        width="120">
      </el-table-column>

      <el-table-column
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>

      <el-table-column
        prop="sex"
        label="性别"
        width="140">
      </el-table-column>

      <el-table-column
        prop="diagnose"
        label="病理"
        width="140">
      </el-table-column>

       <!-- <el-table-column  label="任课科目" width="180" align="center"> -->
         <!-- <template slot-scope="scope"> -->
         <!-- 查询到所有的课程遍历结果并且判断赋值 -->
            <!-- <span v-for="subject in subjectData" :key="subject.id"> -->
            <!-- <span v-if="subject.id===scope.row.subjectid">{{subject.name}}</span> -->
            <!-- </span> -->
        <!-- </template> -->
      <!-- </el-table-column> -->

       <el-table-column  label="是否名师" width="120" align="center">
         <template slot-scope="scope">
         <!-- 判断是否添加颜色背景 -->
         <el-tag :type="scope.row.status=='0' ? 'success' : 'danger' ">
         <!-- 三目表达式赋值 -->
             {{scope.row.status=='0' ? '已经缴费' : '未缴费' }}
         </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" 
          type="primary"
           @click="gotoup(scope.row)"
           
            >修改</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
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
             :page-sizes="[5,10,15,20,25,30]"
             :page-size="pageSize"
             :current-page="pageNum"
             :total="total">
            </el-pagination>
        <!-- 指定显示名称的组件 -->
          <component :is="comname"  @func="" :uptecher="techer"></component>
  </div>
    </div>
</template>

<script>
export default {
   data() {
      return {
            patientid: '',
            registerData: null,
            pageNum:1,
            pageSize:5,
            pageInfo:null,
            total:null,
            comname:'',
            techer:null
      }
   },
   created(){
    this.gotoRegisters();
   },
   methods:{
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
    gotoRegisters(){
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
          console.log(this.registerData);
        //   console.log(res.data.data);
           this.pageInfo=res.data;
          console.log(this.pageInfo);
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
