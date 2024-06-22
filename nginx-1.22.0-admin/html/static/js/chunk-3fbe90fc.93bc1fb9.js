(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3fbe90fc"],{6874:function(e,t,a){},aae9:function(e,t,a){"use strict";a("6874")},e382:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.searchUser}},[a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{attrs:{placeholder:"请输入用户姓名"},model:{value:e.searchUser.name,callback:function(t){e.$set(e.searchUser,"name",t)},expression:"searchUser.name"}})],1),a("el-form-item",{attrs:{label:"性别"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.searchUser.sex,callback:function(t){e.$set(e.searchUser,"sex",t)},expression:"searchUser.sex"}},[a("el-option",{attrs:{label:"男",value:"1"}}),a("el-option",{attrs:{label:"女",value:"2"}})],1)],1),a("el-form-item",{attrs:{label:"创建时间"}},[a("el-date-picker",{staticStyle:{width:"400px","margin-left":"20px"},attrs:{clearable:"","value-format":"yyyy-MM-dd",type:"daterange",placeholder:"选择日期","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.entrydate,callback:function(t){e.entrydate=t},expression:"entrydate"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("查询")]),a("el-button",{attrs:{type:"info"},on:{click:e.clear}},[e._v("清空")])],1)],1),a("el-row",[a("el-button",{attrs:{type:"danger",size:"medium"},on:{click:e.deleteByIds}},[e._v("- 批量删除")]),a("el-button",{attrs:{type:"primary",size:"medium"},on:{click:function(t){e.dialogVisible=!0,e.user={avatar:""}}}},[e._v("+ 新增")])],1),a("el-dialog",{ref:"form",attrs:{title:"编辑用户",visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.user,"label-width":"80px",size:"mini"}},[a("el-form-item",{attrs:{label:"用户名"}},[a("el-input",{model:{value:e.user.username,callback:function(t){e.$set(e.user,"username",t)},expression:"user.username"}})],1),a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{model:{value:e.user.name,callback:function(t){e.$set(e.user,"name",t)},expression:"user.name"}})],1),a("el-form-item",{attrs:{label:"密码"}},[a("el-input",{model:{value:e.user.password,callback:function(t){e.$set(e.user,"password",t)},expression:"user.password"}})],1),a("el-form-item",{attrs:{label:"手机号"}},[a("el-input",{model:{value:e.user.phone,callback:function(t){e.$set(e.user,"phone",t)},expression:"user.phone"}})],1),a("el-form-item",{attrs:{label:"身份证"}},[a("el-input",{model:{value:e.user.idNumber,callback:function(t){e.$set(e.user,"idNumber",t)},expression:"user.idNumber"}})],1),a("el-form-item",{attrs:{label:"性别"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.user.sex,callback:function(t){e.$set(e.user,"sex",t)},expression:"user.sex"}},e._l(e.sexList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"头像"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"/api/upload",headers:e.token,name:"image","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.user.avatar?a("img",{staticClass:"avatar",attrs:{src:e.user.avatar}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择","value-key":"user.status"},model:{value:e.user.status,callback:function(t){e.$set(e.user,"status",t)},expression:"user.status"}},e._l(e.statusList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"创建日期"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{clearable:"",type:"date",placeholder:"选择日期",size:"small"},model:{value:e.user.createTime,callback:function(t){e.$set(e.user,"createTime",t)},expression:"user.createTime"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.add}},[e._v("提交")]),a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取消")])],1)],1)],1),a("br"),[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),a("el-table-column",{attrs:{prop:"name",label:"昵称",align:"center"}}),a("el-table-column",{attrs:{prop:"username",label:"用户名",align:"center"}}),a("el-table-column",{attrs:{prop:"avatar",label:"头像",align:"center"},scopedSlots:e._u([{key:"default",fn:function(e){var t=e.row;return[a("el-image",{staticStyle:{width:"auto",height:"40px",border:"none",cursor:"pointer"},attrs:{src:t.avatar}})]}}])}),a("el-table-column",{attrs:{align:"center",label:"性别"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{staticStyle:{"margin-right":"10px"}},[e._v(" "+e._s("1"==t.row.sex?"男":"女"))])]}}])}),a("el-table-column",{attrs:{prop:"phone",label:"手机号",align:"center"}}),a("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[0==t.row.status?a("span",{staticStyle:{"margin-right":"10px"}},[e._v("启用")]):e._e(),1==t.row.status?a("span",{staticStyle:{"margin-right":"10px"}},[e._v("禁用")]):e._e()]}}])}),a("el-table-column",{attrs:{prop:"idNumber",label:"身份证号",align:"center"}}),a("el-table-column",{attrs:{align:"center",label:"创建日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.createTime?t.row.createTime.replace("T"," "):"")+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"更新日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.updateTime?t.row.updateTime.replace("T"," "):"")+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.update(t.row.id)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(a){return e.deleteById(t.row.id)}}},[e._v("删除")])]}}])})],1)],a("el-pagination",{attrs:{background:e.background,"current-page":e.currentPage,"page-sizes":[5,10,15,20],"page-size":5,layout:"total, sizes, prev, pager, next, jumper",total:e.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],2)},l=[],r=(a("b0c0"),a("c24f")),n=a("5f87"),i={data:function(){return{background:!0,pageSize:5,totalCount:100,currentPage:1,dialogVisible:!1,searchUser:{name:"",sex:""},user:{},sexList:[{id:1,name:"男"},{id:2,name:"女"}],statusList:[{id:0,name:"启用"},{id:1,name:"禁用"}],createTime:"",updateTime:"",entrydate:"",selectedIds:[],multipleSelection:[],tableData:[],token:{token:Object(n["a"])()}}},mounted:function(){this.page()},methods:{page:function(){var e=this;Object(r["f"])(this.searchUser.name,this.searchUser.sex,this.createTime,this.updateTime,this.currentPage,this.pageSize).then((function(t){e.totalCount=t.data.data.total,e.tableData=t.data.data.rows}))},handleSelectionChange:function(e){this.multipleSelection=e},onSubmit:function(){this.currentPage=1,this.page()},clear:function(){this.searchUser={name:"",sex:""},this.createTime="",this.updateTime="",this.page()},add:function(){var e,t=this;e=this.user.id?Object(r["h"])(this.user):Object(r["a"])(this.user),e.then((function(e){1===e.data.code?(t.dialogVisible=!1,t.page(),t.$message({message:"恭喜你，保存成功",type:"success"}),t.user={avatar:""}):t.$message.error(e.data.msg)}))},update:function(e){var t=this;this.dialogVisible=!0,Object(r["g"])(e).then((function(e){1===e.data.code&&(t.user=e.data.data)}))},handleSizeChange:function(e){this.pageSize=e,this.page()},handleCurrentChange:function(e){this.currentPage=e,this.page()},deleteById:function(e){var t=this;this.$confirm("此操作将删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(r["b"])(e).then((function(e){1===e.data.code?(t.$message.success("恭喜你，删除成功"),t.page()):t.$message.error(e.data.msg)}))})).catch((function(){t.$message.info("已取消删除")}))},deleteByIds:function(){var e=this;this.$confirm("此操作将删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){for(var t=0;t<e.multipleSelection.length;t++)e.selectedIds[t]=e.multipleSelection[t].id;Object(r["b"])(e.selectedIds).then((function(t){1===t.data.code?(e.$message.success("恭喜你，删除成功"),e.page()):e.$message.error(t.data.msg)}))})).catch((function(){e.$message.info("已取消删除")}))},handleAvatarSuccess:function(e,t){this.user.avatar=e.data},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,a=e.size/1024/1024<2;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),t&&a}},watch:{entrydate:function(e){e&&e.length>=2?(this.createTime=e[0],this.updateTime=e[1]):(this.createTime="",this.updateTime="")}}},o=i,c=(a("aae9"),a("2877")),u=Object(c["a"])(o,s,l,!1,null,null,null);t["default"]=u.exports}}]);