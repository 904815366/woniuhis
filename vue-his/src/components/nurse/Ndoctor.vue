<template>
  <div>
    <el-descriptions class="margin-top" title="医嘱处理" :column="8" border>
      <template slot="extra">
        <el-select
          v-model="pid"
          filterable
          placeholder="请选择患者"
          @change="getRegister()"
          style="margin-right: 10px"
        >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </template>
      <template slot="extra">
        <el-button type="warning" round size="mini" @click="updateStatus"
          >批量执行</el-button
        >
      </template>
      <el-descriptions-item>
        <template slot="label"> <i class="el-icon-receiving"></i> 床号: </template>
        {{ patient.bedid }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label"> <i class="el-icon-user"></i> 姓名: </template>
        {{ patient.name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i :class="patient.sex == '男' ? 'el-icon-male' : 'el-icon-female'"></i> 性别:
        </template>
        {{ patient.sex }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label"> <i class="el-icon-edit"></i> 年龄: </template>
        {{ patient.age }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label"> <i class="el-icon-notebook-2"></i> 诊断: </template>
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
          <i class="el-icon-date"></i>
          入院时间
        </template>
        {{ patient.entertime }}
      </el-descriptions-item>
    </el-descriptions>
    <!-- 第二张表格 -->
    <el-table
      :data="tableData"
      style="width: 100%; margin-top: 10px"
      max-height="100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" :selectable="selectHandle">
      </el-table-column>
      <el-table-column prop="date" label="开立时间" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.warntime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="drugname" label="医嘱名称" width="200"> </el-table-column>
      <el-table-column prop="num" label="数量" width="100"> </el-table-column>
      <el-table-column prop="measure" label="单次剂量" width="120"> </el-table-column>
      <el-table-column prop="directions" label="用法用量" width="200"> </el-table-column>
      <el-table-column prop="status" label="状态" width="100">
        <template slot-scope="scope">
          <el-tag type="info" v-if="scope.row.status == '0'"> 未提交 </el-tag>
          <el-tag v-if="scope.row.status == '1'"> 未执行 </el-tag>
          <el-tag type="success" v-if="scope.row.status == '2'"> 已执行 </el-tag>
          <el-tag type="success" v-if="scope.row.status == '3'"> 已发药 </el-tag>
          <el-tag type="success" v-if="scope.row.status == '4'"> 已出院历史 </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="doctorname" label="开立医生" width="100"> </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            type="primary"
            @click="(comName = 'editCpoe'), upDoctor(scope.row.id)"
            circle
            v-if="scope.row.status == '1'"
            >执行</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 
     :background 表示背景颜色开启不开启 默认是false
     layout 表示分页组件的显示布局 
      prev上一页   pager导航页码   next下一页   sizes 每页记录数 
       total 设置总记录数字 jumper前往第几页
    -->
    <el-pagination
      @current-change="changepagebynum"
      @size-change="sizepageSize"
      :background="true"
      layout="prev, pager, next,jumper"
      prev-text="上一页"
      next-text="下一页"
      :page-sizes="[5, 10, 15, 20, 25, 30]"
      :page-size="pageSize"
      :current-page="pageNum"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      patient: {}, //患者信息
      tableData: [], //医嘱列表数据
      pageNum: 1,
      pageSize: 5,
      pageInfo: null,
      total: null,
      totalCount: 0,
      pageSizes: [5, 10, 15, 20],
      comName: "",
      cpoe: {},
      ids: [],
      options: [],
      value: "",
      pid: "",
    };
  },
  created() {
    this.getPatientListAll();
  },
  methods: {
    upDoctor(x) {
      //单条修改状态
      this.$axios.get("/api/warn/updateCpoeStatus?ids=" + x + "&status=2").then((res) => {
        if (res.data.status == 200) {
          this.$message({
            showClose: true,
            message: "提交成功",
            type: "success",
            center: true,
          });
          this.gotoWarnByRid();
        } else {
          this.$message({
            showClose: true,
            message: "服务异常!",
            type: "error",
            duration: 1000, //显示的时间,ms
          });
        }
      });
    },
    changepagebynum(pageNum) {
      this.pageNum = pageNum;
      this.gotoWarnByRid();
    },
    sizepageSize(pageSize) {
      this.pageSize = pageSizee;
      this.gotoWarnByRid();
    },
    updateStatus() {
      //批量修改状态的方法
      if (this.ids == null) {
        this.$message({
          showClose: true,
          message: "请先选择要执行的医嘱!",
          type: "error",
          duration: 1000, //显示的时间,ms
        });
      } else {
        let result = this.ids.join(",");
        this.$axios
          .get("/api/warn/updateCpoeStatus?ids=" + result + "&status=2")
          .then((res) => {
            if (res.data.status == 200) {
              this.$message({
                showClose: true,
                message: "执行成功",
                type: "success",
                center: true,
              });
              this.gotoWarnByRid();
            } else {
              this.$message({
                showClose: true,
                message: "服务异常!",
                type: "error",
                duration: 1000, //显示的时间,ms
              });
            }
          });
      }
    },
    handleSelectionChange(val) {
      //批量修改复选框
      for (let i = 0; i < val.length; i++) {
        this.ids[i] = val[i].id;
      }
      console.log(this.ids);
    },
    getPatientListAll() {
      //查看所有的患者，根据用户科室id
      let deptid = window.sessionStorage.getItem("currentUserFamilyId");
      this.$axios.get("/api/register/gotolistAll?familyid=" + deptid).then((res) => {
        console.log(res.data);
        this.options = res.data;
      });
    },

    getRegister() {
      //查看单个患者
      this.$axios
        .get("/api/register/gotoRegisters", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            patientid: this.pid,
          },
        })
        .then((res) => {
          this.patient = res.data.list[0];
          this.gotoWarnByRid();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    gotoWarnByRid() {
      //tableData  通过患者ID查询患者医嘱
      this.$axios
        .get("/api/warn/getWarnList/", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            patientid: this.patient.patientid,
          },
        })
        .then((res) => {
          this.tableData = res.data.data.list;
          this.pageInfo = res.data.data;
          this.pageNum = res.data.data.pageNum;
          // this.pageSize=res.data.pageSize;
          this.total = res.data.data.total;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    selectHandle(row) {
      //复选框的方法
      if (row.status != "1") {
        return false;
      } else {
        return true;
      }
    },
  },
};
</script>

<style></style>
