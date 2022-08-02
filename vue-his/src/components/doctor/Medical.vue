<template>
    <div>
        <el-descriptions class="margin-top" title="病案首页" :column="8" border>
            <template slot="extra">
                <el-select v-model="pid" filterable placeholder="请选择患者" @change="changePatient()"
                    style="margin-right: 10px;">
                    <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                </el-select>
            </template>
        </el-descriptions>
        <!-- 病人信息 -->
        <div
            style="margin-top:10px;padding-top:10px;background-color: white; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
            <el-divider content-position="left">基本信息</el-divider>
            <!-- 病人入院登记的信息 -->
            <el-form ref="form" :model="register" label-width="100px">

                <el-form-item label="住院号:" style="display:inline-block">
                    <el-input v-model="register.id" autocomplete="off" style="width: 50px;" :disabled="true">
                    </el-input>

                </el-form-item>

                <el-form-item label="姓名:" style="display:inline-block">
                    <el-input v-model="register.name" autocomplete="off" style="width: 150px" :disabled="true">
                    </el-input>
                </el-form-item>

                <el-form-item label="性别:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 50px;" v-model="register.sex" :disabled="true">
                    </el-input>

                </el-form-item>

                <el-form-item label="联系方式:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 130px;" v-model="register.phone" :disabled="true">
                    </el-input>
                </el-form-item>

                <el-form-item label="年龄:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 50px;" v-model="register.age" :disabled="true">
                    </el-input>
                </el-form-item>



                <el-form-item label="身份证号码:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 180px;" v-model="register.card" :disabled="true">
                    </el-input>
                </el-form-item>

                <el-form-item label="住址:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 300px; " v-model="register.area" :disabled="true">
                    </el-input>
                </el-form-item>

                <el-form-item label="诊断:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 280px; " v-model="register.diagnose" :disabled="true">
                    </el-input>
                </el-form-item>

                <el-form-item label="余额:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 100px; color:red; " v-model="register.money"
                        :disabled="true">
                    </el-input>

                </el-form-item>

                <el-form-item label="状态:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 100px; " v-model="register.status" :disabled="true">
                    </el-input>
                </el-form-item>
                <el-form-item label="入院时间:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 200px; color:red; " v-model="register.entertime"
                        :disabled="true">
                    </el-input>
                </el-form-item>
                <el-form-item label="出院时间:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 200px; color:red; " v-model="register.outtime"
                        :disabled="true">
                    </el-input>
                </el-form-item>
                <el-form-item label="住院天数:" style="display:inline-block">
                    <el-input autocomplete="off" style="width: 200px; color:red; " v-model="register.days"
                        :disabled="true">
                    </el-input>
                </el-form-item>
            </el-form>
            <el-divider content-position="left">医疗记录</el-divider>
            <el-table :data="tableData" style="width: 100%;margin-top: 10px;margin-left:30px;" max-height="100%">
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
            </el-table>

            <el-divider content-position="left">费用情况</el-divider>

            <!-- 预交缴费列表 -->
            <el-table :data="MoneyList" height="280" style="width: 100%; height:450px; margin-left:30px; " :fit="false">
                <el-table-column prop="id" label="消费编号" width="100" align="center">
                </el-table-column>
                <el-table-column prop="consummoney" label="消费金额(元)" width="200" align="center">
                </el-table-column>
                <el-table-column label="消费时间" width="200" align="center">
                    <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.consumtime | dateconverter }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="consumpart" label="消费详情" width="200" align="center">
                </el-table-column>
                <el-table-column label="状态" width="200" align="center">
                    <template slot-scope="scope">
                        <span v-if="scope.row.status == 0" style="color:red">未结算</span>
                        <span v-if="scope.row.status == 1" style="color:green">已结算</span>
                    </template>
                </el-table-column>
            </el-table>

        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            pid: '',
            options: [],
            //查询的住院信息
            register: {},
            //消费列表
            MoneyList: [],
            tableData: [],
        }
    },
    created() {
        this.getPatientListAll();
    },
    methods: {
        getPatientById(id) {
            this.axios.get("/api/register/queryById/" + id + "/-1").then(res => {
                if (res.data.status === 2000) {
                    this.register = res.data.data;
                    this.register.status = this.statusNumberFormString(this.register.status);
                    this.QueryMoneyListByRegisterId(-1);
                    // this.register.days = this.register.outtime - this.register.entertime;
                    if (res.data.data.outtime != null) {
                        var endTime = new Date(this.register.outtime).getTime() / 1000 - new Date(this.register.entertime).getTime() / 1000;
                        this.register.days = parseInt(endTime / 60 / 60 / 24);        //相差天数
                    } else {
                        var endTime = parseInt(new Date().getTime() / 1000) - new Date(this.register.entertime).getTime() / 1000;
                        this.register.days = parseInt(endTime / 60 / 60 / 24);        //相差天数
                    }
                } else {
                    this.$message({
                        message: '未查询到信息,请先申请出院或住院号不存在!',
                        type: 'error'
                    });
                }
            })
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
        },
        //类型转换方法
        statusNumberFormString(obj) {
            let searchStatus = '';
            if (obj == '-1') {
                searchStatus = '查询所有'
            } else if (obj == 0) {
                searchStatus = '未缴费'
            } else if (obj == 1) {
                searchStatus = '已缴费'
            } else if (obj == 2) {
                searchStatus = '住院中'
            } else if (obj == 3) {
                searchStatus = '申请出院'
            } else if (obj == 4) {
                searchStatus = '审核通过'
            } else if (obj == 5) {
                searchStatus = '已出院'
            }
            return searchStatus;
        },
        //根据住院号查询病人消费列表
        QueryMoneyListByRegisterId(status) {
            this.axios.get("/api/moneylist/query/registerIdAndStatus", {
                params: {
                    registerId: this.pid,
                    status: status,
                }
            }).then(res => {
                if (res.data.status === 2000) {
                    this.MoneyList = res.data.data;
                }
            });
        },
    }
}
</script>

<style>
</style>
