<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-5-8
  Time: 下午3:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>SSL认证网关管理中心</title>
    <script language="JavaScript">
        function down_win32() {
            document.getElementById('downWin32').href = "<c:url value="/DownLoadWin32"/>";
        }

        function down_win64() {
            document.getElementById('downWin64').href = "<c:url value="/DownLoadWin64"/>";
        }

        function down_android() {
            document.getElementById('downAndroid').href = "<c:url value="/DownLoadAndroid"/>";
        }

        function downLinuxCz() {
            document.getElementById('downLinuxCz').href = "<c:url value="/DownLoadLinuxCZ"/>";
        }
    </script>
</head>


<body>
<div style="text-align:center;">
    <h1>欢迎进入 SSLVPN 策略管理中心!</h1>
    <a href="javascript:void(0);" id="downWin32" onclick="down_win32();"><font color="#00008b">Windows 32位客户端</font></a>&nbsp;
    <a href="javascript:void(0);" id="downWin64" onclick="down_win64();"><font color="#00008b">Windows 64位客户端</font></a>&nbsp;<%--
    <a href="javascript:void(0);" id="downAndroid" onclick="down_android();"><font color="#00008b">Android 客户端</font></a>&nbsp;
    <a href="javascript:void(0);" id="downLinuxCz" onclick="downLinuxCz();"><font color="#00008b">Liunx 车载客户端</font></a>&nbsp;--%>
</div>
</body>
</html>
