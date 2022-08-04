<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>
                <router-link to="/doctor/notification">住院通知</router-link>
            </el-breadcrumb-item>
        </el-breadcrumb>
        <el-row style="margin-top: 10px;">
            <el-col :span="8">
                <el-input placeholder="请输入查询患者的名字" v-model="searchName">
                    <i slot="prefix" class="el-input__icon el-icon-search"></i>
                    <el-button slot="append" icon="el-icon-search" @click="getAppByName(1)"></el-button>
                </el-input>
            </el-col>
            <el-col :span="8" style="margin-left: 10px;">
                <el-button type="success" @click="comName = 'addNotification'">开具住院通知单</el-button>
            </el-col>
        </el-row>
        <el-table :data="tableData" style="width: 100%;margin-top: 10px;" max-height="100%">
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="100">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.sex == '男' ? 'success' : 'danger'">
                        {{ scope.row.sex == '男' ? '男' : '女' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="100">
            </el-table-column>
            <el-table-column prop="phone" label="联系方式" width="120">
            </el-table-column>
            <el-table-column prop="card" label="身份证号" width="120">
            </el-table-column>
            <el-table-column prop="diagnose" label="入院诊断" width="120">
            </el-table-column>
            <el-table-column prop="date" label="预计入院时间" width="200">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.entertime }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="familyname" label="入住科室" width="120">
            </el-table-column>
            <el-table-column prop="status" label="状态" width="100">
                <template slot-scope="scope">
                <el-tag type="info" v-if="scope.row.status == '0'">未缴费</el-tag>
                    <el-tag v-if="scope.row.status == '1'">已缴费</el-tag>
                    <el-tag type="success" v-if="scope.row.status == '2'">入院中</el-tag>
                    <el-tag type="warning" v-if="scope.row.status == '3'">申请出院</el-tag>
                    <el-tag type="danger" v-if="scope.row.status == '4'">审核通过</el-tag>
                    <el-tag type="danger" v-if="scope.row.status == '5'">已出院</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.row.id)" circle
                        v-if="scope.row.status == '0'"></el-button>
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
        <component :is="comName" @func="handleShow"></component>
    </div>
</template>

<script>
//引入子组件
import addNotification from '../doctor/AddNotification.vue'
export default {
    inject: ["reload"],
    data() {
        return {
            tableData: [],
            searchName: '',
            pageNum: 1,
            pageSize: 5,
            totalCount: 0,
            pageSizes: [5, 10, 15, 20],
            comName: ''
        }
    }, components: {
        addNotification
    },
    created() {
        this.getAppByName(1);
    },
    methods: {
        getAppByName(pNum) {
            this.$axios.get("/api/appointment/queryListByName?searchName=" + this.searchName + "&pageNum=" + pNum + "&pageSize=" + this.pageSize).then(res => {
                console.log(res.data);
                this.tableData = res.data.data.list;
                this.totalCount = res.data.data.total;
                this.pageNum = res.data.data.pageNum;
                this.pageSize = res.data.data.pageSize;
            })
        },//页码尺寸改变
        handleSizeChange(pSize) {
            this.pageSize = pSize;
            this.pageNum = 1;//默认为第一页
            this.getAppByName(1);//初始化为第一页
        },// 当前页码改变
        handleCurrentChange(pNo) {
            this.pageNum = pNo;
            this.getAppByName(pNo);//翻页
        }, handleShow() {
            this.comName = '';
        },
        handleDelete(id) {
            console.log(id);
            this.$axios.get("/api/appointment/delAppointment?id="+id).then(res=>{
                console.log(res.data);
                this.reload();
            })
        }
    },
}
</script>

<style>
</style>
