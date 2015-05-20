<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/wall.css" rel="stylesheet" type="text/css" />
<title>服务器</title>

<script type="text/javascript">
	function disptime() {
		var today = new Date(); //获得当前时间
		var yy = today.getFullYear();
		var m = today.getMonth() + 1;
		var dd = today.getDate();
		var hh = today.getHours(); //获得小时、分钟、秒
		var mm = today.getMinutes();
		var ss = today.getSeconds();

		if (m < 10)
			m = "0" + m;
		if (dd < 10)
			dd = "0" + dd;
		if (hh < 10)
			hh = "0" + hh;
		if (mm < 10)
			mm = "0" + mm;
		if (ss < 10)
			ss = "0" + ss;
		/*设置div的内容为当前时间*/
		document.getElementById("myclock").innerHTML = "<h1>现在时间是：" + yy + "年"
				+ m + "月" + dd + "日      " + hh + ":" + mm + ":" + ss + "  "  + "<h1>";
				
		/*
		 使用setTimeout在函数disptime()体内再次调用setTimeout
		 设置定时器每隔1秒（1000毫秒），调用函数disptime()执行，刷新时钟显示
		 */
		setTimeout("disptime()", 1000);
	}
</script>
</head>
<body onload="disptime()">
	<br>
	<br>
	<div id="myclock"></div>

</body>
</html>