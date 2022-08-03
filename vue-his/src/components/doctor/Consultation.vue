<template>
    <div>
        <el-descriptions class="margin-top" title="会诊管理" :column="8" border>
            <template slot="extra">
                <el-select v-model="pid" filterable placeholder="请选择患者" @change="changePatient()"
                    style="margin-right: 10px;">
                    <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                </el-select>
            </template>
            <template slot="extra">
                <el-button type="success" round size="mini" @click="comName = 'addConsultation'">发起会诊</el-button>
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
                <span slot="label"><i class="el-icon-notebook-1"></i>本科会诊</span>
                <el-table :data="applyData" style="width: 100%;margin-top: 10px;" max-height="100%">
                    <el-table-column prop="patientname" label="姓名" width="200">
                    </el-table-column>
                    <el-table-column prop="consutantstype" label="会诊类型" width="100">
                        <template slot-scope="scope">
                            <el-tag type="success" v-if="scope.row.consutantstype == '0'">
                                普通
                            </el-tag>
                            <el-tag type="error" v-if="scope.row.consutantstype == '1'">
                                紧急
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="goal" label="会诊目的" width="120">
                    </el-table-column>
                    <el-table-column prop="familyname" label="回复科室" width="120">
                    </el-table-column>
                    <el-table-column prop="status" label="状态" width="100">
                        <template slot-scope="scope">
                            <el-tag type="info" v-if="scope.row.status == '0'">
                                未回复
                            </el-tag>
                            <el-tag type="success" v-if="scope.row.status == '1'">
                                已回复
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-edit"
                                @click="comName = 'viewConsultation', handleEdit(scope.row)" circle></el-button>
                            <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.row.id)" circle
                                v-if="scope.row.status == '0'"></el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><i class="el-icon-edit"></i>回复会诊</span>
                <el-table :data="replyData" style="width: 100%;margin-top: 10px;" max-height="100%">
                    <el-table-column prop="patientname" label="姓名" width="200">
                    </el-table-column>
                    <el-table-column prop="consutantstype" label="会诊类型" width="100">
                        <template slot-scope="scope">
                            <el-tag type="success" v-if="scope.row.consutantstype == '0'">
                                普通
                            </el-tag>
                            <el-tag type="error" v-if="scope.row.consutantstype == '1'">
                                紧急
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="goal" label="会诊目的" width="120">
                    </el-table-column>
                    <el-table-column prop="familyname" label="申请科室" width="120">
                    </el-table-column>
                    <el-table-column prop="status" label="状态" width="100">
                        <template slot-scope="scope">
                            <el-tag type="info" v-if="scope.row.status == '0'">
                                未回复
                            </el-tag>
                            <el-tag type="success" v-if="scope.row.status == '1'">
                                已回复
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-edit-outline"
                                @click="comName = 'replyConsultation', reply(scope.row)" v-if="scope.row.status == '0'">
                                回复</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
        </el-tabs>
        <!-- 指定显示名称的子组件并传值 -->
        <component :is="comName" @func="handleShow" :replyobj="replyobj" :applyobj="applyobj" :patient="patient">
        </component>
    </div>
</template>

<script>
//引入子组件
import addConsultation from '../doctor/AddConsultation.vue'
import replyConsultation from '../doctor/ReplyConsultation.vue'
import viewConsultation from '../doctor/ViewConsultation.vue'
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
            applyData: [],
            replyData: [],
            deptid: '',
            comName: '',
            replyobj: {},
            applyobj: {}
        }
    },
    components: {
        addConsultation,
        replyConsultation,
        viewConsultation
    },
    created() {
        this.deptid = window.sessionStorage.getItem("currentUserFamilyId");
        this.getByApplydeptid();
        this.getByReplydeptid();
        this.getPatientListAll();
    },
    methods: {
        handleShow() {
            this.comName = '';
        },
        getPatientById(id) {
            this.$axios.get("/api/patient/getPatientById?id=" + id).then(res => {
                this.patient = res.data.data;
            })
        },
        getPatientListAll() {
            console.log(this.deptid)
            this.$axios.get("/api/patient/listAll?familyid=" + this.deptid).then(res => {
                console.log(res.data)
                this.options = res.data.data;
            })
        },
        changePatient() {
            this.getPatientById(this.pid);
        },
        getByApplydeptid() {
            this.$axios.get("/api/consutants/getByApplydeptidList?deptId=" + this.deptid).then(res => {
                console.log(res.data);
                this.applyData = res.data.data;
            })
        },
        getByReplydeptid() {
            this.$axios.get("/api/consutants/getByReplydeptid?deptId=" + this.deptid).then(res => {
                console.log(res.data);
                this.replyData = res.data.data;
            })
        },
        reply(obj) {
            console.log(obj)
            this.replyobj = obj;
        },
        handleEdit(obj) {
            this.applyobj = obj;
        },
        handleDelete(id) {
            this.$axios.get("/api/consutants/removeById?id=" + id).then(res => {
                if (res.data.status == 200) {
                    this.$message({
                        showClose: true,
                        message: "删除成功",
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
        }
    }
}
</script>

<style>
</style>
