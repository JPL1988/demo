function viladateEmail(email){
    if(email==null||email.length==0){
        return false;
    }
    return true;
}
function viladatePwd(pwd){
    if(pwd==null||pwd.length==0){
        return false;
    }
    return true;
}
$('.btn').click(function(){
    var user=$('#user').val();
    var pwd = $('#pwd').val();
    if(!viladateEmail(user)){
        alert("邮箱不能为空！");
        return;
    }
    if(!viladatePwd(pwd)){
        alert("密码不能为空！");
        return;
    }
    var flag=$('#password-always-checkbox').is(':checked');
    login(user,pwd,flag);
})
function login(user,pwd,flag) {
    console.log(user+"___"+pwd+"____"+flag)
    $.ajax({
        type:"post",
        url:"/login",
        data:{"user":user,"pwd":pwd,"flag":flag},
        async:false,
        dataType:"json",
        success:function (data) {
            if(data["success"]){
                checkLogin();
            }else {
                alert(data["msg"])
            }
        },
        error:function () {
            window.location.href="/error.html";
        }
    })
}
function checkLogin() {
    $.ajax({
        type:"post",
        url:"/verify",
        dataType:"json",
        async:false,
        xhrField:{
            withCredentials: true
        },
        success:function (data){
            console.log(data)
            console.log(data["success"])
            if(data["success"]){
                window.location.href="/admin?user="+data["name"];
            }
        },
        error:function(data) {
        }
    })
}
checkLogin();