var task


/**
 * 生成数据
 * **/

function dataProduce() {




    var dataNum = $("#data_num").val()
    var startDate = $("#start_date").val()

    var fileName = $("#filename").val()



    //输入框判断
    if (dataNum==""){
        alert("请输入生成数据条数")
        return
    }
    if(startDate=="" ){
        alert("请选择时间")
        return
    }
    if (fileName==""){
        alert("请输入文件命名")
        return
    }
    if(isNaN(dataNum)){
        alert("生成数据条数不是数字,请重新输入")
        return
    }



    endDate = startDate


    //显示进度条
    $("#progressbar").attr("style","display:block;");

    alert("数据准备生成,请等待...")

    $("#pro_btn").attr("disabled","disabled");

    // //打开cookie,设置cookie天数
    // $.cookie('proccess_cookie', 'true', { expires: 7 });
    //定时器打开
    task=setInterval("task_pro()",300);


    //发送请求
    $.ajax({
        type: "POST",
        url: "/data_pro",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        data: JSON.stringify({
            "dataNum": dataNum,
            "startDate": startDate,
            "endDate": endDate,
            "fileName":fileName
        }),

        success: function (response) {

            if (response.code=="200"){

                $("#pb").width("100%");


                $("#pro_btn").removeAttr("disabled");


                clearInterval(task);
                setTimeout(function(){alert("生成数据成功！") }, 1000);


                // //删除cookie
                // $.cookie('proccess_cookie', '', { expires: -1 });
                //成功弹窗在task_pro中
            }else {
                $("#pro_btn").removeAttr("disabled");
                clearInterval(task)
                alert(response.message)
                $("#progressbar").attr("style","display:none;");

            }

        },
        dataType: "json"
    });
}




/**
 *
 * 反复请求函数，用于查询数据生成进度
 *
 * **/
function task_pro() {
    //发送请求
    $.ajax({
        type: "POST",
        url: "/task_pro",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        success: function (response) {
            // $.each(response, function (index, item) {
            //     console.info(item.taskProccess)
            //     console.info(item.tid)
            //     console.info(item.uid)
            // })
            console.log(response.message);
            $("#pb").width(response.message+"%");
            if (response.message=="100"){

                //删除cookie
                // $.cookie('proccess_cookie', '', { expires: -1 });
                clearInterval(task)

                setTimeout(function(){alert("生成数据成功！") }, 1000);
                $("#pro_btn").removeAttr("disabled");

            }
        },
        dataType: "json"
    });

}



/**
 *
 * fileDownLoad
 * */

function filedown() {

    var filename = $("#DownLoad").val()
    window.location.href="http://127.0.0.1:8080/download?name="+filename


}



/**
 *
 * preview
 * */

function preview() {

    var filename = $("#DownLoad").val()

    //发送请求
    $.ajax({
        type: "POST",
        url: "/preview",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        data: JSON.stringify({
            "filename":filename
        }),

        success: function (response) {




            jsonData = JSON.stringify(response);

            if (response.length==0){
                alert("找不到文件啦，请刷新一下试试")
            }else {
                $("#preview_table").html('');//清空表格里面的所有数据
                //JSON.parse用于从一个字符串中解析出json对象
                //利用each遍历json数组，i代表下标，item代表该对象
                jQuery.each(JSON.parse(jsonData), function(i,item){

                    var tr =' <td class="sorting_1 sorting_2">'+
                        '<div class="d-flex">'+
                        '<p class="align-self-center mb-0 admin-name">'+item.movieId+'</p>'+
                        '</div>'+
                        '</td>'+
                        '<td>'+item.movieLanguage+'</td>'+
                        '<td>'+item.movieScore+'</td>'+
                        '<td>'+item.movieLong+'</td>'+
                        '<td>'+item.movieType+'</td>'+
                        '<td>'+item.movieTime+'</td>'
                    $("#preview_table").append(' <tr role="row">'+tr+'</tr>');
                });

            }




        },
        dataType: "json"
    });

}

/**
 *
 * del data
 * */

function del() {

    var filename = $("#DownLoad").val()

    //发送请求
    $.ajax({
        type: "POST",
        url: "/del",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        data: JSON.stringify({
            "filename":filename
        }),

        success: function (response) {
            if (response.code==200){
                alert(response.message)

            }else {
                alert(response.message)
            }

        },
        dataType: "json"
    });
}



function reload_task_page() {
    console.info("****************OK")

    //发送请求
    $.ajax({
        type: "POST",
        url: "/find_alltask_byId",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        success: function (response) {
            $("#taskTable").html('');
            //JSON.parse用于从一个字符串中解析出json对象
            //利用each遍历json数组，i代表下标，item代表该对象
            jsonData = JSON.stringify(response);
            jQuery.each(JSON.parse(jsonData), function(i,item){
                var tr =' <td>'+item.tid +'</td>'+
                    '<td>'+item.uid+'</td>'+
                    '<td><p class="text-info">100%</p></td>'+
                    '<td>'+item.startDate+'</td>'+
                    '<td><button id="bt1" type="submit" class="btn btn-secondary" onclick="return confirm(\'************\')">Check</button></td>'

                $("#taskTable").append(' <tr>'+tr+'</tr>');

            });

        },
        dataType: "json"
    });


}
