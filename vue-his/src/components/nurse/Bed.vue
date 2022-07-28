<template>
    <el-dialog title="患者床位安排" :visible.sync="hidden" @close="cancle">
<!--el-dialog 弹窗  @close 窗口的小叉叉 -->

      <el-form :model="bedpatient">

        <el-form-item label="患者姓名" :label-width="formLabelWidth">
          <el-input v-model="bedpatient.name" autocomplete="off"></el-input>
          <!--autocomplete 自动补全  -->
        </el-form-item>

        <el-form-item label="患者编号" :label-width="formLabelWidth">
          <el-input v-model="bedpatient.patientid" autocomplete="off"></el-input>
        </el-form-item>


      <el-form-item label="选择科室">
      <el-select v-model="fid" @change="gotoword"  placeholder="请选择科室">
      <el-option v-for="op in options" :label="op.familyname" :value="op.id"
      :key="op.id"></el-option>
        </el-select>
       </el-form-item>

       <el-form-item label="选择病房">
      <el-select v-model="wid" @change="gotobeds" placeholder="请选择病房">
      <el-option v-for="ward in children" :label="ward.wardnumber" :value="ward.id"
       :key="ward.id"></el-option>
        </el-select>
       </el-form-item>

       <el-form-item label="选择床位">
      <el-select v-model="bid"  placeholder="请选择床位">
      <el-option v-for="bad in bvalue" :label="bad.id+'号床'" :value="bad.id"
       :key="bad.id"></el-option>
        </el-select>
       </el-form-item>




        <!-- <el-form-item label="毕业专业" :label-width="formLabelWidth">
          <el-input v-model="bedpatient.career" autocomplete="off"></el-input>
        </el-form-item> -->

        <!-- <el-switch v-model="f" active-text="是名师" inactive-text="不是名师"> </el-switch> -->

        <!-- <el-form-item label="所教课程"> -->
          <!-- <el-select v-model="myuptecher.subjectid" placeholder="请选择教课程"> -->
            <!--用于回显的数据  -->
            <!-- <el-option
              :label="sub.name"
              :value="0"
              v-for="sub in subjlist"
              :key="0"
              v-if="myuptecher.subjectid == sub.id"
            ></el-option> -->

            <!-- <el-option
              :label="sub.name"
              :value="sub.id"
              v-for="sub in subjlist"
              :key="sub.id"
            ></el-option>
          </el-select>
        </el-form-item> -->
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle">取 消</el-button>
        <el-button type="primary" @click="commin">提交</el-button>
      </div>

    </el-dialog>
</template>

<script>
export default {
    props:["bedpatient"],
   data() {
      return {
           subjlist: [],
      formLabelWidth: '120px' ,
      hidden: true,
      f: false,
       bvalue: [],
        options: [],
        children: [],
        fid: null,
        wid:'',
        bid:''
      }
   },
   created(){
    this.gotofamiys();
   },
   methods:{
     handleChange(value) {
        console.log(value);
    },
     commin() {//提交方法
     this.bedpatient.familyid=this.fid;
     this.bedpatient.bedid=this.bid;
    //  提交的同时修改床位 upbedstats
 this.$axios
        .get("/api/bed/upbedstats", {
            params:{
            bid: this.bid,
            }
        })
        .then((res) => {
          
        })
        .catch((e) => {console.log(e);});

    this.$axios({//提交完整入院登记
      method: 'post',
      url:'/api/register/upregister',
      data:this.bedpatient,
    }).then((res) => {

        });
//取消方法
      this.hidden = false;
      this.$emit("func"); //调用父类中的方法
     },
    cancle() {
      //取消方法
      this.hidden = false;
      this.$emit("func"); //调用父类中的方法
    },
    // 查所有科室的异步
    gotofamiys(){
        this.$axios
        .get("/api/bed/famiy")
        .then((res) => {
          this.children='';//清空病房
          this.bvalue='';//清空床位
          this.wid='';
          this.fid='';
          this.bid='';
          this.options=res.data;
        })
        .catch((e) => {console.log(e);});
    },
    gotoword(){
        // 根据科室查询病房
        this.$axios
        .get("/api/bed/ward",{
            params:{
            fid:this.fid
            }
        })
        .then((res) => {
           this.bvalue='';
            this.bid='';//清空床位
            this.wid='';//清空上次的病房id
             this.children='';
             this.children=res.data;
        })
        .catch((e) => {console.log(e);});
    },
    gotobeds(){//查询床位
        this.$axios
        .get("/api/bed/bed", {
            params:{
            wid: this.wid,
            }
        })
        .then((res) => {
          this.bvalue=res.data;
          if(this.bvalue==null||this.bvalue==''){
            this.bvalue=[{id:'无可用床位'}]
          }
        })
        .catch((e) => {console.log(e);});
    }
   }
}
</script>

<style>

</style>
