function delNews(obj){
    var value = $(obj).parent().attr('newsId');
    $.post('/delNews',{newsId:value},function (result) {
        if(result){
            alert("删除成功");
            $(obj).parent().parent().remove();
        }
    })
}

function modifyNews(obj){
    var value = $(obj).parent().attr('newsId');
    window.parent.menuFrame.location.href= "/modify?newsId="+value;
}