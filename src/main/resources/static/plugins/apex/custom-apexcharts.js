// Simple Line








//发送请求
$.ajax({
    type: "POST",
    url: "/find_data",
    contentType: 'application/json',

    success: function (response) {

        if (response.code==200){

            var arr_1 = response.demand1.split(',');
            var arr_2 = response.demand2.split(',');
            var arr_3 = response.demand3.split(',');
            var arr_4 = response.demand4.split(',');
            var arr_5 = response.demand5.split(',');
            var arr_6 = response.demand6.split(',');
            var arr_7 = response.demand7.split(',');

            var in_arr_6 = []
            var out_arr_6 = []
            var arr_4_100 = []
            for(i in arr_6  ){

                if (i>5){
                    out_arr_6.push(arr_6[i])

                }else {
                    in_arr_6.push(arr_6[i])

                }
            }


            for(i in arr_4  ){
                arr_4_100.push(arr_4[i]*100)
            }



// Radial Chart

            var radialChart = {
                chart: {
                    height: 350,
                    type: 'radialBar',
                    toolbar: {
                        show: false,
                    }
                },
                // colors: ['#1b55e2', '#888ea8', '#acb0c3', '#d3d3d3'],
                plotOptions: {
                    radialBar: {
                        dataLabels: {
                            name: {
                                fontSize: '22px',
                            },
                            value: {
                                fontSize: '16px',
                            },
                            total: {
                                show: true,
                                label: 'Total',
                                formatter: function (w) {
                                    // By default this function returns the average of all series. The below is just an example to show the use of custom formatter function
                                    return 100
                                }
                            }
                        }
                    }
                },
                series: arr_4_100,
                labels: ['4-5', '5-6', '6-7', '7-8', '8-9', '9-10'],
            }

            var sLineArea = {
                chart: {
                    height: 350,
                    type: 'area',
                    toolbar: {
                        show: false,
                    }
                },
                // colors: ['#1b55e2', '#888ea8'],
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'smooth'
                },
                series: [{
                    name: '国内',
                    data:in_arr_6
                }, {
                    name: '国外',
                    data: out_arr_6
                }],

                xaxis: {
                    type: 'value',

                    categories: ["4-5","5-6","6-7","7-8","8-9","9-10"],
                },
                tooltip: {
                    x: {
                        format: 'dd/MM/yy HH:mm'
                    },
                }
            }

            var chart = new ApexCharts(
                document.querySelector("#s-line-area"),
                sLineArea
            );

            chart.render();

            var chart = new ApexCharts(
                document.querySelector("#radial-chart"),
                radialChart
            );

            chart.render();









            /*
                =============================================
                    Perfect Scrollbar | Recent Activities
                =============================================
            */







        }else {
            alert(response.message)
            window.location.replace("http://127.0.0.1:8080/calculate");

        }


    },
    dataType: "json"
});



