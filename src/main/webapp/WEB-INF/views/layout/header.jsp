<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="/css/search.css" rel="stylesheet">
        <link href="/css/starter-template.css" rel="stylesheet">
        <link href="/css/sticky-footer.css" rel="stylesheet">

        <title>:: 메인페이지 ::</title>
    </head>

    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal" var="principal" />
    </sec:authorize>

    <body>
        
        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">홈 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/board/writeForm">게시판 쓰기</a>
                </li>
                </ul>
                <div class="text-right">
                    <c:choose>
                        <c:when test="${empty principal}">
                            <a href="/auth/loginForm" class="btn btn-primary">로그인</a>
                            <a href="/auth/joinForm" class="btn btn-primary">회원가입</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/user/userForm" class="btn btn-primary">회원정보</a>
                            <a href="/logout" class="btn btn-primary">로그아웃</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>