<template>
    <div>
        <el-dialog title="发起会诊" :visible.sync="addDialogFormVisible" @close="cancelAdd">
            <el-form :model="consultation">
                <el-form-item label="患者姓名:">
                    <el-input v-model="consultation.patientname" style="width:220px" placeholder="请输入内容"></el-input>
                    <input type="hidden" name="applydeptid" v-model="consultation.applydeptid"/>
                </el-form-item>
                <el-form-item label="会诊类型:">
                    <el-radio v-model="consultation.consutantstype" label="0">普通</el-radio>
                    <el-radio v-model="consultation.consutantstype" label="1">紧急</el-radio>
                </el-form-item>
                <el-form-item label="会诊目的:">
                    <el-input v-model="consultation.goal" style="width:220px" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="患者情况:">
                    <el-input v-model="consultation.patientcondition" style="width:220px" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="诊疗情况:">
                    <el-input v-model="consultation.consutantscondition" style="width:220px" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="回复科室:">
                    <el-select v-model="consultation.replydeptid" filterable placeholder="请选择">
                        <el-option v-for="item in options" :key="item.id" :label="item.familyname" :value="item.id">
                        </el-option>
                    </el-select>
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
            consultation: {},
            options: [],
            value: ''
        }
    },
    props: ['patient'],//接收父组件传来的属性值
    created() {
        this.consultation.registerid=this.patient.id;
        this.consultation.patientname=this.patient.name;
        this.getDeptList();
        this.consultation.applydeptid = window.sessionStorage.getItem("currentUserFamilyId");
        this.consultation.userid = window.sessionStorage.getItem("currentUserId");
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
            this.$axios.post("/api/consutants/addApplyConsultation", this.consultation).then(res => {
                console.log(res.data);
                if (res.data.status == 200) {
                    this.$message({
                        showClose: true,
                        message: "申请成功",
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
                console.log("..............");
                console.log(res.data.data);
                this.options = res.data.data;
            })
        }
    }
}
</script>

<style>
</style>
