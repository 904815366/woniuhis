// <template>
//   <div class="tinymce-editor">
//     <editor v-model="myValue" :init="init" :disabled="disabled" @onClick="onClick" />
//   </div>
// </template>

// <script>
// import skin from 'tinymce/skins/ui/oxide/skin.min.css'// 引入的css样式
// import tinymce from 'tinymce/tinymce'
// import 'tinymce/icons/default/icons'
// import 'tinymce/themes/silver/theme'
// import Editor from '@tinymce/tinymce-vue'
// import 'tinymce/plugins/template' // 模板插入
// import 'tinymce/plugins/preview' // 预览
// import 'tinymce/plugins/save' // 保存
// import 'tinymce/plugins/searchreplace' // 查找替换
// import 'tinymce/plugins/print' // 打印
// import 'tinymce/plugins/image' // 插入上传图片插件
// import 'tinymce/plugins/media' // 插入视频插件
// import 'tinymce/plugins/table' // 插入表格插件
// import 'tinymce/plugins/lists' // 列表插件
// import 'tinymce/plugins/contextmenu' // 右键菜单插件
// import 'tinymce/plugins/wordcount' // 字数统计插件
// import 'tinymce/plugins/colorpicker' // 字体颜色
// import 'tinymce/plugins/textcolor' // 文本颜色插件
// import 'tinymce/plugins/advlist'
// import 'tinymce/plugins/paste'
// import 'tinymce/plugins/fullscreen' // 全屏插件
// import 'tinymce/plugins/code' // 代码插件

// export default {
//   components: {
//     Editor
//   },
//   props: {
//     supThis: {
//       type: Object,
//       required: true
//     },
//     // 传入一个value，使组件支持v-model绑定
//     value: {
//       type: String,
//       default: ''
//     },
//     disabled: {
//       type: Boolean,
//       default: false
//     },
//     plugins: {
//       type: [String, Array],
//       default:
//         'code lists image media template table textcolor wordcount contextmenu advlist paste searchreplace preview save print fullscreen'
//     },
//     toolbar: {
//       type: [String, Array],
//       default:
//         'undo redo |  formatselect | bold italic | alignleft aligncenter alignright alignjustify |code searchreplace preview save template print fullscreen | bullist numlist outdent indent | lists image media table  | removeformat | fontselect forecolor backcolor underline strikethrough'
//     },
//     templates: {
//       type: [String, Array, Object],
//       'default': null
//     }
//   },
//   data() {
//     return {
//       // 初始化配置
//       init: {
//         menu: {// 菜单栏项目点击后下拉的内容，items为下拉内容
//           file: { title: 'File', items: 'newdocument' },
//           edit: { title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall' },
//           insert: { title: 'Insert', items: 'link template  lists image media table  hr' },
//           // view: {title: 'View', items: 'visualaid'},
//           format: {
//             title: 'Format',
//             items: 'bold italic underline strikethrough superscript subscript | formats | removeformat'
//           },
//           // items里的所有配置项所属为table插件，如果没引入table插件，菜单栏该项目不显示
//           table: { title: 'Table', items: 'inserttable tableprops deletetable | cell row column' }
//         },
//         selector: '#tinydemo', // change this value according to your HTML
//         // menubar: "insert", // 添加插入按钮（下拉框显示图片多媒体模板表格）
//         // language_url: "./zh_CN.js",
//         language: 'zh_CN',
//         skin_url: skin,
//         height: 300,
//         plugins: this.plugins,
//         toolbar: this.toolbar,
//         branding: false,
//         paste_data_images: true, // 图片复制功能
//         powerpaste_allow_local_images: true,
//         end_container_on_empty_block: true,
//         // word复制保存原样式
//         powerpaste_word_import: 'propmt', // 参数可以是propmt, merge, clear
//         powerpaste_html_import: 'propmt', // propmt, merge, clear
//         // menubar: false,
//         // 此处为图片上传处理函数，这个直接用了base64的图片形式上传图片，
//         // 如需ajax上传可参考https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_handler
//         images_upload_handler: (blobInfo, success, failure) => {
//           const img = 'data:image/jpeg;base64,' + blobInfo.base64()
//           success(img)
//         },
//         // save_enablewhendirty: true,
//         // 保存
//         // save_onsavecallback: (val) => {
//         //   this.supThis.save()
//         // },
//         // 模板可以具有动态内容/智能内容，该内容可以通过template_replace_values键中的功能进行更新
//         templates: this.templates
//         //  template_replace_values: this.supThis.replaceValues,
//         //     templates: [
//         //     {title: 'cdate', description: 'cdate', content: 'Name: {$username}, StaffID: {$staffid}'}
//         // ],
//         // 模板插入回调
//         // templates:(val)=>{
//         //   this.supThis.insertTemplate()
//         // },
//       },
//       myValue: this.value
//     }
//   },
//   watch: {
//     value(newValue) {
//       this.myValue = newValue
//     },
//     myValue(newValue) {
//       this.$emit('input', newValue)
//     }
//   },
//   mounted() {
//     tinymce.init({})
//   },
//   methods: {
//     // 添加相关的事件，可用的事件参照文档=> https://github.com/tinymce/tinymce-vue => All available events
//     // 需要什么事件可以自己增加
//     onClick(e) {
//       this.$emit('onClick', e, tinymce)
//     },
//     // 可以添加一些自己的自定义事件，如清空内容
//     clear() {
//       this.myValue = ''
//     },
//     print() {
//       tinymce.execCommand('mcePrint')// .execCommand('mcePreview')
//     },
//     handleSave() {
//       console.log('表单提交')
//     }
//   }
// }
// </script>
// <style scoped></style>
