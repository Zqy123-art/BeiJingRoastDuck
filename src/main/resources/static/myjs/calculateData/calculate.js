
var check_task1



function showCal(a) {


    tid =   $("#tid").text()
    //发送请求
    $.ajax({
        type: "POST",
        url: "/add_tid",
        contentType: 'application/json',
        data: JSON.stringify({
            "tid":tid
        }),
        success: function (response) {
            window.location.replace("http://127.0.0.1:8080/data_show");

        },
        dataType: "json"
    });


}



//开始计算
function cal_start() {

    var filename = $("#cal_file").val()



    //发送请求
    $.ajax({
        type: "POST",
        url: "/start_calculate",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        data: JSON.stringify({
            "filename":filename
        }),

        success: function (response) {
            if (response.code==200){
                alert(response.message)




                window.location.reload();




                // reloadCalTable()
                //
                //
                // check_task1=setInterval("cal_pro_f2()",300);




            }else {
                alert(response.message)
            }

        },
        dataType: "json"
    });
}




//反复请求直到数据生成完成
function  cal_pro_f2() {




    $.ajax({
        type: "POST",
        url: "/cal_task_pro",
        xhrFields:{withCredentials:true},
        contentType: 'application/json',
        success: function (response) {

            $.each(response, function (index, i) {
                pro_tid = i.tid
                console.info(pro_tid+":"+i.taskProccess)
                $("#" + pro_tid + "").css('width',+i.taskProccess+"%")


                //刷新
                if (i.taskProccess==100){

                    reloadTable()
                }
            })



            if (response.length==0){
                //清空定时器
                clearInterval(check_task1)

            }
        },
        dataType: "json"
    });
}


function delCal(del) {
    var deltid = $(del).attr("id");
    tid = deltid.substring(3)

    $.ajax({
        type: "POST",
        url: "/del_calTask",
        xhrFields:{withCredentials:true},
        data: JSON.stringify({
            "tid":tid
        }),
        contentType: 'application/json',
        success: function (response) {
            alert(response.message)
            reloadCalTable()
        },
        dataType: "json"
    });
}



function reloadCalTable() {


    //清空
    $("#calTable").html('');


    $.ajax({
        type: "POST",
        url: "/find_finish_caltask",
        contentType: 'application/json',
        success: function (response) {
            if(response.length!=0){
                console.info("完成的len"+response.length)

                $.each(response, function (index, i) {
                    pro_tid = i.tid

                    console.info("====reloadCalTable:"+pro_tid)
                    var tr =    '<tr>'+
                        '<td id="tid">'+pro_tid+'</td>'+
                        '<td>'+i.uid+'</td>'+
                        '<td>'+
                        '<div class="progress br-30">'+
                        "<div class='progress-bar br-30 bg-info' id="+pro_tid+" role='progressbar' style='width:100%' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100'></div> </div>"+
                        '</td>'+
                        '<td>'+i.startDate+'</td>'+
                        '<td><p class="usr-location" data-location="计算完成">计算完成</p></td>'+

                        "<td><button id=del"+pro_tid+"  class='btn btn-outline-primary btn-rounded mb-2' onclick='delCal(this)'>Delete</button></td> "+
                        "<td><button id=show"+pro_tid+"  class='btn btn-outline-primary btn-rounded mb-2' onclick='showCal(this)'>show</button></td> "+
                        '</tr>'

                    $("#calTable").append(' <tr>'+tr+'</tr>');

                })

                // $("#" + pro_tid + "").css('width',50)

            }

            //查询未完成的

            $.ajax({
                type: "POST",
                url: "/cal_task_pro",
                contentType: 'application/json',
                success: function (response) {
                    if(response.length!=0){
                        console.info("没完成的len"+response.length)
                        $.each(response, function (index, i) {
                            console.info("未完成====reloadCalTable:"+i.tid)
                            pro_tid = i.tid
                            var tr =    '<tr>'+
                                '<td id="tid">'+pro_tid+'</td>'+
                                '<td>'+i.uid+'</td>'+
                                '<td>'+
                                '<div class="progress br-30">'+
                                "<div class='progress-bar br-30 bg-danger' id="+pro_tid+" role='progressbar' style='width: "+i.taskProccess+"%' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100'></div> </div>"+
                                '</td>'+
                                '<td>'+i.startDate+'</td>'+
                                '<td><p class="usr-location" data-location="正在计算">正在计算</p></td>'+
                                "<td><button id=del"+pro_tid+"  class='btn btn-outline-primary btn-rounded mb-2'  disabled=‘disabled' onclick='delCal(this)'>Delete</button></td> "+
                                "<td><button id=show"+pro_tid+"  class='btn btn-outline-primary btn-rounded mb-2' disabled=‘disabled' onclick='showCal(this)'>show</button></td> "+
                                '</tr>'

                            $("#calTable").append(' <tr>'+tr+'</tr>');

                        })
                    }
                },
                dataType: "json"
            });


        },
        dataType: "json"
    });


}

