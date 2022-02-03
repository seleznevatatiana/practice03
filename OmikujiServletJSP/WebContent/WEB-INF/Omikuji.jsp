<%@ page import= "omikuji.OmikujiBean" %>
<jsp:useBean id="omikuji" class="omikuji.OmikujiBean" scope="request" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>今日の運勢</title>
</head>
<body>
<p>
今日の運勢は<%= omikuji.getUnsei() %>です！<br>
願い事：<%=omikuji.getNegaigoto()%><br>
商い：<%=omikuji.getAkinai()%><br>
学問：<%=omikuji.getGakumon()%><br>
</p>
</body>
</html>