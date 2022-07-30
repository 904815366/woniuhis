<template>
    <div>
        <el-dialog title="回复会诊" :visible.sync="addDialogFormVisible" @close="cancelAdd">
            <el-form :model="replyobj">
                <el-form-item label="患者姓名:">
                    <el-input v-model="replyobj.patientname" :disabled="true" style="width:220px"
                        placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="会诊类型:">
                    <el-radio v-model="replyobj.consutantstype" label="0" :disabled="true">普通</el-radio>
                    <el-radio v-model="replyobj.consutantstype" label="1" :disabled="true">紧急</el-radio>
                </el-form-item>
                <el-form-item label="会诊目的:">
                    <el-input v-model="replyobj.goal" style="width:220px" :disabled="true" placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="患者情况:">
                    <el-input v-model="replyobj.patientcondition" style="width:220px" :disabled="true"
                        placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="诊疗情况:">
                    <el-input v-model="replyobj.consutantscondition" style="width:220px" :disabled="true"
                        placeholder="请输入内容">
                    </el-input>
                </el-form-item>
                <el-form-item label="回复科室:">
                    <el-select v-model="replyobj.replydeptid" filterable placeholder="请选择" :disabled="true">
                        <el-option v-for="item in options" :key="item.id" :label="item.familyname" :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="回复内容:">
                    <el-input v-model="replyobj.opinion" style="width:220px" placeholder="请输入内容">
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
    data() {
        return {
            addDialogFormVisible: true,
            formLabelWidth: '120px',
            options: [],
            value: ''
        }
    },
    props: ['replyobj'],//接收父组件传来的属性值
    created() {
        this.getDeptList();
        this.replyobj.replyid = window.sessionStorage.getItem("currentUserId");
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
            this.$axios.post("/api/consutants/replyConsultation", this.replyobj).then(res => {
                console.log(res.data);
                if (res.data.status == 200) {
                    this.$message({
                        showClose: true,
                        message: "回复成功",
                        type: "success",
                        center: true,
                    });
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
