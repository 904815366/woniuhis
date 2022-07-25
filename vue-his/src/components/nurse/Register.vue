<template>
    <div>
        <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><router-link to="/techer">入院登记</router-link></el-breadcrumb-item>
      <el-breadcrumb-item>预约病人列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row style="margin-top: 10px">
      <el-col :span="8"
        ><el-input v-model="name" placeholder="请输入病人编号">
          <!-- 输入框内的放大镜按钮 -->
          <el-button slot="append" icon="el-icon-search" @click="patientid">
          </el-button></el-input
      ></el-col>
      <el-col :span="8" style="margin-left: 10px">
        </el-col
      >
      <el-col :span="8"></el-col>
    </el-row>

    <!-- 讲师列表的表格 -->
    <el-table :data="teacherData" style="width: 100%">

    <el-table-column
      type="index"
      :index="indexMethod"
        prop="id"
        label="序号"
        width="80">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="education"
        label="毕业院校"
        width="140">
      </el-table-column>
      <el-table-column
        prop="career"
        label="教育经历"
        width="140">
      </el-table-column>

      <el-table-column  label="是否名师" width="120" align="center">
         <template slot-scope="scope">
         <!-- 判断是否添加颜色背景 -->
         <el-tag :type="scope.row.isfamous=='y' ? 'success' : 'danger' ">
         <!-- 三目表达式赋值 -->
            {{scope.row.isfamous=='y' ? '是' : '否' }}
         </el-tag>
        </template>
      </el-table-column>

       <el-table-column  label="任课科目" width="180" align="center">
         <template slot-scope="scope">
         <!-- 查询到所有的课程遍历结果并且判断赋值 -->
            <span v-for="subject in subjectData" :key="subject.id">
            <span v-if="subject.id===scope.row.subjectid">{{subject.name}}</span>
            </span>
        </template>
      </el-table-column>

       <el-table-column  label="在职状态" width="120" align="center">
         <template slot-scope="scope">
         <!-- 判断是否添加颜色背景 -->
         <el-tag :type="scope.row.isfamous=='y' ? 'success' : 'danger' ">
         <!-- 三目表达式赋值 -->
            {{scope.row.isfamous=='y' ? '在职' : '离职' }}
         </el-tag>
        </template>
      </el-table-column>


      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" 
          type="primary"
           @click="gotoup(scope.row)"
           
            >修改</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
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
             layout="prev, pager, next,sizes,jumper"
             prev-text="上一页"
             next-text="下一页"
             :page-sizes="[5,10,15,20,25,30]"
             :page-size="pageSize"
             :current-page="pageNum"
             :total="total">
            </el-pagination>
        <!-- 指定显示名称的组件 -->
          <component :is="comname"  @func="turnSon" :uptecher="techer"></component>
  </div>
    </div>
</template>

<script>
export default {
   data() {
      return {
            patientid: '',
      }
   },
   created(){

   },
   methods:{

   }
}
</script>

<style>

</style>
