function regist() {

    var username =  $("#username").val();
    var password =  $("#password").val();



        //发送请求
        $.ajax({
            type: "POST",
            url: "/toregist",

            contentType: 'application/json',
            data: JSON.stringify({
                "username":username,
                "password":password
            }),
            success: function (response) {

                if (response.code==200){
                    alert(response.message)
                    window.location.replace("http://127.0.0.1:8080/login");
                }else {
                    alert(response.message)
                }

            },
            dataType: "json"
        });



}



function tologin() {
    var username =  $("#username").val();
    var password =  $("#password").val();



    //发送请求
    $.ajax({
        type: "POST",
        url: "/login_post",
        contentType: 'application/json',
        data: JSON.stringify({
            "username":username,
            "password":password
        }),
        success: function (response) {

            if (response.code==200){
                alert(response.message)
                window.location.replace("http://127.0.0.1:8080/data_produce");
            }else {
                alert(response.message)
                window.location.replace("http://127.0.0.1:8080/login");
            }
        },
        dataType: "json"
    });
}