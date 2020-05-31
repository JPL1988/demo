$('#personCount').click(function () {
    $.ajax({
        type:"post",
        url:"/person",
        async:true,
        dataType:"json",
        success:function (data) {
            console.log(data);
            console.log(data["count"]);
            $("#numOfPerson").text("计科院已登录人数"+data["count"]+"人");
        },
        error:function () {
            window.location.href="/error.html";
        }
    })
})