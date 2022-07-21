let index = {
    init: function() {
        $('#btn-save').on('click', () => {
            this.save();
        })
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

    }
}

index.init();