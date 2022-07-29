<template>
    <div>
        <el-descriptions class="margin-top" title="入院记录" :column="8" border>
            <template slot="extra">
                <el-select v-model="pid" filterable placeholder="请选择患者" @change="changePatient()"
                    style="margin-right: 10px;">
                    <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                </el-select>
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
        <el-tabs type="border-card" sty>
            <el-tab-pane>
                <span slot="label"><i class="el-icon-notebook-1"></i>记录总览</span>
                我的行程
            </el-tab-pane>
            <el-tab-pane>
            <span slot="label"><i class="el-icon-edit"></i>入院记录</span>
            <my-editor></my-editor>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
import MyEditor from './MyEditor.vue'
export default {
    data() {
        return {
            patient: {},//患者信息
            options: [],
            value: '',
            pid: '',
            editableTabsValue: '2',
            editableTabs: [{
                title: 'Tab 1',
                name: '1',
                content: 'Tab 1 content'
            }],
            tabIndex: 2
        }
    },
    components: {
        MyEditor,
    },
    created() {
        this.getPatientListAll();
    },
    methods: {
        getPatientById(id) {
            this.$axios.get("/api/patient/getPatientById?id=" + id).then(res => {
                this.patient = res.data.data;
            })
        },
        getPatientListAll() {
            let deptid = window.sessionStorage.getItem("currentUserFamilyId");
            this.$axios.get("/api/patient/listAll?familyid=" + deptid).then(res => {
                console.log(res.data)
                this.options = res.data.data;
            })
        },
        changePatient() {
            this.getPatientById(this.pid);
        }
    }
}
</script>

<style>
.box-card {
    width: 800px;
}
</style>
