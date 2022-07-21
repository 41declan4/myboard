let index = {
    init: function() {
        $('#btn-save').on('click', () => {
            this.save();
        })

        $('#btn-update').on('click', () => {
            this.update();
        })

        $('#btn-delete').on('click', () => {
            this.boardDelete();
        })

        $('#btn-reply-save').on('click', () => {
            this.replySave();
        })

        // $('#btn-reply-delete').on('click', () => {
        //     this.replyDelete();
        // })

    },

    save: function() {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        }

        // console.log(data);

        $.ajax({
            type: 'POST',
            url: '/api/writeProc',
            data: JSON.stringify(data),
            contentType: 'application/json; utf-8',
            dataType: 'json'
        }).done(res => {
            alert('글쓰기가 완료되었습니다.'),
            location.href = '/';
        }).fail(error => {
            alert(JSON.stringify(error));
        })

    },

    update: function() {
        let id = $('#id').val();
        
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        }

        console.log(id, data);

        $.ajax({
            type: 'PUT',
            url: '/api/updateProc/' + id,
            data: JSON.stringify(data),
            contentType: 'application/json; utf-8',
            dataType: 'json'
        }).done(res => {
            alert('글 수정이 완료되었습니다.'),
            location.href = '/';
        }).fail(error => {
            alert(JSON.stringify(error));
        })

    },

    boardDelete: function() {
        let id = $('#id').text()
        

        // console.log(id);

        $.ajax({
            type: 'DELETE',
            url: '/api/delete/' + id,
        }).done(res => {
            alert('글 삭제 완료되었습니다.'),
            location.href = '/';
        }).fail(error => {
            alert(JSON.stringify(error));
        })

    },

    replySave: function() {
        
        let data = {
            userId: $('#userId').val(),
            boardId: $('#boardId').val(),
            comment: $('#comment').val()
        }

        console.log(data);

        $.ajax({
            type: 'POST',
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: 'application/json; utf-8',
            dataType: 'json'
        }).done(res => {
            alert('댓글 등록 완료');
            location.href = `/board/${data.boardId}`;
        }).fail(error => {
            alert(JSON.stringify(error));
        })
        
    },

    replyDelete: function(boardId, replyId) {

        // let data = {
        //     boardId: $('#boardId').val(),
        //     replyId: $('#replyId').val()
        // }

        // console.log(data);

        $.ajax({
            type: 'DELETE',
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: 'json'
        }).done(res => {
            alert('댓글 삭제 완료');
            location.href = `/board/${boardId}`;
        }).fail(error => {
            alert(JSON.stringify(error));
        })

    }
}

index.init();