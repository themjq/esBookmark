(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-512bfe88"],{"26b0":function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-card",{staticClass:"divcard"},[s("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[s("p",{staticClass:"info",staticStyle:{float:"left",position:"relative","font-size":"35px","margin-top":"20px"}},[e._v("信息修改")]),s("el-avatar",{staticStyle:{float:"right"},attrs:{size:120,src:e.userInfo.avatar},on:{error:e.errorHandler}},[s("el-avatar",[e._v(" "+e._s(e.userInfo.username)+" ")])],1)],1)]),s("el-card",{staticClass:"divcard"},[s("el-form",{ref:"form",staticClass:"updateform",attrs:{model:e.userInfo,"label-width":"80px"}},[s("el-form-item",{attrs:{label:"用户名"}},[s("el-input",{model:{value:e.userInfo.username,callback:function(t){e.$set(e.userInfo,"username",t)},expression:"userInfo.username"}})],1),s("el-form-item",{attrs:{label:"昵称"}},[s("el-input",{model:{value:e.userInfo.name,callback:function(t){e.$set(e.userInfo,"name",t)},expression:"userInfo.name"}})],1),s("el-form-item",{attrs:{label:"密码"}},[s("el-input",{model:{value:e.userInfo.password,callback:function(t){e.$set(e.userInfo,"password",t)},expression:"userInfo.password"}})],1),s("el-form-item",{attrs:{label:"手机号"}},[s("el-input",{model:{value:e.userInfo.phone,callback:function(t){e.$set(e.userInfo,"phone",t)},expression:"userInfo.phone"}})],1),s("el-form-item",{attrs:{label:"身份证"}},[s("el-input",{model:{value:e.userInfo.idNumber,callback:function(t){e.$set(e.userInfo,"idNumber",t)},expression:"userInfo.idNumber"}})],1),s("el-form-item",{attrs:{label:"性别"}},[s("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.userInfo.sex,callback:function(t){e.$set(e.userInfo,"sex",t)},expression:"userInfo.sex"}},e._l(e.sexList,(function(e){return s("el-option",{key:e.value,attrs:{label:e.name,value:e.id}})})),1)],1),s("el-form-item",{attrs:{label:"头像"}},[s("el-upload",{staticClass:"avatar-uploader",attrs:{action:"/api/upload",headers:e.token,name:"image","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.userInfo.avatar?s("img",{staticClass:"avatar",attrs:{src:e.userInfo.avatar}}):s("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),s("el-form-item",{attrs:{label:"状态"}},[s("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择","value-key":"userInfo.status"},model:{value:e.userInfo.status,callback:function(t){e.$set(e.userInfo,"status",t)},expression:"userInfo.status"}},e._l(e.statusList,(function(e){return s("el-option",{key:e.value,attrs:{label:e.name,value:e.id}})})),1)],1),s("el-form-item",{attrs:{label:"创建日期"}},[s("el-date-picker",{staticStyle:{width:"100%"},attrs:{clearable:"",type:"date",placeholder:"选择日期",size:"small"},model:{value:e.userInfo.createTime,callback:function(t){e.$set(e.userInfo,"createTime",t)},expression:"userInfo.createTime"}})],1),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:e.updateUser}},[e._v("提交")])],1)],1)],1)],1)},r=[],n=s("c7eb"),o=s("1da1"),l=s("5f87"),i=s("c24f"),u={data:function(){return{userInfo:{},sexList:[{id:1,name:"男"},{id:2,name:"女"}],statusList:[{id:0,name:"启用"},{id:1,name:"禁用"}],token:{token:Object(l["a"])()}}},methods:{toggleSideBar:function(){this.$store.dispatch("app/toggleSideBar")},logout:function(){var e=this;return Object(o["a"])(Object(n["a"])().mark((function t(){return Object(n["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:Object(l["b"])(),e.$router.push("/login");case 2:case"end":return t.stop()}}),t)})))()},errorHandler:function(){return!0},handleAvatarSuccess:function(e,t){this.userInfo.avatar=e.data},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,s=e.size/1024/1024<2;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),s||this.$message.error("上传头像图片大小不能超过 2MB!"),t&&s},updateUser:function(){var e,t=this;e=Object(i["f"])(this.userInfo),e.then((function(e){1===e.data.code?(t.$message({message:"恭喜你，保存成功",type:"success"}),t.getUser()):t.$message.error(e.data.msg)}))},getUser:function(){var e=this,t=s("14b7"),a=Object(l["a"])();try{var r=t.decode(a),n=Object(i["e"])(r.id);n.then((function(t){1===t.data.code?e.userInfo=t.data.data:e.$message.error("获取用户信息失败")}))}catch(o){console.error("无法解码 JWT:",o)}}},mounted:function(){this.getUser()},computed:{sex:function(){return this.userInfo.sex?1===this.userInfo.sex?"男":2===this.userInfo.sex?"女":void 0:"暂无"},status:function(){return void 0==this.userInfo.status?"暂无":0===this.userInfo.status?"正常":1===this.userInfo.status?"禁用":void 0}}},c=u,f=(s("65ee"),s("2877")),d=Object(f["a"])(c,a,r,!1,null,null,null);t["default"]=d.exports},"65ee":function(e,t,s){"use strict";s("d6fd")},d6fd:function(e,t,s){}}]);