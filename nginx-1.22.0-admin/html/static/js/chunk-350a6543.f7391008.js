(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-350a6543"],{"1aa5":function(e,t,a){},2953:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.searchAdmin}},[a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{attrs:{placeholder:"请输入用户昵称"},model:{value:e.searchAdmin.name,callback:function(t){e.$set(e.searchAdmin,"name",t)},expression:"searchAdmin.name"}})],1),a("el-form-item",{attrs:{label:"性别"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.searchAdmin.sex,callback:function(t){e.$set(e.searchAdmin,"sex",t)},expression:"searchAdmin.sex"}},[a("el-option",{attrs:{label:"男",value:"1"}}),a("el-option",{attrs:{label:"女",value:"2"}})],1)],1),a("el-form-item",{attrs:{label:"修改时间"}},[a("el-date-picker",{staticStyle:{width:"400px","margin-left":"20px"},attrs:{clearable:"","value-format":"yyyy-MM-dd",type:"daterange",placeholder:"选择日期","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.entrydate,callback:function(t){e.entrydate=t},expression:"entrydate"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("查询")]),a("el-button",{attrs:{type:"info"},on:{click:e.clear}},[e._v("清空")])],1)],1),a("el-row",[a("el-button",{attrs:{type:"danger",size:"medium"},on:{click:e.deleteByIds}},[e._v("- 批量删除")]),a("el-button",{attrs:{type:"primary",size:"medium"},on:{click:function(t){e.dialogVisible=!0,e.admin={avatar:""}}}},[e._v("+ 新增")])],1),a("el-dialog",{ref:"form",attrs:{title:"编辑用户",visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.admin,"label-width":"80px",size:"mini"}},[a("el-form-item",{attrs:{label:"用户名"}},[a("el-input",{model:{value:e.admin.username,callback:function(t){e.$set(e.admin,"username",t)},expression:"admin.username"}})],1),a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{model:{value:e.admin.name,callback:function(t){e.$set(e.admin,"name",t)},expression:"admin.name"}})],1),a("el-form-item",{attrs:{label:"密码"}},[a("el-input",{model:{value:e.admin.password,callback:function(t){e.$set(e.admin,"password",t)},expression:"admin.password"}})],1),a("el-form-item",{attrs:{label:"手机号"}},[a("el-input",{model:{value:e.admin.phone,callback:function(t){e.$set(e.admin,"phone",t)},expression:"admin.phone"}})],1),a("el-form-item",{attrs:{label:"身份证"}},[a("el-input",{model:{value:e.admin.idNumber,callback:function(t){e.$set(e.admin,"idNumber",t)},expression:"admin.idNumber"}})],1),a("el-form-item",{attrs:{label:"性别"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.admin.sex,callback:function(t){e.$set(e.admin,"sex",t)},expression:"admin.sex"}},e._l(e.sexList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"头像"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"/api/upload",headers:e.token,name:"image","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.admin.avatar?a("img",{staticClass:"avatar",attrs:{src:e.admin.avatar}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择","value-key":"admin.status"},model:{value:e.admin.status,callback:function(t){e.$set(e.admin,"status",t)},expression:"admin.status"}},e._l(e.statusList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"创建日期"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{clearable:"",type:"date",placeholder:"选择日期",size:"small"},model:{value:e.admin.createTime,callback:function(t){e.$set(e.admin,"createTime",t)},expression:"admin.createTime"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.add}},[e._v("提交")]),a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取消")])],1)],1)],1),a("br"),[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),a("el-table-column",{attrs:{prop:"name",label:"昵称",align:"center"}}),a("el-table-column",{attrs:{prop:"username",label:"用户名",align:"center"}}),a("el-table-column",{attrs:{prop:"avatar",label:"头像",align:"center"},scopedSlots:e._u([{key:"default",fn:function(e){var t=e.row;return[a("el-image",{staticStyle:{width:"auto",height:"40px",border:"none",cursor:"pointer"},attrs:{src:t.avatar}})]}}])}),a("el-table-column",{attrs:{align:"center",label:"性别"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{staticStyle:{"margin-right":"10px"}},[e._v(" "+e._s("1"==t.row.sex?"男":"女"))])]}}])}),a("el-table-column",{attrs:{prop:"phone",label:"手机号",align:"center"}}),a("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[0==t.row.status?a("span",{staticStyle:{"margin-right":"10px"}},[e._v("启用")]):e._e(),1==t.row.status?a("span",{staticStyle:{"margin-right":"10px"}},[e._v("禁用")]):e._e()]}}])}),a("el-table-column",{attrs:{prop:"idNumber",label:"身份证号",align:"center"}}),a("el-table-column",{attrs:{align:"center",label:"创建日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.createTime?t.row.createTime.replace("T"," "):"")+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"更新日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.updateTime?t.row.updateTime.replace("T"," "):"")+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.update(t.row.id)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(a){return e.deleteById(t.row.id)}}},[e._v("删除")])]}}])})],1)],a("el-pagination",{attrs:{background:e.background,"current-page":e.currentPage,"page-sizes":[5,10,15,20],"page-size":5,layout:"total, sizes, prev, pager, next, jumper",total:e.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],2)},i=[],l=(a("b0c0"),a("50fc")),r=a("5f87"),s={data:function(){return{background:!0,pageSize:5,totalCount:100,currentPage:1,dialogVisible:!1,searchAdmin:{name:"",sex:""},admin:{},sexList:[{id:1,name:"男"},{id:2,name:"女"}],statusList:[{id:0,name:"启用"},{id:1,name:"禁用"}],createTime:"",updateTime:"",entrydate:"",selectedIds:[],multipleSelection:[],tableData:[],token:{token:Object(r["a"])()}}},mounted:function(){this.page()},methods:{page:function(){var e=this;Object(l["d"])(this.searchAdmin.name,this.searchAdmin.sex,this.createTime,this.updateTime,this.currentPage,this.pageSize).then((function(t){e.totalCount=t.data.data.total,e.tableData=t.data.data.rows}))},handleSelectionChange:function(e){this.multipleSelection=e},onSubmit:function(){this.currentPage=1,this.page()},clear:function(){this.searchAdmin={name:"",sex:""},this.createTime="",this.updateTime="",this.page()},add:function(){var e,t=this;e=this.admin.id?Object(l["f"])(this.admin):Object(l["a"])(this.admin),e.then((function(e){1===e.data.code?(t.dialogVisible=!1,t.page(),t.$message({message:"恭喜你，保存成功",type:"success"}),t.admin={avatar:""}):t.$message.error(e.data.msg)}))},update:function(e){var t=this;this.dialogVisible=!0,Object(l["e"])(e).then((function(e){1===e.data.code&&(t.admin=e.data.data)}))},handleSizeChange:function(e){this.pageSize=e,this.page()},handleCurrentChange:function(e){this.currentPage=e,this.page()},deleteById:function(e){var t=this;this.$confirm("此操作将删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(l["b"])(e).then((function(e){1===e.data.code?(t.$message.success("恭喜你，删除成功"),t.page()):t.$message.error(e.data.msg)}))})).catch((function(){t.$message.info("已取消删除")}))},deleteByIds:function(){var e=this;this.$confirm("此操作将删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){for(var t=0;t<e.multipleSelection.length;t++)e.selectedIds[t]=e.multipleSelection[t].id;Object(l["b"])(e.selectedIds).then((function(t){1===t.data.code?(e.$message.success("恭喜你，删除成功"),e.page()):e.$message.error(t.data.msg)}))})).catch((function(){e.$message.info("已取消删除")}))},handleAvatarSuccess:function(e,t){this.admin.avatar=e.data},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,a=e.size/1024/1024<2;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),t&&a}},watch:{entrydate:function(e){e&&e.length>=2?(this.createTime=e[0],this.updateTime=e[1]):(this.createTime="",this.updateTime="")}}},o=s,c=(a("acac"),a("2877")),d=Object(c["a"])(o,n,i,!1,null,null,null);t["default"]=d.exports},"50fc":function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"d",(function(){return l})),a.d(t,"a",(function(){return r})),a.d(t,"f",(function(){return s})),a.d(t,"b",(function(){return o})),a.d(t,"e",(function(){return c}));var n=a("b775");function i(e){return Object(n["a"])({url:"/admin/login",method:"post",data:e})}function l(e,t,a,i,l,r){return Object(n["a"])({url:"/admin/admin?name="+e+"&sex="+t+"&begin="+a+"&end="+i+"&page="+l+"&pageSize="+r,method:"get"})}function r(e){return Object(n["a"])({url:"/admin/admin",method:"post",data:e})}function s(e){return Object(n["a"])({url:"/admin/admin",method:"put",data:e})}function o(e){return Object(n["a"])({url:"/admin/admin/"+e,method:"delete"})}function c(e){return Object(n["a"])({url:"/admin/admin/"+e,method:"get"})}},acac:function(e,t,a){"use strict";a("1aa5")}}]);