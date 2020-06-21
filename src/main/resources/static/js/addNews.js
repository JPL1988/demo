function add() {
    var title =$('.input_title').val();
    if(title==null||title.length==0){
        alert("新闻标题不能为空");
        return;
    }
    var content = simplemde.value();
    if(content==null||content.length==0){
        alert("新闻内容不能为空");
        return;
    }
    var source=$('.input_source').val();
    if(source==null||source.length==0){
        alert("新闻来源不能为空");
        return;
    }
    var time = $('.input_time').val();
    if(time==null||time.length==0){
        alert("发表时间不能为空");
        return;
    }
    alert(content);
    $.post('/insertNews',{'title':title,'content':content,'source':source,'time':time},function (result) {
        if(result=='true'){
            alert("发布成功,请到新闻列表查看新闻");
        }else {
            alert("请稍后重试");
        }
    })
}