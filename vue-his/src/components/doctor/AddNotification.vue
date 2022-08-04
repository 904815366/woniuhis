<template>
    <el-dialog title="住院申请单开具" :visible.sync="addDialogFormVisible" @close="cancelAdd">
        <el-form :model="notification" label-position="right" label-width="100px">
            <el-form-item label="患者编号:">
                <el-input v-model="notification.patientid" style="width:220px" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="姓名:">
                <el-input v-model="notification.name" style="width:220px" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="性别:">
                <el-radio v-model="notification.sex" label="男">男</el-radio>
                <el-radio v-model="notification.sex" label="女">女</el-radio>
            </el-form-item>
            <el-form-item label="年龄:">
                <el-input v-model="notification.age" style="width:220px" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="联系方式:">
                <el-input v-model="notification.phone" style="width:220px" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="身份证号:">
                <el-input v-model="notification.card" style="width:220px" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="入院诊断:">
                <el-input v-model="notification.diagnose" style="width:220px" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="预住院时间:">
                <el-date-picker v-model="notification.entertime" type="datetime" placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="入住科室:">
                <el-select v-model="notification.familyid" filterable placeholder="请选择">
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
</template>

<script>
export default {
    data() {
        return {
            addDialogFormVisible: true,
            formLabelWidth: '120px',
            notification: {},
            options: [],
            value: ''
        }
    },
    created() {
        this.getDeptList();
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
            this.$axios.post("/api/appointment/addAppointment", this.notification).then(res => {
                console.log(res.data);
                if (res.data.status == 200) {
                    this.$message({
                        showClose: true,
                        message: "添加成功",
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
                console.log("-------")
                console.log(this.options)
                console.log("-------")
            })
        }
    }
}
</script>

<style>
</style>
