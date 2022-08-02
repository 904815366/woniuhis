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
            <template slot="extra">
                <el-button type="success" round size="mini" @click="addEmr">保存记录</el-button>
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
                <el-table :data="tableData" style="width: 100%;margin-top: 10px;" max-height="100%"
                    @cell-dblclick="dbclick">
                    <el-table-column prop="name" label="记录名称" width="120">
                    </el-table-column>
                    <el-table-column prop="date" label="创建时间" width="200">
                        <template slot-scope="scope">
                            <i class="el-icon-time"></i>
                            <span style="margin-left: 10px">{{ scope.row.createdate }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="username" label="创建人" width="120">
                    </el-table-column>
                    <el-table-column label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-view"
                                @click="comName = 'viewEmr', viewEdit(scope.row)" circle></el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!-- 指定显示名称的子组件并传值 -->
                <component :is="comName" @func="handleShow" :lookEmr="lookEmr"></component>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><i class="el-icon-edit"></i>入院记录</span>
                <el-form :model="emr">
                    <my-editor></my-editor>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
import myEditor from './MyEditor.vue'
import viewEmr from './ViewEmr.vue'
export default {
    data() {
        return {
            patient: {},//患者信息
            options: [],
            value: '',
            pid: '',
            editableTabsValue: '2',
            editableTabs: [],
            tabIndex: 2,
            tableData: [],
            content: '',
            emr: {},
            view: '',
            comName: '',
            lookEmr:''
        }
    },
    components: {
        myEditor, viewEmr
    },
    created() {
        this.getPatientListAll();
        // 当"我"收到 xxx 事件（信号）的时候，就自动执行"我"的showMessage 方法
        this.$bus.on('input', this.showMessage);
    },
    methods: {
        getPatientById(id) {
            this.$axios.get("/api/patient/getPatientById?id=" + id).then(res => {
                this.patient = res.data.data;
            });
            this.$axios.get("/api/emr/getEmrListById?registerid=" + id).then(res => {
                console.log(res.data);
                this.tableData = res.data.data;
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
        }, beforeDestroy() {
            // 上面的 on 的反向操作：解绑定
            this.$bus.off('input', this.showMessage);
        },
        showMessage(data) {
            this.content = data;
        },
        addEmr() {
            this.emr.name = '入院记录';
            this.emr.registerid = this.patient.id;
            this.emr.content = this.content;
            this.emr.userid = window.sessionStorage.getItem('currentUserId');
            this.$axios.post("/api/emr/addEmr", this.emr).then(res => {
                if (res.data.status == 200) {
                    this.$message({
                        showClose: true,
                        message: "保存成功",
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
        dbclick(row, column) {
            this.view = row.content;
        },
        viewEdit(obj) {
            this.lookEmr=obj.content;
        },
        handleShow() {
            this.comName = '';
        }
    }
}
</script>

<style>
.box-card {
    width: 800px;
}
</style>
