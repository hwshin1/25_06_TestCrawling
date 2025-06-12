<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>K 리그 로고 가져오기</title>
</head>
<body>
<h2>K리그 1부 팀 리스트</h2>
<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
    <thead>
    <tr>
        <th style="text-align: center;">구단 이름</th>
        <th style="text-align: center;">홈페이지</th>
        <th style="text-align: center;">구단로고</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="team" items="${teams }">
        <tr>
            <td style="text-align: center;">${team.teamName}</td>
            <td style="text-align: center;">
                <a target="_blank" class="hover:underline" href="${team.homepage }">${team.homepage }</a>
            </td>
            <td style="text-align: center;">
                <img src="${team.logo}" srcset="${team.logoSrcSet}" width="32px" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
