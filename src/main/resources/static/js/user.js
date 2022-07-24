let index = {
    init: function() {
        $('#btn-save').on('click', () => {
            this.save();
        })

        $('#btn-update').on('click', () => {
            this.update();
        })
    },

    save: function() {
        let data = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val()
        }


        $.ajax({
            type: 'POST',
            url: '/auth/joinProc',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function(resp) {
            if (resp === 500) {
                alert('회원가입 실패');    
            } else {
                alert('회원가입 완료');
                location.href = '/';
            }
            
        }).fail(function(error) {
            alert("회원가입 실패");
            console.log(error);
        })
    },

    update: function() {
        let data = {
            id: $('#id').val(),
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val()
        }

        console.log(data);


        $.ajax({
            type: 'PUT',
            url: `/user/updateProc/${data.id}`,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function(resp) {
            if (resp === 500) {
                alert('회원 수정 실패');    
            } else {
                alert('회원 수정 완료');
                location.href = '/';
            }
            
        }).fail(function(error) {
            alert('회원 수정 실패');
            console.log(error);
        })
    }
}

index.init();