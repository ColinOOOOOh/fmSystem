webpackJsonp([3,27],{1007:function(t,a){t.exports={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("el-breadcrumb",{attrs:{separator:"/"}},[e("el-breadcrumb-item",[e("i",{staticClass:"el-icon-date"}),t._v(" 数据分析业务")]),t._v(" "),e("el-breadcrumb-item",[t._v("店面布局结果")])],1),t._v(" "),e("div",{staticClass:"echarts"},[e("IEcharts",{attrs:{option:t.bar}})],1)],1)},staticRenderFns:[]}},1025:function(t,a,e){var r=e(709);"string"==typeof r&&(r=[[t.i,r,""]]),r.locals&&(t.exports=r.locals);e(180)("4a0a2296",r,!0)},523:function(t,a,e){e(1025);var r=e(181)(e(601),e(1007),"data-v-e906f604",null);t.exports=r.exports},529:function(t,a,e){t.exports=e(51)(122)},531:function(t,a,e){t.exports=e(51)(124)},541:function(t,a,e){"use strict";function r(){return n}function o(t){n=t}a.a=r,a.b=o;var n=null},601:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r=e(529),o=e.n(r),n=e(531),s=e.n(n),i=e(541);a.default={components:{IEcharts:s.a},data:function(){return{bar:{color:["#20a0ff","#13CE66","#F7BA2A","#FF4949"],title:{text:"店面布局"},xAxis:{data:["短裤","衬衫","T恤","西装裤","polo衫","夹克","卫衣"]},yAxis:{name:"单位：件"},series:[{name:"销量",type:"bar",data:[50,200,70,100,120,150,60]}]}}},beforeMount:function(){var t=e.i(i.a)();null==t?o.a.post("DAController/layout").then(function(t){console.log(t.data);var a=(t.data.flat,t.data.object),e=[],r=[];a.forEach(function(t){e.push(t.commodityArea),r.push(t.commodityName)}),bar.xAxis.data=r,bar.series[0].data=e}):(t.object.forEach(function(t){data.push(t.commodityArea),xalias.push(t.commodityName)}),bar.xAxis.data=xalias,bar.series[0].data=data)}}},709:function(t,a,e){a=t.exports=e(68)(),a.push([t.i,".echarts[data-v-e906f604]{width:100%;height:400px}",""])}});