<template>
    <div>
        <el-descriptions class="margin-top" title="医嘱录入" :column="8" border>
            <template slot="extra">
                <el-select v-model="pid" filterable placeholder="请选择患者" @change="changePatient()" style="margin-right: 10px;">
                    <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                </el-select>
            </template>
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
        <el-table :data="tableData" style="width: 100%;margin-top: 10px;" max-height="100%"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" :selectable="selectHandle">
            </el-table-column>
            <el-table-column prop="date" label="开立时间" width="180">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.warntime }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="drugname" label="医嘱名称" width="200">
            </el-table-column>
            <el-table-column prop="num" label="数量" width="100">
            </el-table-column>
            <el-table-column prop="measure" label="单次剂量" width="120">
            </el-table-column>
            <el-table-column prop="directions" label="用法用量" width="200">
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100">
                <template slot-scope="scope">
                    <el-tag type="info" v-if="scope.row.status == '0'">
                        未提交
                    </el-tag>
                    <el-tag v-if="scope.row.status == '1'">
                        已提交
                    </el-tag>
                    <el-tag type="success" v-if="scope.row.status == '2'">
                        已执行
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="doctorname" label="开立医生" width="100">
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" @click="comName = 'editCpoe', handleEdit(scope.row)"
                        circle v-if="scope.row.status == '0'"></el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.row.id)" circle
                        v-if="scope.row.status != '2'"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--background是否显示背景色,layout显示分特的布局组件,prev上一下next下一页pager导航页码sizes每页记录数
            total设置总记录数,page-size梅特记录数,current-page当前页码
          -->
        <el-pagination :background="true" layout="prev, pager, next,sizes,jumper,->,total" prev-text="上一页"
            @size-change="handleSizeChange" @current-change="handleCurrentChange" next-text="下一页"
            :page-sizes='pageSizes' :page-size=pageSize :current-page="pageNum" :total="totalCount"
            style="margin-top: 10px;">
        </el-pagination>
        <!-- 指定显示名称的子组件并传值 -->
        <component :is="comName" @func="handleShow" :patient="patient" :cpoe="cpoe"></component>
    </div>
</template>

<script>
//引入子组件
import addCpoe from '../doctor/AddCpoe.vue'
import editCpoe from '../doctor/EditCpoe.vue'
export default {
    data() {
        return {
            patient: {},//患者信息
            tableData: [],//医嘱列表数据
            pageNum: 1,
            pageSize: 5,
            totalCount: 0,
            pageSizes: [5, 10, 15, 20],
            comName: '',
            cpoe: {},
            ids: [],
            options: [],
            value: '',
            pid:''
        }
    }, components: {
        addCpoe, editCpoe
    },
    created() {
        let id = this.$route.query.id;
        if (id == null) {
            this.$message({
                showClose: true,
                message: '请先选择患者!',
                type: 'error',
                //offset: 550,
                duration: 1000  //显示的时间,ms
            });
        } else {
            this.getPatientById(id);
            this.getWarnListById(id, 1);
        }
        this.getPatientListAll();
    },
    methods: {
        getPatientById(id) {
            this.$axios.get("/api/patient/getPatientById?id=" + id).then(res => {
                this.patient = res.data.data;
            })
        },
        getWarnListById(id, pNum) {
            this.$axios.get("/api/warn/getWarnList?patientid=" + id + "&pageNum=" + pNum + "&pageSize=" + this.pageSize)
                .then(res => {
                    console.log(res.data);
                    this.tableData = res.data.data.list;
                    this.totalCount = res.data.data.total;
                    this.pageNum = res.data.data.pageNum;
                    this.pageSize = res.data.data.pageSize;
                }).catch(e => {
                    this.$message({
                        showClose: true,
                        message: '服务器跑不见了!',
                        type: 'error',
                        offset: 550,
                        duration: 1000  //显示的时间,ms
                    });
                });
        },
        //页码尺寸改变
        handleSizeChange(pSize) {
            let id;
            if(this.$route.query.id==null){
                id=this.pid;
            }else{
                id=this.$route.query.id;
            }
            this.pageSize = pSize;
            this.pageNum = 1;//默认为第一页
            this.getWarnListById(id, 1);//初始化为第一页
        },
        // 当前页码改变
        handleCurrentChange(pNo) {
            let id;
            if(this.$route.query.id==null){
                id=this.pid;
            }else{
                id=this.$route.query.id;
            }
            this.pageNum = pNo;
            this.getWarnListById(id, pNo);//翻页
        },
        handleShow() {
            this.comName = '';
        }, handleEdit(cp) {
            this.cpoe = cp;
        },
        handleDelete(id) {
            this.$axios.get("/api/warn/delCpoeById?id=" + id).then(res => {
                console.log(res.data)
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
        },
        handleSelectionChange(val) {
            this.ids=[];
            for (let i = 0; i < val.length; i++) {
                this.ids[i] = val[i].id;
            }
            console.log(this.ids);
        },
        selectHandle(row) {
            if (row.status != '0') {
                return false
            } else {
                return true
            }
        },
        updateStatus() {
            if (this.ids == null) {
                this.$message({
                    showClose: true,
                    message: '请先选择要提交的医嘱!',
                    type: 'error',
                    duration: 1000  //显示的时间,ms
                });
            } else {
                let result = this.ids.join(",");
                this.$axios.get("/api/warn/updateCpoeStatus?ids=" + result+"&status=1")
                    .then(res => {
                        if (res.data.status == 200) {
                            this.$message({
                                showClose: true,
                                message: "提交成功",
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
        },
        getPatientListAll(){
            let deptid=window.sessionStorage.getItem("currentUserFamilyId");
            this.$axios.get("/api/patient/listAll?familyid="+deptid).then(res=>{
                console.log(res.data)
                this.options=res.data.data;
            })
        },
        changePatient(){
            this.getPatientById(this.pid);
            this.getWarnListById(this.pid, 1);
        }
    }
}
</script>

<style>
</style>
