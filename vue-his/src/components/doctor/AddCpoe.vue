<template>
    <div>
        <el-dialog title="医嘱开立" :visible.sync="addDialogFormVisible" @close="cancelAdd">
            <el-form :model="cpoe">
                <input type="hidden" name="patientid" :value="patient.id" />
                <el-form-item label="开立时间:">
                    <el-date-picker v-model="cpoe.warntime" type="datetime" placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="药品名称:">
                    <el-select v-model="value" filterable placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="药品数量:">
                    <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="描述文字">
                    </el-input-number>
                </el-form-item>
                <el-form-item label="单次剂量:">
                    <el-input v-model="input" style="width:220px" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="用法用量:">
                    <el-input type="textarea" :rows="2" style="width:220px" placeholder="请输入内容" v-model="textarea">
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
            num: 1,
            options: [{
                value: '选项1',
                label: '青霉素'
            }, {
                value: '选项2',
                label: '头孢'
            }, {
                value: '选项3',
                label: '利巴韦林'
            }, {
                value: '选项4',
                label: '感康'
            }, {
                value: '选项5',
                label: '维生素c'
            }],
            value: ''
        }
    },
    props: ['patient'],//接收父组件传来的属性值
    created() {
        this.cpoe.warntime = new Date();
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
        }
    }
}
</script>

<style>
</style>
