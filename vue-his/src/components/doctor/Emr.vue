<template>
    <div>
        <el-descriptions class="margin-top" title="入院记录" :column="8" border>
            <template slot="extra">
                <el-button type="success" round size="mini" @click="comName = 'addCpoe'">新增</el-button>
            </template>
            <template slot="extra">
                <el-button type="warning" round size="mini" @click="updateStatus">提交</el-button>
            </template>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-receiving"></i> 床号:
                </template>
                {{ patient.bedid }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i> 姓名:
                </template>
                {{ patient.name }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i :class="patient.sex == '男' ? 'el-icon-male' : 'el-icon-female'"></i> 性别:
                </template>
                {{ patient.sex }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-edit"></i> 年龄:
                </template>
                {{ patient.age }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-notebook-2"></i> 诊断:
                </template>
                {{ patient.diagnose }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-house"></i>
                    现住址
                </template>
                {{ patient.area }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-office-building"></i>
                    科室
                </template>
                <el-tag size="small">{{ patient.familyname }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-date"></i>
                    入院时间
                </template>
                {{ patient.entertime }}
            </el-descriptions-item>
        </el-descriptions>
        <my-editor></my-editor>
    </div>
</template>

<script>
import MyEditor from './MyEditor.vue'
export default {
    data() {
        return {
            patient: {},//患者信息
        }
    },
    components: {
    MyEditor,
    },
    created() {
        this.getPatientById(id);
    },
    methods: {
        getPatientById(id) {
            this.$axios.get("/api/patient/getPatientById?id=" + id).then(res => {
                this.patient = res.data.data;
            })
        },
    }
}
</script>

<style>
.box-card {
  width: 800px;
}
</style>
