<template>
   <el-dialog title="添加护理记录" :visible.sync="hidden" @close="cancle">
  <el-form :model="nurser">

    <el-form-item label="护理类型">
      <el-select v-model="nurser.nursetypeid"   placeholder="请选择记录类型">
      <el-option v-for="nty in nurtypes" :label="nty.nursename" :value="nty.id"
      :key="nty.id"></el-option>
        </el-select>
       </el-form-item>

    <el-form-item label="住院号" :label-width="formLabelWidth">
      <el-input v-model="nurser.registerid" autocomplete="off"></el-input> 
    </el-form-item>

    <!-- <el-form-item label="护士" :label-width="formLabelWidth">
      <el-input v-model="nurser.career" autocomplete="off"></el-input> 
    </el-form-item> -->

     <el-form-item label="护理描述" :label-width="formLabelWidth">
      <el-input v-model="nurser.nursetext" autocomplete="off"></el-input> 
    </el-form-item>

    <el-form-item label="护理时间" :label-width="formLabelWidth">
     <el-time-picker placeholder="选择时间" v-model="nurser.nursetime" style="width: 100%;"></el-time-picker>
      </el-form-item>

     <el-form-item label="当前记录人" :label-width="formLabelWidth">
       <el-input
            v-model="username"
            :disabled="true">
          </el-input>
      </el-form-item>


  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="cancle">取 消</el-button>
    <el-button type="primary" @click="commin">确 定</el-button>
  </div>
</el-dialog>
</template>

<script>
export default {
  data() {
      return {
            hidden:true,
            formLabelWidth: '120px' ,
            nurser:{},
            nurtypes:[],
            username:''
      }
   },
   created(){
   this.gotosubjlist();
   this.gotoNutype();
   this.nurser.userid=window.sessionStorage.getItem("currentUserId");
   this.username=window.sessionStorage.getItem('currentUser');
   },
   methods:{
    cancle(){//取消方法
        this.hidden = false;
       this.$emit('func');//调用父类中的方法
    },
    commin(){//提交方法
        this.hidden = false;
        // 异步添加
         this.$axios.post("/api/nurserecord/addNureser",this.nurser).then((res) => {
         if(res.data.status==200){
             this.$message({
                    message: '添加成功',
                    type: 'success',
                    duration: 1500 ,
                    offset : 175
                   });
                   this.$emit('func');
         }else{
          this.$message({
                  message: '添加失败',
                  type: 'error',
                  duration: 1500 ,
                  offset : 175
                });
         }
        })
        .catch((e) => {});
    },
    // 查询所有的课程
     gotosubjlist(){
        
    },
    gotoNutype(){//异步查询所有的类型
     this.$axios
        .get("/api/nurserecord/getNutypes").then((res) => {
          this.nurtypes=res.data;
        })
        .catch((e) => {});
    }

   }
}
</script>

<style>

</style>
