<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

    <main role="main" class="container">
        <form action="">
            <div class="form-group">
                <input type="text" class="form-control" id="title">
            </div>

            <div class="form-group">
                <textarea class="form-control summernote" id="content" rows="3"></textarea>
            </div>

            <div class="text-right">
                <button type="button" id="btn-save" class="btn btn-primary">작성</button>
                <a href="/" class="btn btn-primary">돌아가기</a>
            </div>

        </form>
    </main>

    <script>
        $('.summernote').summernote({
            height: 400
        });
    </script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
<script src="/js/board.js"></script>