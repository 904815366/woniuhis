<template>
    <div>
        <el-dialog title="医嘱开立" :visible.sync="addDialogFormVisible" @close="cancelAdd">
            <el-form :model="cpoe">
                <input type="hidden" name="patientname" v-model="cpoe.patientname" />
                <input type="hidden" name="patientid" v-model="cpoe.patientid" />
                <input type="hidden" name="doctorid" v-model="cpoe.doctorid" />
                <input type="hidden" name="familyid" v-model="cpoe.familyid"/>
                <el-form-item label="开立时间:">
                    <el-date-picker v-model="cpoe.warntime" type="datetime" placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="药品名称:">
                    <el-select v-model="cpoe.drugid" filterable placeholder="请选择">
                        <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="药品数量:">
                    <el-input-number v-model="cpoe.num" :min="1" :max="10" label="描述文字">
                    </el-input-number>
                </el-form-item>
                <el-form-item label="单次剂量:">
                    <el-input v-model="cpoe.measure" style="width:220px" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="用法用量:">
                    <el-input type="textarea" :rows="2" style="width:220px" placeholder="请输入内容"
                        v-model="cpoe.directions">
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
            cpoe: {},
            options: [],
            value: ''
        }
    },
    props: ['patient'],//接收父组件传来的属性值
    created() {
        this.cpoe.warntime = new Date();
        this.getDrugList();
        this.cpoe.patientname = this.patient.name;
        this.cpoe.patientid = this.patient.id;
        this.cpoe.doctorid = window.sessionStorage.getItem("currentUserId");
        this.cpoe.familyid=window.sessionStorage.getItem("currentUserFamilyId");
        console.log(this.cpoe.doctorid);
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
            this.$axios.post("/api/warn/addCpoe", this.cpoe).then(res => {
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
        getDrugList() {
            this.$axios.get("/api/drug/druglist").then(res => {
                console.log(res.data);
                this.options = res.data.data;
            })
        }
    }
}
</script>

<style>
</style>
