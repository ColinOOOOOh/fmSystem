webpackJsonp([18,27],{526:function(e,t,s){var r=s(181)(s(604),s(998),null,null);e.exports=r.exports},529:function(e,t,s){e.exports=s(51)(122)},604:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=s(529),n=s.n(r);t.default={data:function(){return{username:"",userPhone:"",userId:""}},methods:{onSubmit:function(){var e=this;n.a.post("shopController/searchEmployeeUser",{userPhone:this.userPhone}).then(function(t){console.log(t.data);var s=t.data.object;e.username=s.username,e.userPhone=s.userPhone,e.userId=s.userId,e.$message.success("查询成功")}).catch(function(t){e.$message.error("查询失败")})},add:function(){var e=this;n.a.post("shopController/addEmplopyee",{username:this.username,userPhone:this.userPhone,userId:this.userId}).then(function(t){console.log(t.data),e.$message.success("添加店员成功")}).then(function(t){e.$message.error("添加店员失败")})}}}},998:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("div",{staticClass:"crumbs"},[s("el-breadcrumb",{attrs:{separator:"/"}},[s("el-breadcrumb-item",[s("i",{staticClass:"el-icon-date"}),e._v("店员信息")]),e._v(" "),s("el-breadcrumb-item",[e._v("用户添加")])],1)],1),e._v(" "),s("h2",[e._v("查询一般用户")]),e._v(" "),s("div",{staticStyle:{display:"flex","margin-top":"20px"}},[s("div",{staticStyle:{width:"30%"}},[s("el-input",{attrs:{placeholder:"用户手机号码"},model:{value:e.userPhone,callback:function(t){e.userPhone=t},expression:"userPhone"}})],1),e._v(" "),s("div",{staticStyle:{"margin-left":"20px"}},[s("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("查询")])],1)]),e._v(" "),e._m(0),e._v(" "),s("div",{staticStyle:{"line-height":"50px",border:"1px solid #ccc","border-radius":"5px",width:"40%",padding:"20px"}},[s("div",[s("label",[e._v("用户名：")]),e._v(" "),s("span",[e._v(e._s(e.username))])]),e._v(" "),s("div",[s("label",[e._v("手机号码：")]),e._v(" "),s("span",[e._v(e._s(e.userPhone))])]),e._v(" "),s("div",[s("el-button",{attrs:{type:"primary",disabled:""},on:{click:e.add}},[e._v("添加店员")])],1)])])},staticRenderFns:[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticStyle:{"line-height":"50px"}},[s("p",[e._v("查询结果：")])])}]}}});