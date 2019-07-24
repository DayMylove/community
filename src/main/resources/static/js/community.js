function postComment() {
    var questionId = $('#question_id').val()
    var commentBody = $('#commentBody').val()

    $.ajax({
        url: '/comment',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "Content": commentBody,
            "type": 1
        }),
        success: function (data) {
            if (data.code == 200) {

            } else {
                if (data.code == 2002) {
                    //是否点击确认框
                    var isClicked = window.confirm(data.message);
                    if (isClicked) {
                        window.open('https://github.com/login/oauth/authorize?client_id=79fd5140e9dcc4639cfc&redirect_uri=http://localhost:8887/callback&scope=user&state=1');
                        window.localStorage.setItem('closable', true);
                    }
                }
            }

        }, error: function (e) {
            console.log(e)
        }
    })
}