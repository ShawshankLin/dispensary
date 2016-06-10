<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>ECharts</title>
<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
</head>

<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height:400px"></div>
	<!-- ECharts单文件引入 -->
    <script src="${ctx}/scripts/echarts/source/echarts.js"></script>
	<script type="text/javascript" language="javascript">
		var myChart;
		var eCharts;
		// 路径配置
        require.config({
            paths: {
                echarts: '${ctx}/scripts/echarts/source'
            }
        });
		require(
			[ 'echarts',
			  'echarts/chart/bar',// 使用柱状图就加载bar模块，按需加载
              'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
			], DrawEChart //异步加载的回调函数绘制图表
		);

		//创建ECharts图表方法
		function DrawEChart(ec) {
			eCharts = ec;
			myChart = eCharts.init(document.getElementById('main'),'macarons');
			myChart.showLoading({
				text : "图表数据正在努力加载..."
			});
			//定义图表options
			var options = {
				title : {
					text : "每月就诊数",
					subtext : "柱状图",
					///sublink : "http://www.baidu.com"
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : []
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : ['bar','line' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
					        show : true,
					        title : '保存为图片',
					        type : 'png',
					        lang : ['点击保存'],
					    }
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ '1', '2', '3', '4', '5', '6', '7' ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value} 人'
					},
					splitArea : {
						show : true
					}
				} ],
				grid : {
					width : '90%'
				},
				series : [

				]
			};
			myChart.setOption(options); //先把可选项注入myChart中
			myChart.hideLoading();
			getChartData();//aja后台交互 
		}
	</script>


	<script type="text/javascript">
		function getChartData() {
			//获得图表的options对象
			var options = myChart.getOption();
			//通过Ajax获取数据
			$.ajax({
				type : "post",
				async : false, //同步执行
				url : "${ctx}/pages/PatiCaseHistory/showMonthVisitsChart.do?type=bar",
				data : {},
				dataType : "json", //返回数据形式为json
				success : function(result) {
					if (result) {
						options.legend.data = result.legend;
						options.xAxis[0].data = result.category;
						for(var i=0;i<result.series.length;i++){
							var o={
					            name:result.series[i].name,
					            type:result.series[i].type,
					            data:result.series[i].data,
					            markPoint : {
					                data : [
					                    {type : 'max', name: '最大值'},
					                    {type : 'min', name: '最小值'}
					                ]
					            },
					            markLine : {
					                data : [
					                    {type : 'average', name: '平均值'}
					                ]
					            }
					        };
							options.series.push(o);
						} 
						myChart.hideLoading();
						myChart.setOption(options);
					}
				},
				error : function(errorMsg) {
					alert("不好意思，图表请求数据失败啦!");
					myChart.hideLoading();
				}
			});
		}
	</script>
</body>
</html>
