<template>
    <div>
        <div class="tinymce-editor">
            <editor :id="tinymceId" v-model="myValue" :init="init" v-bind="$attrs" v-on="inputListeners">
            </editor>
        </div>
    </div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";

export default {
    name: "MyEditor",
    components: { Editor, Toolbar },
    data() {
        return {
            editor: null,
            html: "<p>hello&nbsp;world</p>",
            toolbarConfig: {
                // toolbarKeys: [ /* 显示哪些菜单，如何排序、分组 */ ],
                // excludeKeys: [ /* 隐藏哪些菜单 */ ],
            },
            editorConfig: {
                placeholder: "请输入内容...",
                // autoFocus: false,
                // 所有的菜单配置，都要在 MENU_CONF 属性下
                MENU_CONF: {},
            },
        };
    },
    methods: {
        onCreated(editor) {
            this.editor = Object.seal(editor); // 【注意】一定要用 Object.seal() 否则会报错
        },
        onChange(editor) {
            console.log("onChange", editor.getHtml()); // onChange 时获取编辑器最新内容
        }
    },
    mounted() {
        // 模拟 ajax 请求，异步渲染编辑器
        setTimeout(() => {
            this.html = "";
        }, 1500);
    },
    beforeDestroy() {
        const editor = this.editor;
        if (editor == null) return;
        editor.destroy(); // 组件销毁时，及时销毁 editor ，重要！！！
    },
};
</script>

<style src="@wangeditor/editor/dist/css/style.css">
</style>
