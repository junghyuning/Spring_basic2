<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<script>
    /*데이터는 잘 전송이 된 것 같은데 왜 삭제되었다는 알람이 안 뜨는지?*/
    let msg = "${msg}";
    if(msg == "MOD_OK") alert("글이 성공적으로 수정되었습니다");
    if(msg == "WRT_OK") alert("글이 성공적으로 등록되었습니다");
    if(msg == "DEL_OK") alert("삭제되었습니다");
    if(msg == "DEL_ERROR") alert("존재하지않는 게시물입니다.");
</script>
<div style="text-align:center">
    <button type="button" id="writeBtn" onclick="location.href = '<c:url value="/board/write"/>'">글쓰기</button>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>이름</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="boardDto" items="${list}">
            <tr>
                <th>${boardDto.bno}</th>
                <th><a href="<c:url value='/board/read?bno=${boardDto.bno}&page=${page}&pageSize=${pageSize}'/>"> ${boardDto.title}</a></th>
                <th>${boardDto.writer}</th>
                <th>${boardDto.regDate}</th>
                <th>${boardDto.viewCnt}</th>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div>
        <c:if test="${ph.showPrev}">
            <a class="page" href="<c:url value="/board/list?page=${ph.beginPage-1}"/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a class="page ${i==ph.page? "paging-active" : ""}" href="<c:url value="/board/list?page=${i}"/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a class="page" href="<c:url value="/board/list?page=${ph.endPage+1}"/>">&gt;</a>
        </c:if>
    </div>
</div>
</body>
</html>
