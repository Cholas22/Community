/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2Target(questionId,1,content);
}
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    comment2Target(commentId,2,content);
}
function comment2Target(targetId,type,content) {
    if (!content) {
        alert("回复不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=cac3b05d57657b5636a1&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

/**
 * 展开二级评论
 * @param e
 */
function collapseComment(e) {
    var id = e.getAttribute("data-id");
    var comment = $("#comment-"+id);
    e.classList.toggle("active");
    if(e.classList.contains("active")){
        //获取二级评论
        $.getJSON("/comment/"+id,function (data) {
            console.log(data);
        });
    }
    comment.toggleClass("in");
}
