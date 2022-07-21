<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

        <main role="main" class="container">

            <div class="starter-template">
                <h1>Bootstrap starter template</h1>
                <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
            </div>

            <div class="row">
                <div class="col-lg-12 card-margin">
                    <div class="card search-form">
                        <div class="card-body p-0">
                            <form id="search-form">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="row no-gutters">
                                            <div class="col-lg-11 col-md-6 col-sm-12 p-0">
                                                <input type="text" placeholder="Search..." class="form-control" id="searchText" name="searchText" value="${param.searchText}">
                                            </div>
                                            <div class="col-lg-1 col-md-3 col-sm-12 p-0">
                                                <button type="submit" class="btn btn-base">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="board" items="${boards}">
                        <tr>
                            <td><c:out value="${board.id}" /></td>
                            <td><a href="/board/${board.id}"><c:out value="${board.title}" /></a></td>
                            <td><c:out value="${board.user.username}" /></td>
                            <td><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                    </c:forEach>
                    
                </tbody>
            </table>

            <!-- <div>Showing ${number+1} of ${size+1} of ${totalElements}</div> -->
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:choose>
                        <c:when test="${number == 0}">
                            <li class="page-item disabled">
                                <a class="page-link" href="?page=${number - 1}&searchText=${param.searchText}">Previous</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="?page=${number - 1}&searchText=${param.searchText}">Previous</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    
                    <c:forEach begin="0" end="${totalPages - 1}" var="page">
                        <c:choose>
                            <c:when test="${page == number}">
                                <li class="page-item disabled">
                                    <a class="page-link" href="?page=${page}&searchText=${param.searchText}">${page + 1}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page}&searchText=${param.searchText}">${page + 1}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        
                    </c:forEach>

                    <c:choose>
                        <c:when test="${hasNext}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${number + 1}&searchText=${param.searchText}">Next</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item disabled">
                                <a class="page-link" href="?page=${number + 1}&searchText=${param.searchText}">Next</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    
                </ul>
            </nav>

        </main>
        <!-- /.container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>