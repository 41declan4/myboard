<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

    <main role="main" class="container">

        <ul class="list-group list-group-horizontal text-right">
            <li class="list-group-item">번호 : <span id="id">${board.id}</span></li>
            <li class="list-group-item">작성자 : ${board.user.username}</li>
            <li class="list-group-item">작성일 : <fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd" /></li>
        </ul>
        <hr />

        <div class="form-group">
            ${board.title}
        </div>
        <hr />

        <div class="form-group">
            ${board.content}
        </div>
        <hr />

        <div class="text-right">
            <c:choose>
                <c:when test="${principal.user.username eq board.user.username}">
                    <a href="/board/${board.id}/update" class="btn btn-warning" type="button">수정</a>
                    <button id="btn-delete" class="btn btn-danger" type="button">삭제</button>
                    <a href="/" class="btn btn-secondary">되돌아가기</a>
                </c:when>
                <c:otherwise>
                    <a href="/" class="btn btn-secondary">되돌아가기</a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="card mt-5">
            <input type="hidden" id="userId" value="${principal.user.id}" />
            <input type="hidden" id="boardId" value="${board.id}" />
            <form>
                <div class="card-body">
                    <textarea id="comment" class="form-control" rows="1"></textarea>
                </div>
                <div class="card-footer text-right">
                    <button id="btn-reply-save" class="btn btn-primary" type="button">등록</button>
                </div>
            </form>
        </div>

        <div class="card mt-3">
            <div class="card-header">댓글 리스트</div>
            <ul class="list-group">
                <c:forEach var="reply" items="${board.replys}">
                    <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                        <div>${reply.comment}</div>
                        <div class="d-flex">
                            <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                            <c:if test="${reply.user.id eq principal.user.id}">
                            <button onclick="index.replyDelete(${board.id}, ${reply.id})" class="btn-danger" >삭제</button>
                        </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        
    </main>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
<script src="/js/board.js"></script>