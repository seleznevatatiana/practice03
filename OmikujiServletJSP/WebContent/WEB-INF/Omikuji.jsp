<%@ page import= "omikuji.OmikujiBean" %>
<jsp:useBean id="omikujiBean" class="omikuji.OmikujiBean" scope="request" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>今日の運勢</title>
</head>
<body style="background-color:pink">
<p>
今日の運勢は<%= omikujiBean.getUnsei() %>です！<br>
願い事：<%=omikujiBean.getNegaigoto()%><br>
商い：<%=omikujiBean.getAkinai()%><br>
学問：<%=omikujiBean.getGakumon()%><br>
</p>
</body>
</html>