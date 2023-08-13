<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--boardList에서 표부분 삭제하여 만들기--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
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
    let msg="${msg}";
    if(msg=="WRT_ERR") alert("게시물 등록에 실패했습니다. 다시 시도해 주세요")
    if(msg=="MOD_ERR") alert("게시물 수정에 실패했습니다. 다시 시도해 주세요")
</script>
<div style="text-align:center">
    <h2>게시물 ${mode=="new"?"글쓰기":"읽기"}</h2>
    <form action="" id="form">
        <input type="hidden" name="bno" value="${boardDto.bno}"/>
        <input type="text" name="title" value="${boardDto.title}" ${mode=="new"? '' : 'readonly="readonly"'}/>
        <textarea name="content" id="" cols="30" rows="10" ${mode=="new"? '' : 'readonly="readonly"'}>${boardDto.content}</textarea>
        <button type="button" id="writeBtn" class="btn">글쓰기</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    $(document).ready(function(){ //main() 메서드
        $('#listBtn').on("click", function () {
            // alert("listBtn Clicked"); //안될때 이벤트가 제대로 걸렸는지 확인
            location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}"; //Get방식
        });
        $('#removeBtn').on("click", function (){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            // post 방식으로 처리해야 함
            let form = $('#form');
            form.attr("action","<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
            form.attr("method","post");
            form.submit();
        });
        $('#writeBtn').on("click", function (){
            // post 방식으로 처리해야 함
            let form = $('#form');
            form.attr("action","<c:url value='/board/write'/>");
            form.attr("method","post");
            form.submit();
        });
        $('#modifyBtn').on("click", function (){
            //1. 읽기상태이면 -> 수정 상태로 변경
            let form = $('#form');
            let isReadOnly = $("input[name=title]").attr('readonly');
            if(isReadOnly == "readonly"){
                $("input[name=title]").attr('readonly', false); //제목
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("등록");
                $("h2").html("게시물 수정");
                return;
            }
            // 2. 수정상태이면 -> 수정된 서버를 서버로 전송

            // post 방식으로 처리해야 함
            form.attr("action","<c:url value='/board/modify'/>?page=${page}&pageSize=${pageSize}");
            form.attr("method","post");
            form.submit();
        });
    });
</script>
</body>
</html>