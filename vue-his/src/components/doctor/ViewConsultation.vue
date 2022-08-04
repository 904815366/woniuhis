<template>
    <div>
        <el-dialog title="会诊详情" :visible.sync="addDialogFormVisible" @close="cancelAdd">
            <el-form :model="applyobj">
                <el-form-item label="患者姓名:">
                    <input type="hidden" name="id" v-model="applyobj.id" />
                    <el-input v-model="applyobj.patientname" :disabled="true" style="width:220px" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="会诊类型:">
                    <el-radio v-model="applyobj.consutantstype" label="0" :disabled="status">普通</el-radio>
                    <el-radio v-model="applyobj.consutantstype" label="1" :disabled="status">紧急</el-radio>
                </el-form-item>
                <el-form-item label="会诊目的:">
                    <el-input v-model="applyobj.goal" style="width:220px" :disabled="status" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="患者情况:">
                    <el-input v-model="applyobj.patientcondition" style="width:220px" :disabled="status" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="诊疗情况:">
                    <el-input v-model="applyobj.consutantscondition" style="width:220px" :disabled="status" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="回复科室:">
                    <el-select v-model="applyobj.replydeptid" filterable placeholder="请选择" :disabled="status">
                        <el-option v-for="item in options" :key="item.id" :label="item.familyname" :value="item.id" >
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="回复内容:" >
                    <el-input v-model="applyobj.opinion" style="width:220px" :disabled="true" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelAdd">取 消</el-button>
                <el-button type="primary" @click="confirmAdd">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    inject: ["reload"],
    data() {
        return {
            addDialogFormVisible: true,
            formLabelWidth: '120px',
            options: [],
            value: '',
            status:false
        }
    },
    props: ['applyobj'],//接收父组件传来的属性值
    created() {
        this.getDeptList();
        if(this.applyobj.status==1){
            this.status=true;
        }
    },
    methods: {
        cancelAdd() {
            this.addDialogFormVisible = false;
            //调用父组件传来的方法
            this.$emit('func');
        },
        confirmAdd() {
            this.addDialogFormVisible = false;
            //调用父组件传来的方法
            this.$emit('func');
            this.$axios.post("/api/consutants/modifyApplyConsultation", this.applyobj).then(res => {
                console.log(res.data);
                if (res.data.status == 200) {
                    this.$message({
                        showClose: true,
                        message: "修改成功",
                        type: "success",
                        center: true,
                    });
                    this.reload();
                } else {
                    this.$message({
                        showClose: true,
                        message: '服务异常!',
                        type: 'error',
                        duration: 1000  //显示的时间,ms
                    });
                }
            })
        },
        getDeptList() {
            this.$axios.get("/api/family/list").then(res => {
                this.options = res.data.data;
            })
        }
    }
}
</script>

<style>
</style>
