<template>
 <el-dialog title="患者信息详情" :visible.sync="hidden" @close="cancle">
    <!-- 患者列表的表格 -->
    <el-table :data="money" style="width: 100%">
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

       <el-table-column  label="患者状态" width="120" align="center">
         <template slot-scope="scope">
                    <el-tag type="info" v-if="scope.row.status == '0'">未缴费</el-tag>
                    <el-tag v-if="scope.row.status == '1'">已缴费</el-tag>
                    <el-tag type="success" v-if="scope.row.status == '2'">入院中</el-tag>
                    <el-tag type="warning" v-if="scope.row.status == '3'">申请出院中</el-tag>
                    <el-tag type="danger" v-if="scope.row.status == '4'">出院申请通过</el-tag>
                    <el-tag type="danger" v-if="scope.row.status == '5'">已出院</el-tag>
                </template>
      </el-table-column>
    </el-table>

            <!-- 第二张表格 -->
     <el-table :data="moneylist" style="width: 100%">
        <el-table-column
        prop="consumtime"
        label="消费时间"
        width="160">
      </el-table-column>

      <el-table-column
        prop="consummoney"
        label="消费金额"
        width="120">
      </el-table-column>

      <el-table-column
        prop="consumpart"
        label="消费详情"
        width="140">
      </el-table-column>

    </el-table>

     <div slot="footer" class="dialog-footer">
        <el-button @click="cancle">取 消</el-button>
      </div>
      </el-dialog>
</template>

<script>
// 导入子组件
export default {
   props:["bedpatient"],
   data() {
      return {
            patientid: '',
             formLabelWidth: '120px' ,
             hidden: true,
             f: false,
            registerData: null,
            pageNum:1,
            patient:null,
            money:[],
            moneylist:[]
      }
   },
   created(){
    this.money.push(this.bedpatient);
    this.gotoMoney();
   },
   methods:{
     cancle() { //取消方法
      this.hidden = false;
      this.$emit("func"); //调用父类中的方法
    }, 
    gotoMoney() {//获取集合
        let x=this.bedpatient.id;
     this.$axios
        .get("/api/moneylist/getMoneylists", {
            params:{
            mid: x,
            }
        })
        .then((res) => {
          this.moneylist=res.data;
        })
        .catch((e) => {console.log(e);});
     },
   }
}
</script>

<style>

</style>
